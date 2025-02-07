package es.cursosprhib.mediosdepago.servicio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TestCompraSpring {
	
	@Autowired
	private Compra compra;
	
	@Test
	void testBuscarExtractoTarjeta() {
		Cuenta c = new Cuenta();
		c.setIdCuenta(10);
		Tarjeta tj = new Tarjeta();	
		tj.setIdTarjeta(13);
		tj.setCuenta(c);
		Extracto ext = compra.buscarExtractoTarjeta(tj,	2020, 5);
		assertNotNull(ext);
	}
}
