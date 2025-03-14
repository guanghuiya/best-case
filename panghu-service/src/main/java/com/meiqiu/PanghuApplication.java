package com.meiqiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.meiqiu.feign")
public class PanghuApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanghuApplication.class, args);
    }

}
