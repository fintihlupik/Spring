package es.cursosprhib.mediosdepago.persistencia;

import java.util.Map;

import es.cursosprhib.mediosdepago.modelo.Cliente;

public interface ClienteDaoCustom {
	
	Map<String,Cliente> getClientesMapNif();

}
