package br.silveira.conciliador.sysadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SysadminApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SysadminApplication.class, args);
	}
}
