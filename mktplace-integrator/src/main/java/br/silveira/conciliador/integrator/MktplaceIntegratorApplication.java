package br.silveira.conciliador.integrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages="br.silveira.conciliador")
public class MktplaceIntegratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MktplaceIntegratorApplication.class, args);
	}

}
