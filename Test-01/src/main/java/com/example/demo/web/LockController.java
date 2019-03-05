package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王超 by 2019-03-04
 */
@RestController
public class LockController {

    @GetMapping("/lock")
    public String lockTest(){
        return "访问成功";
    }

}
