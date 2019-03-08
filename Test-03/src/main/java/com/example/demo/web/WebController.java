package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 王超 by 2019-03-07
 */
@Controller
public class WebController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/page1")
    public String page1(){
        return "page1";
    }

    @GetMapping("/page2")
    public String page2(){
        return "page2";
    }

    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }

}
