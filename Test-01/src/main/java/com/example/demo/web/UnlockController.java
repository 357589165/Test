package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王超 by 2019-03-04
 */
@RestController
public class UnlockController {

    @GetMapping("/unlock")
    public String unlock(){
        return "访问成功";
    }

}
