package es.cursosprhib.mediosdepago.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import es.cursosprhib.mediosdepago.modelo.Cliente;
import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Movimiento;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;
import es.cursosprhib.mediosdepago.persistencia.ClienteDao;
import es.cursosprhib.mediosdepago.persistencia.ExtractoDao;

@SuppressWarnings("serial")

public class GeneraExtractoImpl implements GeneraExtracto{

	private ExtractoDao extDao;
	private ClienteDao cliDao;
	
	public GeneraExtractoImpl(ExtractoDao extDao, ClienteDao cliDao) {
		this.extDao = extDao;
		this.cliDao = cliDao;
	}

	@Override
	public List<Cliente> getClientes(String nombre) {
		if(nombre.equals("")) return cliDao.findAll();
		else return cliDao.findByNombre(nombre);
	}

	@Override
	public Extracto generaExtracto(Cuenta cuenta, int anyo, int mes) {
		return extDao.findExtracto(cuenta, anyo, mes);
	}
	
	@Override
	public Set<Tarjeta> getTarjetas(Extracto ext){
		System.out.println("getTarjetas");
		Set<Tarjeta> tjtas = new TreeSet<Tarjeta>();
		for (Movimiento mov : ext.getMovimientos()) {
			tjtas.add(mov.getTarjeta());
		}
		return tjtas;
	}
	
	@Override
	public List<Movimiento> getMovimientos(Extracto ext, Tarjeta tjta){
		List<Movimiento> movs = new ArrayList<>();
		for (Movimiento movimiento : ext.getMovimientos()) {
			if (movimiento.getTarjeta().equals(tjta)) {
				movs.add(movimiento);
			}
		}
		return movs;
	}

	@Override
	public Cliente getClienteConCuentas(Cliente c) {
		return this.getClienteConCuentas(c.getIdPersona());
	}
	
	@Override
	public Cliente getClienteConCuentas(int idCliente) {
		return cliDao.findByIdEager(idCliente);
	}
	

}
