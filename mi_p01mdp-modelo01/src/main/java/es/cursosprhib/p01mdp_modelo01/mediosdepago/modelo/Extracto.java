package es.cursosprhib.p01mdp_modelo01.mediosdepago.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("serial")

public class Extracto implements Comparable<Extracto>, Serializable{
	
	private Integer idExtracto;
	private Integer anyo;
	private Integer mes;
	private Cuenta cuenta;
	private Set<Movimiento> movimientos;

	public Extracto() {
	}

	public Extracto(Integer anyo, Integer mes, Cuenta cuenta) {
		this.anyo = anyo;
		this.mes = mes;
		this.cuenta = cuenta;
	}

	public Integer getIdExtracto() {
		return idExtracto;
	}

	public void setIdExtracto(Integer idExtracto) {
		this.idExtracto = idExtracto;
	}

	public String getNumeroExtracto() {
		return cuenta.getNroCuenta() + this.anyo + this.mes;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Set<Movimiento> getMovimientos() {
		return new TreeSet<Movimiento>(movimientos);
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	public void addMovimiento(Movimiento mov) {
		if(movimientos == null) movimientos = new HashSet<Movimiento>();
		movimientos.add(mov);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idExtracto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extracto other = (Extracto) obj;
		return Objects.equals(idExtracto, other.idExtracto);
	}

	@Override
	public int compareTo(Extracto o) {
		return Integer.compare(this.idExtracto, o.idExtracto);
	}

	@Override
	public String toString() {
		return "Extracto [" + idExtracto + ", " + anyo + ", " + mes + ", " + cuenta + "]";
	}
}
