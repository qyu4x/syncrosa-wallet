package com.syncrosa.usersservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservices RESTful API Documentation",
				description = "Syncrosa Accounts microsrevices RESTful API Documentation",
				version = "v1.0.0",
				contact = @Contact(
						name = "Qirara",
						email = "qirara@gmail.com",
						url = "github.com/qyu4x"
				),
				license = @License(
						name = "Apache 2.0",
						url = "github.com/qyu4x"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Syncrosa Accounts microsrevices RESTful API External Documentation",
				url = "github.com/qyu4x"
		),
		servers = {
				@Server(
					url = "http://localhost:8080",
					description = "Local Server Accounts Services"
				),
				@Server(
						url = "http://api.syncrosa:80",
						description = "Production Server Accounts Services"
				)
		}
)
public class UsersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}

}
