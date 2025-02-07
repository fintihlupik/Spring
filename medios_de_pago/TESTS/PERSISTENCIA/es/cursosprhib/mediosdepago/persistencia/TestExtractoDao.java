package es.cursosprhib.mediosdepago.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.persistencia.jpa.ExtractoDaoImpl;

class TestExtractoDao {

	private static ExtractoDaoImpl eDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		eDao = new ExtractoDaoImpl(EMFactory.getEmf());
	}

	@Test
	void testFindExtracto() {
		Cuenta c = new Cuenta();
		c.setIdCuenta(2);
		assertNull(eDao.findExtracto(c, 2024, 5));
		assertNotNull(eDao.findExtracto(c, 2020, 7));
		assertEquals(288, eDao.findExtracto(c, 2020, 7).getIdExtracto());
	}

}
