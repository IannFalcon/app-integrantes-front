package pe.edu.cibertec.integrantes_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IntegrantesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrantesAppApplication.class, args);
	}

}
