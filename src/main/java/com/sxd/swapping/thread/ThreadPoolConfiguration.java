package com.sxd.swapping.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ConditionalOnProperty(name = "thread.pool.core.size")
public class ThreadPoolConfiguration {


    @Value("${thread.pool.core.size}")
    private Integer coreSize;

    @Value("${thread.pool.max.size}")
    private Integer maxSize;

    @Value("${thread.pool.queue.capacity}")
    private Integer queueCapacity;

    @Value("${thread.pool.alive.seconds}")
    private Integer keepAliveSeconds;

    /**
     * 业务1专用线程池
     * @return
     */
    @Bean(name = "taskExecutor1")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(coreSize);
        poolTaskExecutor.setMaxPoolSize(maxSize);
        poolTaskExecutor.setQueueCapacity(queueCapacity);
        poolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        return poolTaskExecutor;
    }

    /**
     * 业务2专用线程池
     * @return
     */
    @Bean(name = "taskExecutor2")
    public ThreadPoolTaskExecutor taskExecutor2() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(coreSize);
        poolTaskExecutor.setMaxPoolSize(maxSize);
        poolTaskExecutor.setQueueCapacity(queueCapacity);
        poolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        return poolTaskExecutor;
    }
}
