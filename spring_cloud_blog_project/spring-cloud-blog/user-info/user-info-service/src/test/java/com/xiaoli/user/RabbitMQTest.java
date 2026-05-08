package com.xiaoli.user;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    void send(){
//        routingKey 为交换机名称     object:为消息 ~   exchange：为交换机名称~~
        rabbitTemplate.convertAndSend("","Hello","Hello World");
    }







}
