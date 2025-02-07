package es.cursosprhib.mediosdepago.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {
		"es.cursosprhib.mediosdepago.vista",
		"es.cursosprhib.mediosdepago.vista.swing",
		"es.cursosprhib.mediosdepago.servicio", 
		"es.cursosprhib.mediosdepago.persistencia"})
public class AppConfig {

	@Bean
	@Profile("tests")
	public EntityManagerFactory emfTest() {
		Map<String, Object> prop = new HashMap<>();

		prop.put("jakarta.persistence.jdbc.user", "root");
		prop.put("jakarta.persistence.jdbc.password", "root");
		prop.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
		prop.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/medios_de_pago");

		return Persistence.createEntityManagerFactory("mediosdepago", prop);
	}

	@Bean
	@Profile({"produccion", "default"})
	public EntityManagerFactory emfProd() {
		Map<String, Object> prop = new HashMap<>();
		
		prop.put("jakarta.persistence.jdbc.user", "root");
		prop.put("jakarta.persistence.jdbc.password", "root");
		prop.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
		prop.put("jakarta.persistence.jdbc.url", "jdbc:mysql://172.17.0.3:3306/medios_de_pago");
		
		return Persistence.createEntityManagerFactory("mediosdepago", prop);
	}
	
}
