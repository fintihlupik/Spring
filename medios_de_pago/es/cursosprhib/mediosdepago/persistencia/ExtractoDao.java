package es.cursosprhib.mediosdepago.persistencia;

import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;

//Implementar en la clase sólo los siguientes métodos:
//	findExtracto(...)

public interface ExtractoDao extends DaoGenerico<Extracto, Integer>{

	public Extracto findExtracto(Cuenta cuenta, int anyo, int mes);
}
