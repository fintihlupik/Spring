package es.cursosprhib.mediosdepago.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cursosprhib.p01mdp_modelo01.mediosdepago.modelo.Cliente;
import es.cursosprhib.p01mdp_modelo01.mediosdepago.modelo.Cuenta;
import es.cursosprhib.p01mdp_modelo01.mediosdepago.modelo.Tarjeta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TestMapeo {

	static EntityManagerFactory emf;
	static EntityManager em;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("test");
	} 

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("em");
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void testClientes() {
		Cliente cli = em.find(Cliente.class, 5);
		assertNull(cli);
		
		cli = em.find(Cliente.class, 22);
		assertEquals("30186195Y", cli.getNif());

	} 
	
	@Test
	void testClientesCuentas() {
		
		Cliente cli = em.find(Cliente.class, 22);
		
		List<Cuenta> cuentas = new ArrayList<>(cli.getCuentas());
		
		assertEquals(1, cuentas.size());
		
		assertEquals("ES6501308087414994805266", cuentas.get(0).getNroCuenta());
	} 
	
	@Test
	void testCuentas() {
		Cuenta cuenta = em.find(Cuenta.class, 200);
		assertNull(cuenta);
		
		cuenta = em.find(Cuenta.class, 73);
		assertNotNull(cuenta);
		
		assertEquals("ES4221006114656600290482", cuenta.getNroCuenta());
	}
	
	@Test
	void testCuentasTarjetas() {
		Cuenta cuenta = em.find(Cuenta.class, 73);
		Set<Tarjeta> tjetas = cuenta.getTarjetas();
		
		assertEquals(3, tjetas.size());
	}
}
