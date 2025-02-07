package es.cursosprhib.mediosdepago.persistencia;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
@ComponentScan("es.cursosprhib.mediosdepago.persistencia")
public class TestConfig {
	
	@Bean
	public EntityManagerFactory emf() {
		Map<String, Object> prop = new HashMap<>();

		prop.put("javax.persistence.jdbc.user", "root");
		prop.put("javax.persistence.jdbc.password", "root");
		prop.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
		prop.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/medios_de_pago");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

		return Persistence.createEntityManagerFactory("mediosdepago", prop);

	}
}
