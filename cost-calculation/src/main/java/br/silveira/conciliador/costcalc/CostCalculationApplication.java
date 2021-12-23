package br.silveira.conciliador.costcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients(basePackages="br.silveira.conciliador")
@SpringBootApplication
public class CostCalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostCalculationApplication.class, args);
	}

}
