package com.sxd.swapping.config;

import com.sxd.swapping.redisReceiver.RedisReceiver;
import com.sxd.swapping.redisReceiver.RedisReceiver2;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



/**
 * @author sxd
 * @date 2019/5/27 16:13
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {



    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2)
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //可以添加多个 messageListener
        //可以对 messageListener对应的适配器listenerAdapter  指定本适配器 适配的消息类型  是什么
        //在发布的地方 对应发布的redisTemplate.convertAndSend("user",msg);  那这边的就对应的可以消费到指定类型的 订阅消息
        container.addMessageListener(listenerAdapter, new PatternTopic("user"));
        container.addMessageListener(listenerAdapter2, new PatternTopic("goods"));

        return container;
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * receiveMessage 是默认监听方法 一般不变
     * @param redisReceiver redis消息处理器，自定义的
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisReceiver redisReceiver) {
        System.out.println("消息适配器1进来了");
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * receiveMessage 是默认监听方法 一般不变
     * @param redisReceiver2 redis消息处理器，自定义的
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter2(RedisReceiver2 redisReceiver2) {
        System.out.println("消息适配器2进来了");
        return new MessageListenerAdapter(redisReceiver2, "receiveMessage");
    }



    //使用默认的工厂初始化redis操作模板
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer keySerializer = new StringRedisSerializer();
//        RedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        //key采用字符串反序列化对象
        redisTemplate.setKeySerializer(keySerializer);
        //value也采用字符串反序列化对象
        //原因：管道操作，是对redis命令的批量操作，各个命令返回结果可能类型不同
        //可能是 Boolean类型 可能是String类型 可能是byte[]类型 因此统一将结果按照String处理
        redisTemplate.setValueSerializer(keySerializer);
        return redisTemplate;
    }









}
