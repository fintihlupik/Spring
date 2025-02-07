package es.cursosprhib.mediosdepago.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TestEMF {

	static EntityManagerFactory emf;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("test");
	}

	
	@Test
	void testEntityManager() {
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		em.close();
		emf.close();
	}

}
