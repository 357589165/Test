package com.example.demo.dao;

import com.example.demo.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王超 by 2019-03-13
 */
@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Integer> {

    List<UserOrder> findAllByActiveOrderIs(int activeOrder);

}
