package com.wuzhijing.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wuzhijing.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component//不能忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable cause) {
		 
		return new DeptClientService() {
			
			@Override
			public List<Dept> list() {
				return null;
			}
			
			@Override
			public Dept get(long id) {
				return new Dept().setDeptno(id).setDname("该ID" + id + "没有对应的信息，null--@HystrixCommand,consumer客户端提供的降级信息：服务已关闭")
						  .setDb_source("no this database in mysql!");
			}
			
			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}
	
	

}
