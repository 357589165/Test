package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * @author 王超 by 2019-03-07
 */
@Controller
public class SSEController {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    @GetMapping("/sseGet")
    public void sseGet(HttpServletResponse response) throws IOException {
        // 设置response 信息
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        // 获取输出流
        PrintWriter write = response.getWriter();
        // 进行循环传输，如出现异常则手动关闭输出流
        try{
            for(;;){
                Thread.sleep(1000);
                write.append("retry:2000").append("\n");
                write.append("data:")
                        .append("当前服务器时间：")
                        .append(simpleDateFormat.format(System.currentTimeMillis()))
                        .append("\n\n");
                write.flush();
            }
        }catch (Exception e){
            write.close();
        }
    }



}
