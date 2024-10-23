package pe.edu.cibertec.integrantes_app.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplateIntegrante(RestTemplateBuilder builder) {
        return builder
                .rootUri("https://app-integrantes-back-fvh5aadqg0fbeka8.brazilsouth-01.azurewebsites.net/integrante")
                .setConnectTimeout(Duration.ofSeconds(5)) // Tiempo de espera maximo para establecer la conexión
                .setReadTimeout(Duration.ofSeconds(10)) // Tiempo de espera maximo para recibir la respuesta total
                .build();
    }

}
