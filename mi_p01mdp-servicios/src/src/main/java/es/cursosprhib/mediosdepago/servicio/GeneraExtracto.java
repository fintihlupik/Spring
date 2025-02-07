package es.cursosprhib.mediosdepago.servicio;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import es.cursosprhib.mediosdepago.modelo.Cliente;
import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Movimiento;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;

public interface GeneraExtracto extends Serializable{

	// Debera retornar los clientes donde "nombre" este contenido en su nombre, apellido2 o apellido2
	// No debera cargar su coleccion de cuentas
	public List<Cliente> getClientes(String nombre);
	
	// Debera retornar el extracto correspondiente a esa cuenta de ese año y ese mes
	// El extracto debera contener los movimientos con la/las tarjetas de la cuenta
	public Extracto generaExtracto(Cuenta cuenta, int anyo, int mes);
	
	// Debera retornar el cliente con sus cuentas cargadas. No es necesario que las cuentas tengan sus tarjetas
	public Cliente getClienteConCuentas(int idCliente);
	
	// Igual que el anterior
	// AYUDA: utilizar el anterior
	public Cliente getClienteConCuentas(Cliente c);
	
	// No hace falta
	public Set<Tarjeta> getTarjetas(Extracto ext);
	
	// Debera retornar una lista con los movimientos del extracto pero solo de la tarjeta indicada
	// AYUDA: puede extraerse del extracto recibido, no hace falta volver a los daos
	public List<Movimiento> getMovimientos(Extracto ext, Tarjeta tjta);
}