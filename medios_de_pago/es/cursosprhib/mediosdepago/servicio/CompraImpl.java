package es.cursosprhib.mediosdepago.servicio;

import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;
import es.cursosprhib.mediosdepago.persistencia.ExtractoDao;

@SuppressWarnings("serial")


public class CompraImpl implements Compra{

	private ExtractoDao extDao;
	
	public CompraImpl(ExtractoDao extDao) {
		this.extDao = extDao;
	}

	@Override
	public Extracto buscarExtractoTarjeta(Tarjeta tjta, int anyo, int mes) {
		System.out.println(this.getClass().getName());
		return extDao.findExtracto(tjta.getCuenta(), anyo, mes);
	}

}
