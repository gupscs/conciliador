package br.silveira.conciliador.integrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages="br.silveira.conciliador")
public class MktplaceIntegratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MktplaceIntegratorApplication.class, args);
	}

}
