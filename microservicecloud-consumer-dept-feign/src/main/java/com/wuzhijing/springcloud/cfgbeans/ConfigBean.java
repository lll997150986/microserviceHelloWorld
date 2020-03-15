package com.wuzhijing.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean{
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate ()
    {
         return new RestTemplate();
    }
//    配置ribbon负载均衡策略--随机
//    @Bean
//    public IRule myRule() {
//    	return new RandomRule();
//    }
}