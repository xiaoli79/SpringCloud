package com.xiaoli.user.config;


import common.xiaoli.common.costant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("helloBean")
    public Queue queue() {
        return QueueBuilder.durable("Hello").build();
    }


//   第一种写法
////  队列
//    @Bean("userQueue")
//    public Queue userQueue() {
//        return QueueBuilder.durable(Constants.USER_QUEUE_NAME).build();
//    }
//
////  交换机
//    @Bean("userExchange")
//    public FanoutExchange userExchange() {
//        return ExchangeBuilder.fanoutExchange(Constants.USER_EXCHANGE_NAME).durable(true).build();
//    }
//
////  动态绑定~
//    @Bean("userBinding")
//    public Binding userBinding(@Qualifier("userQueue") Queue userQueue,@Qualifier("userExchange") FanoutExchange userExchange) {
//        return BindingBuilder.bind(userQueue).to(userExchange);
//    }


}
