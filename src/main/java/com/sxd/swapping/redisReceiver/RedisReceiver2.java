package com.sxd.swapping.redisReceiver;

import org.springframework.stereotype.Service;

/**
 * redis 订阅发布  消息接收器/处理器2
 * @author sxd
 * @date 2019/5/30 17:15
 */
@Service
public class RedisReceiver2 {

    public void receiveMessage(String message) {
        System.out.println("消息处理器2>我处理商品信息："+message);
        //这里是收到通道的消息之后执行的方法
        //此处执行接收到消息后的 业务逻辑
    }
}
