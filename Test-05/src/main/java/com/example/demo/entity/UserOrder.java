package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 王超 by 2019-03-12
 */
@Entity
public class UserOrder implements Delayed {

    /**
     * 订单主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单生成时间
     */
    private Long createTime;
    /**
     * 订单过期时间
     */
    private Long expireTime;
    /**
     *  0 - 未支付
     *  1 - 已支付
     * -1 - 已关闭
     */
    private int activeOrder;

    @Override
    public long getDelay(TimeUnit unit) {
        return this.expireTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return this.expireTime.compareTo(((UserOrder) o).expireTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public int getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(int activeOrder) {
        this.activeOrder = activeOrder;
    }
}
