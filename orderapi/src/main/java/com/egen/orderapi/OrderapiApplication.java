package com.egen.orderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class OrderapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderapiApplication.class, args);
	}

}
