package com.example.demo.web;

import com.example.demo.dao.OrderRepository;
import com.example.demo.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * @author 王超 by 2019-03-13
 */
@RestController
public class WebController {

    @Autowired
    DelayQueue<UserOrder> delayQueue;

    @Autowired
    OrderRepository orderRepository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @GetMapping
    public String insert(){
        int i = 0;
        do {
            UserOrder userOrder = this.createOrder((int) (1 + Math.random() * 20));
            orderRepository.save(userOrder);
            i++;
            delayQueue.add(userOrder);
            System.out.println("订单编号：" + userOrder.getId() + "    到期时间：" + simpleDateFormat.format(userOrder.getExpireTime()));
        }while (i < 5);

        return null;
    }


    private UserOrder createOrder(int timeMillis){
        UserOrder userOrder = new UserOrder();
        userOrder.setActiveOrder(0);
        Long createTime = System.currentTimeMillis();
        userOrder.setCreateTime(createTime);
        userOrder.setExpireTime(createTime + timeMillis * 1000);
        return userOrder;
    }



}
