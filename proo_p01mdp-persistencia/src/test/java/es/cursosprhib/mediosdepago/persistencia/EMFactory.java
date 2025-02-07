package es.cursosprhib.mediosdepago.persistencia;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFactory {
	private static EntityManagerFactory emf;
	
	private EMFactory() {}
	
	public static EntityManagerFactory getEmf() {
		if(emf == null) {
			Map<String, Object> prop = new HashMap<>();

			prop.put("javax.persistence.jdbc.user", "root");
			prop.put("javax.persistence.jdbc.password", "root");
			prop.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
			prop.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/medios_de_pago");
			prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

			emf = Persistence.createEntityManagerFactory("mediosdepago", prop);
		}
		return emf;
	}
}
