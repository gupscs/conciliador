package br.silveira.conciliador.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class AdminServerApplication {

	public static void main(String[] args) {
		//TODO IMPLEMENTAR A SEGURANÇA NO ACESSO DO CONSOLE E TAMBÉM AS NOTIFICAÇÕES POR EMAIL OU OUTRA PLATAFORMA
		SpringApplication.run(AdminServerApplication.class, args);
	}

}
