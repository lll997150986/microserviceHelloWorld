package com.wuzhijing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

//配置feign
@EnableFeignClients(basePackages = {"com.wuzhijing.springcloud"})
@ComponentScan("com.wuzhijing.springcloud")

@SpringBootApplication
@EnableEurekaClient
//自定义ribbon配置类
//@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class)
public class DeptConsumer80_Feign_App{
  public static void main(String[] args){
   SpringApplication.run(DeptConsumer80_Feign_App.class, args);
  }
}

 

 