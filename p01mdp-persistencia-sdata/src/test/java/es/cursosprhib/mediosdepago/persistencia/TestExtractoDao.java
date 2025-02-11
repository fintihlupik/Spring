package es.cursosprhib.mediosdepago.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cursosprhib.mediosdepago.config.PersistenciaConfig;
import es.cursosprhib.mediosdepago.modelo.Cuenta;

@SpringBootTest(classes = PersistenciaConfig.class)
class TestExtractoDao {

	@Autowired
	private ExtractoDao eDao;
	
	
	@Test
	void testFindExtracto() {
		Cuenta c = new Cuenta();
		c.setIdCuenta(2);
		assertNull(eDao.findExtracto(c, 2024, 5));
		assertNotNull(eDao.findExtracto(c, 2020, 7));
		assertEquals(288, eDao.findExtracto(c, 2020, 7).getIdExtracto());
	}

}
