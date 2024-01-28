package fr.limayrac.securite2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication
public class securite2Application {

	public static void main(String[] args) {
		SpringApplication.run(securite2Application.class, args);
	}
}
