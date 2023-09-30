package fun.icpc.iris.irisonlinejudge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor dockerRunnerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);

        executor.initialize();
        return executor;
    }

    @Bean
    public ThreadPoolTaskExecutor dockerDestroyerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);

        executor.initialize();
        return executor;
    }

}
