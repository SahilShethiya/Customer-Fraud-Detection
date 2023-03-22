package com.myproject.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                            "com.myproject.customer",
                            "com.myproject.amqp",
                    }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.myproject.clients"
)
public class CustomerApplication {
    public static void main(String args[]){
        SpringApplication.run(CustomerApplication.class, args);
    }
}
