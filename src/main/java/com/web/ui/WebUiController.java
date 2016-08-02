package com.web.ui;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class WebUiController {

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("/hello")
	public void hello(){
		List<User> list = userService.searchAll();
		System.err.println("接收的数据="+list.toString());
		User user = list.get(0);
		
//		User user = userService.getUser();
		System.err.println("username="+user.getName());
	}
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate =   new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(WebUiController.class, args);
	}
}
