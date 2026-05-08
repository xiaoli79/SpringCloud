package com.xiaoli.user.listener;



import com.rabbitmq.client.Channel;
import com.xiaoli.user.dataobject.UserInfo;
import common.xiaoli.common.costant.Constants;
import common.xiaoli.common.utils.JsonUtil;
import common.xiaoli.common.utils.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class UserQueueListener {


    @Autowired
    private Mail mail;


//    @RabbitListener(queues = Constants.USER_QUEUE_NAME)
//    public void handler(Message message, Channel channel) throws IOException {
////      deleverTag 用来获取信息ID，为接下来的确认做准备！！
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try{
//            String body = new String(message.getBody());
//            log.info("收到用户信息，body{}",body);
//
//            //TODO     发送邮件
//
//
//            channel.basicAck(deliveryTag,true);
//
//        }catch (Exception e){
//            channel.basicNack(deliveryTag,true,true);
//            log.error("邮件发送失败");
//
//        }
//
//    }

    @RabbitListener(bindings =@QueueBinding(
            value = @Queue(value = Constants.USER_QUEUE_NAME,durable = "true"),
            exchange = @Exchange(value = Constants.USER_EXCHANGE_NAME,type = ExchangeTypes.FANOUT)
    ))
    public void handler(Message message, Channel channel) throws IOException {
//      deleverTag 用来获取信息ID，为接下来的确认做准备！！
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            String body = new String(message.getBody());
            log.info("收到用户信息，body{}",body);

            //TODO 给用户发邮件
            UserInfo userInfo = JsonUtil.parseJson(body,UserInfo.class);
            String content = new StringBuilder()
                    .append("尊敬的").append(userInfo.getUserName()).append("，您好！<br/>")
                    .append("感谢您注册成为我们博客社区的一员！我们很高兴您决定加入我们的大家庭。<br/>")
                    .append("您的注册信息如下：<br/>")
                    .append("用户名：").append(userInfo.getUserName()).append("<br/>")
                    .append("为了确保您的账户安全，请妥善保管您的登录信息。如果使用过程中，遇到任何问题，欢迎联系我们的支持团队。3099592185@qq.com <br/>")
                    .append("再次感谢您的加入，我们期待看到您的精彩内容！<br/>")
                    .append("最好的祝愿<br/>")
                    .append("肥波博客团队").toString();
            mail.send(userInfo.getEmail(), "欢迎加入肥波博客社区", content);
            channel.basicAck(deliveryTag,true);
        }catch (Exception e){
            channel.basicNack(deliveryTag,true,true);
            log.error("邮件发送失败,e",e);

        }
    }



}
