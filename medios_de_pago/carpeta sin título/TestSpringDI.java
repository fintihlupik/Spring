package es.cursosprhib.mediosdepago.persistencia;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.cursosprhib.mediosdepago.modelo.Cuenta;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MdpConfig.class)
public class TestSpringDI {

	@Autowired
	private ClienteDao cDao;

	@Autowired
	private ExtractoDao eDao;
	
	@Test
	void testClienteDao() {
		assertTrue(cDao.findAll().size() > 1);
	}
	
	@Test
	void testExtractoDao() {
		Cuenta c = new Cuenta();
		c.setIdCuenta(2);
		assertNotNull(eDao.findExtracto(c, 2020, 7));
	}
}

