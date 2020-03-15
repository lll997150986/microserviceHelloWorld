package com.wuzhijing.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.wuzhijing.springcloud.entities.Dept;
import com.wuzhijing.springcloud.service.DeptService;


@RestController

public class DeptController

{

	@Autowired
  private DiscoveryClient client;//springframework包下
	@Autowired
  private DeptService service;


  @PostMapping("/dept/add")
  public boolean add(@RequestBody Dept dept){
   return service.add(dept);
  }

  @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
//  一旦调用方法失败并且抛出错误信息后，会自动调用标注好的@HystrixCommand标注的fallback
  @HystrixCommand(fallbackMethod = "processHystrix_Get")
  public Dept get(@PathVariable("id") Long id)  {
	  Dept dept = service.get(id);
	  if(null == dept) {
		  throw new RuntimeException("该ID" + id + "没有对应的信息");
	  }
	  
   return dept;
  }
  
  public Dept processHystrix_Get(@PathVariable("id")Long id) {
	  return new Dept().setDeptno(id).setDname("该ID" + id + "没有对应的信息，null--@HystrixCommand")
			  .setDb_source("no this database in mysql");
  }

  @RequestMapping(value="/dept/list",method=RequestMethod.GET)
  public List<Dept> list()  {
   return service.list();
  }
  
  @GetMapping("/dept/discovery")
  public Object dicovery() {
	  List<String> list = client.getServices();
	  System.out.println("******"+list);
	  List<ServiceInstance>srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
	  for(ServiceInstance instance: srvList) {
		  System.out.println(instance.getServiceId() + "\t" + instance.getHost()+"\t"+ instance.getPort()+"\t"+instance.getUri());;
	  }
	  return this.client;
  }
  

}

 