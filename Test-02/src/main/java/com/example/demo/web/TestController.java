package com.example.demo.web;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王超 by 2019-03-05
 */
@RestController
public class TestController {

    @Autowired
    RateLimiter rateLimiter;

    @GetMapping("/lock")
    public String lock(){
        return rateLimiter.tryAcquire() ? "访问成功" : "访问失败";
    }

}
