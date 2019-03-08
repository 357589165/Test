package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 王超 by 2019-03-07
 */
@Component
public class LongPollingUtils {

    @Autowired
    SimpleDateFormat simpleDateFormat;

    private DeferredResult<String> deferredResult = null;

    @PostConstruct
    public void init(){
        new Thread(() -> {
            String result = "服务器当前刷新时间:" + simpleDateFormat.format(System.currentTimeMillis());
            for (;;){
                if(this.deferredResult != null){
                    this.deferredResult.setResult(result);
                }
                sleep();
            }
        }).start();
    }

    private void sleep(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addDeferredResult(DeferredResult<String> deferredResult){
        this.deferredResult = deferredResult;
    }

}
