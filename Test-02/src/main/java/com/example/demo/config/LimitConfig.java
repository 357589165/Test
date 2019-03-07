package com.example.demo.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王超 by 2019-03-05
 */
@Configuration
public class LimitConfig {

    @Bean
    public RateLimiter rateLimiter(){
        return RateLimiter.create(0.5);
    }

}
