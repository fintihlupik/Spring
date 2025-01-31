package es.cursosprhib.mediosdepago.modelo;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")

public class TipoMovimiento implements Comparable<TipoMovimiento>, Serializable{
	
	private Integer idTipoMovimiento;
	private String tipoMovimiento;
	
	public TipoMovimiento() {
	}

	public TipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoMovimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoMovimiento other = (TipoMovimiento) obj;
		return Objects.equals(idTipoMovimiento, other.idTipoMovimiento);
	}

	@Override
	public int compareTo(TipoMovimiento o) {
		return Integer.compare(this.idTipoMovimiento, o.idTipoMovimiento);
	}

}
