package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

/**
 * @author 王超 by 2019-03-07
 */
@Component
public class LongPollingUtils {

    @Autowired
    SimpleDateFormat simpleDateFormat;

    private String dataUpdateTime = null;

    @PostConstruct
    public void init(){
        new Thread(() -> {
            for(;;){
                dataUpdateTime = "数据最后更新时间:" + simpleDateFormat.format(System.currentTimeMillis());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String getDataUpdateTime() {
        String result = this.dataUpdateTime;
        this.dataUpdateTime = null;
        return result;
    }
}
