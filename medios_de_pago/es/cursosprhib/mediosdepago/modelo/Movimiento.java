package es.cursosprhib.mediosdepago.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("serial")

public class Movimiento implements Comparable<Movimiento>, Serializable{
	
	private Integer idMovimiento;
	private Extracto extracto;
	private TipoMovimiento tipo;
	private Tarjeta tarjeta;
	private Date fecha;
	private Double importe;
	private String proveedor;
	
	public Movimiento() {
	}

	public Movimiento(TipoMovimiento tipo, Tarjeta tarjeta, Date fecha, double importe, String proveedor, Extracto extracto) {
		this.tipo = tipo;
		this.tarjeta = tarjeta;
		this.fecha = fecha;
		this.importe = importe;
		this.proveedor = proveedor;
		this.extracto = extracto;
	}
	
	public Movimiento(TipoMovimiento tipo, Tarjeta tarjeta, int dia, int mes, int anyo, double importe, String proveedor, Extracto extracto) {
		this(tipo, tarjeta, null, importe, proveedor, extracto);
		LocalDate ld = LocalDate.of(anyo, mes, dia);
		fecha = java.sql.Date.valueOf(ld);
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Extracto getExtracto() {
		return extracto;
	}

	public void setExtracto(Extracto extracto) {
		this.extracto = extracto;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getFechaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setFecha(int anyo, int mes, int dia) {
		fecha = java.sql.Date.valueOf(LocalDate.of(anyo, mes, dia));
	}
	
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMovimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return Objects.equals(idMovimiento, other.idMovimiento);
	}

	@Override
	public int compareTo(Movimiento o) {
		if(this.equals(o)) return 0;
		else return this.fecha.compareTo(o.fecha);
	}
}
