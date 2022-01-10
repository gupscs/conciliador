package br.silveira.conciliador.organizational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages="br.silveira.conciliador")
public class OrganizationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationalApplication.class, args);
	}

}
