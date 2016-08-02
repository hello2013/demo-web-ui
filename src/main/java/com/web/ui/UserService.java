package com.web.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {
	
	@Autowired
	RestTemplate restTemplate; 

	final String SERVICE_NAME="demo-customer-service";  

//	@HystrixCommand(fallbackMethod = "fallbackSearchAll")
//	public List<String> searchAll() {
//		System.err.println("SERVICE_NAME="+SERVICE_NAME);
//		return restTemplate.getForObject("http://"+SERVICE_NAME+"/user",List.class);
//	}   

	
	public List<User> searchAll() {
//		System.err.println("SERVICE_NAME="+SERVICE_NAME);
		restTemplate.getMessageConverters();
		return restTemplate.getForObject("http://"+SERVICE_NAME+"/users",List.class);
	}   
	
	
	public User getUser(){
		return restTemplate.getForObject("http://"+SERVICE_NAME+"/user",User.class);
	}
	
	
	 private List<User> fallbackSearchAll() {

               System.out.println("HystrixCommand fallbackMethod handle!");

               List<User> ls = new ArrayList<User>();

               User user = new User();

               user.setName("TestHystrix");

               ls.add(user);
               return ls;
     }
}
