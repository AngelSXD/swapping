package com.sxd.swapping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author sxd
 * @date 2019/5/28 9:10
 */
@RestController
@RequestMapping(value = "/queue")
public class QueueController {

    static ConcurrentLinkedQueue<String> waitQueue = new ConcurrentLinkedQueue<>();//等待队列

    static ConcurrentLinkedQueue<String> runQueue = new ConcurrentLinkedQueue<>();//执行队列



    @RequestMapping("/test")
    public void userQueue(String str){
        doBusiness(str);
        System.out.println("controller执行结束");
    }


    public void doBusiness(String str){

       if (runQueue.size() > 0 ){
           waitQueue.add(str);
           System.out.println(str+"加入等待队列，等待执行");
       }else {
           runQueue.add(str);
           System.out.println(str+"加入执行队列，准备执行");


           try {
               System.out.println(str+"执行业务");
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }finally {
               String poll = runQueue.poll();
               int size = runQueue.size();
               System.out.println(poll+"执行完成，从执行队列移除，此时执行队列大小为"+size);

               if (waitQueue.size() >0){
                   doBusiness(waitQueue.poll());
               }
           }



       }

    }
}
