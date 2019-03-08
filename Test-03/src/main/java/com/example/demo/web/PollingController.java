package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

/**
 * @author 王超 by 2019-03-07
 */
@Controller
public class PollingController {

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @GetMapping("/pollingGet")
    @ResponseBody
    public String pollingGet(){
        return "当前服务器时间：" + simpleDateFormat.format(System.currentTimeMillis());
    }

}
