package es.cursosprhib.mediosdepago.persistencia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cursosprhib.mediosdepago.config.PersistenciaConfig;

@SpringBootTest(classes = PersistenciaConfig.class)
class TestSpringData {
	
	@Autowired
	ClienteDao cliDao;
	@Autowired
	CuentaDao ctaDao;
	

	@Test
	void contextLoads() {
		System.out.println(cliDao.findById(261));
		System.out.println(ctaDao.findById(37));
		System.out.println(cliDao.findByNif("32722044J"));
		cliDao.findByNombre("pe").forEach(System.out::println); // esto es una lambda
		System.out.println(cliDao.findByIdEager(29).getCuentas());
		System.out.println(cliDao.findById(73).get().getCuentas());
	}

}
