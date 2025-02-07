package es.cursosprhib.mediosdepago.servicio;

import java.io.Serializable;

import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;

public interface Compra extends Serializable{

	public Extracto buscarExtractoTarjeta(Tarjeta tjta, int anyo, int mes);
	
}
