package es.cursosprhib.mediosdepago.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;
import es.cursosprhib.mediosdepago.persistencia.ExtractoDao;

@SuppressWarnings("serial")

@Service
public class CompraImpl implements Compra{

	private ExtractoDao extDao;
	
	@Autowired
	public CompraImpl(ExtractoDao extDao) {
		this.extDao = extDao;
	}

	@Override
	public Extracto buscarExtractoTarjeta(Tarjeta tjta, int anyo, int mes) {
		return extDao.findExtracto(tjta.getCuenta(), anyo, mes);
	}

}
