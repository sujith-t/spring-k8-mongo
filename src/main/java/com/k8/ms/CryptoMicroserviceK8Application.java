package com.k8.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Sujith T
 *
 * <!In God We Trust>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CryptoMicroserviceK8Application {

	public static void main(String[] args) {
		SpringApplication.run(CryptoMicroserviceK8Application.class, args);
	}

}
