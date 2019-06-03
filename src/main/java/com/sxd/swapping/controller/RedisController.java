package com.sxd.swapping.controller;

import com.sxd.swapping.utils.RedisLock;
import com.sxd.swapping.utils.RedisLock2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * redis解决并发竞争问题  的解决方案
 */
@RequestMapping("/redistest")
@RestController
public class RedisController {

    //分布式锁的标识
    public static final String REDIS_KEY = "ms-swapping:mykey";


    //这个key只是用来记录有多少个请求在并发访问[实际业务并不需要]
    public static final String REDIS_COUNT_KEY = "ms-swapping:countKey";
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisLock lock;

    @Autowired
    RedisLock2 lock2;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * redis 发布订阅pubsub
     */
    @RequestMapping(value = "/redisPubSub")
    public void redisPubSub(String msg){
        if (msg.contains("用户")){
            redisTemplate.convertAndSend("user",msg);
        }else {
            redisTemplate.convertAndSend("goods",msg);
        }
    }



    /**
     * redis 批量操作其中一种方式
     * redis pipeline 管道技术
     */
    @RequestMapping(value = "/redisPipeline" )
    public void redisPipeline(){

//        1.executePipelined 重写 入参 RedisCallback 的doInRedis方法
        List<Object> resultList = redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                2.connection 打开管道
                connection.openPipeline();

//                3.connection 给本次管道内添加 要一次性执行的多条命令

//                3.1 一个set操作
                byte[] key1 = "mykey1".getBytes();
                byte[] value1 = "字符串value".getBytes();
                connection.set(key1,value1);

//                3.2一个批量mset操作
                Map<byte[],byte[]> tuple = new HashMap<>();
                tuple.put("m_mykey1".getBytes(),"m_value1".getBytes());
                tuple.put("m_mykey2".getBytes(),"m_value2".getBytes());
                tuple.put("m_mykey3".getBytes(),"m_value3".getBytes());
                connection.mSet(tuple);

//                 3.3一个get操作
                connection.get("m_mykey2".getBytes());

//                4.关闭管道 不需要close 否则拿不到返回值
//                connection.closePipeline();

//                这里一定要返回null，最终pipeline的执行结果，才会返回给最外层
                return null;
            }
        });


//        5.最后对redis pipeline管道操作返回结果进行判断和业务补偿
        for (Object str : resultList) {
            System.out.println(String.valueOf(str));
        }
        
    }




    /**
     * redis分布式锁 实现[spring boot2.x LUA脚本]
     */
    @RequestMapping("/test3")
    public void test3(){
        ValueOperations vops = redisTemplate.opsForValue();
        String uuid = UUID.randomUUID().toString();

        //加锁
        if (lock2.lock("mykey1",uuid,5000,3)){

            try {
//                执行业务
                System.out.println("加锁成功，做业务");

                vops.increment(REDIS_COUNT_KEY,1);
                Thread.sleep(3000);

                System.out.println("业务执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //解锁
                lock2.unlock("mykey1",uuid);
            }
        }
    }


    /**
     * redis分布式锁[spring boot 1.5.x LUA]
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void test2(){

        ValueOperations vops = redisTemplate.opsForValue();
        String uuid = UUID.randomUUID().toString();
        if (lock.lock("mykey1",uuid,3)){
            System.out.println("加锁成功");
            vops.increment(REDIS_COUNT_KEY,1);


            lock.unlock("mykey1",uuid);
        }

    }


    /**
     * redis 分布式锁  [incr自增 +expire过期 实现分布式锁]
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){


        ValueOperations vops = redisTemplate.opsForValue();




        dealByIncr(vops);


    }






    /**
     *
     * 方式1
     * 利用redis自带的incr命令 解决并发竞争
     *
     * 利用redis的incr命令的自增原子性操作，实现分布式锁
     * 如果自增返回后不是1，说明加了锁，检查锁是否有过期时间并给锁加过期时间，并继续调用业务方法即可
     *
     * 如果是1，说明没加锁，执行业务，在业务做完后，执行删除 并发标识的操作。
     *
     * 使用步骤：
     * 1.redis调用incr，判断是否并发标识存在
     * 2.正常进入业务，先给并发标识 设置过期时间，防止key未正常释放导致的 死锁
     * 3.业务执行完成，无论如何，删除并发标识
     *
     */
    public void dealByIncr(ValueOperations vops){

        //1.加锁
        Long increment = vops.increment(REDIS_KEY, 1);

        try{
            //2.1如果 incr结果为1  表示加锁成功
            if(increment == 1){

                try{
                    //2.1.1 先设置并发标识 的key  过期时间为2s,放置key未被正确释放，导致死锁
                    redisTemplate.expire(REDIS_KEY,2, TimeUnit.SECONDS);

                    //2.1.2 执行业务
                    Long count = vops.increment(REDIS_COUNT_KEY,1);//本行属于业务逻辑 与本次的并发锁无关
                    System.out.println("编号为"+count+"的请求在执行业务");

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //2.1.3 业务执行完成，删除自增标识
                    redisTemplate.delete(REDIS_KEY);
                }

            }else {
            //2.2 incr结果不为1  说明 加锁失败

                //2.2.1 先判断已存在的锁是否存在过期时间 调用的是redis的PTTL 命令
                //如果 key不存在 返回 -2  如果key存在，没有设置过期时间 返回-1  ，如果有过期时间，返回剩余过期时间，以毫秒为单位
                Long expireTime = redisTemplate.getExpire(REDIS_KEY);
                if (expireTime == -1){
                    redisTemplate.expire(REDIS_KEY,2, TimeUnit.SECONDS);
                }

                //2.2.2 然后 继续等待获取锁，执行业务
                dealByIncr(vops);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
