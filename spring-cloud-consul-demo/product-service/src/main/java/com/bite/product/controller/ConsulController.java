package com.bite.product.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RefreshScope
@RestController
@RequestMapping("/consul")
public class ConsulController {


    @Value("${service-name}")
    private String serviceName;

    @Value("${output.info}")
    private String outputInfo;

    @RequestMapping("/getConsulConfig")
    public String getConsulConfig(){
        return String.format("从consul中获取配置信息,serviceName:%s,outputInfo:%s",serviceName,outputInfo);
    }
}
