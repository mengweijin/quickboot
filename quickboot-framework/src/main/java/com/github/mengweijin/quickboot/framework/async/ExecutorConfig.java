package com.github.mengweijin.quickboot.framework.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 当任务新增进来时：
 * 1. 当前运行的线程 < corePoolSize 时，新起一个线程执行新增进来的任务；
 * 2. 当前运行的线程 >= corePoolSize 时，新增进来的任务添加到阻塞队列；
 * 3. 阻塞队列已经满了时，但当前运行线程数 < maxPoolSize, 新起一个线程执行新增进来的任务；
 * 4. 阻塞队列已经满了时，并且当前运行线程数 = maxPoolSize，执行任务丢弃策略。
 *
 * @author mengweijin
 */
@EnableAsync
@Configuration
public class ExecutorConfig {

    @Bean("simple")
    public Executor simple() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setThreadNamePrefix("thread-simple-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }

    @Bean("pool")
    public Executor threadPool() {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(processors + 1);
        executor.setMaxPoolSize(processors * 2);
        executor.setThreadNamePrefix("thread-pool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }
}