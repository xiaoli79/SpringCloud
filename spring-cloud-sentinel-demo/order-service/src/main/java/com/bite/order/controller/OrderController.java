package com.bite.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.bite.order.model.OrderInfo;
import com.bite.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.UndeclaredThrowableException;



@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;




    @SentinelResource("/sentinel/id")
    @RequestMapping("/{orderId}")
    public OrderInfo getOrderById(@PathVariable("orderId") Integer orderId) {

//        try{
//            OrderInfo orderInfo = orderService.selectOrderById(orderId);
//            return orderInfo;
//        }catch(UndeclaredThrowableException e){
//
//            log.info("获取订单详情失败");
//            return new OrderInfo() ;
//        }


        return orderService.selectOrderById(orderId);
    }

    @RequestMapping("/read")
    public String read() {

        orderService.queryOrder();
        return "订单查询";
    }

    @RequestMapping("/write")
    public String write() {
        orderService.queryOrder();
        return "修改订单";
    }


}
