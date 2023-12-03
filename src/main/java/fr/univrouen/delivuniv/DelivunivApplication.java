package fr.univrouen.delivuniv;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Delivery Person API", version = "2.0", description = "Delivery Person API"))
public class DelivunivApplication {

	public static void main(String[] args) {
		SpringApplication.run(DelivunivApplication.class, args);
	}

}
