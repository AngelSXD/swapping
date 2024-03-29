package com.sxd.swapping.controller;

import com.sxd.swapping.mybatis.vo.UniVerResponse;
import com.sxd.swapping.jpa.pojo.GoodsStock;
import com.sxd.swapping.service.GoodsStockService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/goods/stock")
public class GoodsStockController {


    @Resource
    private GoodsStockService goodsStockService;

    /**
     * 保存 商品库存信息
     * @param goodsStock
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public UniVerResponse<GoodsStock> insert(@RequestBody GoodsStock goodsStock){
        UniVerResponse<GoodsStock> res = new UniVerResponse<>();
        UniVerResponse.checkField(goodsStock,"goodsName","goodsPrice","stock");

        goodsStock.initEntity();
        goodsStock.setSaleNum(0L);
        goodsStock.setVersion(0);

        GoodsStock save = goodsStockService.save(goodsStock);
        if (save!= null){
            res.beTrue(save);
        }else {
            res.beFalse("保存失败",UniVerResponse.ERROR_BUSINESS,null);
        }
        return res;
    }

    /**
     * uid代表            同一时间 大家都来买这一件东西
     * threadCount代表    同时会有多少人在操作
     * buyNum代表         同一个人的一次购买量
     * @param entity
     * @return
     */
    @RequestMapping(value = "/concurrentStock",method = RequestMethod.POST)
    public UniVerResponse<Map<Integer,String>> concurrentStock(@RequestBody GoodsStock entity){
        UniVerResponse.checkField(entity,"uid","threadCount","buyNum");
        UniVerResponse<Map<Integer,String>> res = new UniVerResponse<>();

        String uid = entity.getUid();

        GoodsStock old = goodsStockService.findByUid(uid);
        if (old != null){
            //设置一个线程安全的Map记录各个线程是否成功执行
            Map<Integer,String> map = new ConcurrentHashMap<Integer, String>();


            Integer threadCount = entity.getThreadCount();
            //所有线程阻塞，然后统一开始
            CountDownLatch begin = new CountDownLatch(1);

            //主线程阻塞，直到所有分线程执行完毕
            CountDownLatch end = new CountDownLatch(threadCount);

            //开始多线程
            begin.countDown();
            for (Integer i = 0; i < threadCount; i++) {
                Runnable runnable = buyGoods(map,entity,i,begin,end);
                new Thread(runnable).start();
            }

            //多个线程都执行结束
            try {
                end.await();
                res.beTrue(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
                res.beFalse("多线程执行失败",UniVerResponse.ERROR_BUSINESS,null);
            }
        }else {
            res.beFalse("商品不存在",UniVerResponse.ERROR_BUSINESS,null);
        }
        return  res;
    }


    //多线程的方法
    public Runnable buyGoods(Map<Integer,String> map, GoodsStock entity, Integer threadNum,CountDownLatch begin,CountDownLatch end){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("线程"+threadNum+":--------------------->开始工作");
                    begin.await();

                    goodsStockService.updateStock(map,entity,threadNum);

                    end.countDown();
                    System.out.println("线程"+threadNum+":--------------------->结束工作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        return runnable;
    }

}
