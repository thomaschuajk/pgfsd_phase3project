package com.bookcabcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories
@SpringBootApplication
public class SpringCloudBookcabsdemoBookcabsMicrosvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudBookcabsdemoBookcabsMicrosvcApplication.class, args);
	}

}
