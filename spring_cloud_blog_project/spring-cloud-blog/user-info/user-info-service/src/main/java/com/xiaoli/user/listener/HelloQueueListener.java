package com.xiaoli.user.listener;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloQueueListener {

//  从哪个队列进行接收消息~~
    @RabbitListener(queues = "Hello")
    public void handler(Message message){
        System.out.println("收到消息:"+message);
    }

}
