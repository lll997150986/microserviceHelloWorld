package com.wuzhijing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient//服务发现
@SpringBootApplication
@EnableEurekaClient //启动后会注册进eureka服务
public class DeptProvider8002_App{
  public static void main(String[] args){
   SpringApplication.run(DeptProvider8002_App.class, args);
  }
}
