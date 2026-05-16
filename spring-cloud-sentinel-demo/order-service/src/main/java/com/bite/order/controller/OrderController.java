package com.bite.order.controller;

import com.bite.order.model.OrderInfo;
import com.bite.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/{orderId}")
    public OrderInfo getOrderById(@PathVariable("orderId") Integer orderId) {
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
