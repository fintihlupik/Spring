package es.cursosprhib.mediosdepago.modelo;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")

public class Tarjeta implements Comparable<Tarjeta>, Serializable{
	
	private Integer idTarjeta;
	private String pan;
	private String marca;
	private TipoTarjeta tipo;
	private Integer anyoVencimiento;
	private Integer mesVencimiento;
	private Cuenta cuenta;

	public Tarjeta() {
	}

	public Tarjeta(String pan, String marca, TipoTarjeta tipo, Integer anyoVencimiento, Integer mesVencimiento, Cuenta cuenta) {
		this.pan = pan;
		this.marca = marca;
		this.tipo = tipo;
		this.anyoVencimiento = anyoVencimiento;
		this.mesVencimiento = mesVencimiento;
		this.cuenta = cuenta;
	}

	public Integer getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Integer idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public TipoTarjeta getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarjeta tipo) {
		this.tipo = tipo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public Integer getAnyoVencimiento() {
		return anyoVencimiento;
	}

	public void setAnyoVencimiento(Integer anyoVencimiento) {
		this.anyoVencimiento = anyoVencimiento;
	}

	public Integer getMesVencimiento() {
		return mesVencimiento;
	}

	public void setMesVencimiento(Integer mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTarjeta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(idTarjeta, other.idTarjeta);
	}

	@Override
	public int compareTo(Tarjeta o) {
		return Integer.compare(this.idTarjeta, o.idTarjeta);
	}
}
