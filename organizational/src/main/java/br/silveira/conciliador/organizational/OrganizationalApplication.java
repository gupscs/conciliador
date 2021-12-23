package br.silveira.conciliador.organizational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrganizationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationalApplication.class, args);
	}

}
