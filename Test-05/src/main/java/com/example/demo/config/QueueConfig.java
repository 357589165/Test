package com.example.demo.config;

import com.example.demo.dao.OrderRepository;
import com.example.demo.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * @author 王超 by 2019-03-13
 */
@Configuration
public class QueueConfig {

    @Autowired
    OrderRepository orderRepository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @PostConstruct
    public void doOrderQueue(){
        new Thread(() -> {
            List<UserOrder> orderList = orderRepository.findAllByActiveOrderIs(0);
            delayQueue().addAll(orderList);

            while (true){
                UserOrder userOrder = null;
                try {
                    userOrder = this.delayQueue().take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(userOrder == null) continue;
                if(userOrder.getActiveOrder() == 0){
                    userOrder.setActiveOrder(-1);
                    orderRepository.save(userOrder);
                    System.out.println("关闭订单ID: " + userOrder.getId() + "   关闭时间: " + simpleDateFormat.format(userOrder.getExpireTime()));
                }
            }
        }).start();
    }


    @Bean
    public DelayQueue<UserOrder> delayQueue(){
        return new DelayQueue<>();
    }

}
