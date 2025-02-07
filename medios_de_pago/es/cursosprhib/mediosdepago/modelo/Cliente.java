package es.cursosprhib.mediosdepago.modelo;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")

public class Cliente extends PersonaFisica {
	
	private Integer nroCliente;
	private Set<Cuenta> cuentas;
	
	public Cliente() {
	}

	public Cliente(String nombre, String apellido1, String apellido2, String nif, Genero sexo, String municipio, String privincia, Integer nroCliente) {
		super(nombre, apellido1, apellido2, nif, sexo, municipio, privincia);
		this.nroCliente = nroCliente;
	}

	public Integer getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(Integer nroCliente) {
		this.nroCliente = nroCliente;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public void addCuenta(Cuenta cuenta) {
		if (cuentas == null) cuentas = new HashSet<Cuenta>();
		cuentas.add(cuenta);
	}
}
