package negocio;

public interface BancoNegocio {
	
	void transferencia(long dni1, long dni2, double cantidad);
}
