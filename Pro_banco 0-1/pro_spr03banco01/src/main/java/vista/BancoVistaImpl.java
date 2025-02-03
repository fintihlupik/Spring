package vista;

import java.util.Scanner;

import negocio.BancoNegocio;

public class BancoVistaImpl implements BancoVista {

	private BancoNegocio bancoNegocio;

	public BancoVistaImpl() {
	}
	
	public BancoVistaImpl(BancoNegocio bancoNegocio) {
		this.bancoNegocio = bancoNegocio;
	}

	public void iniciarAplicacion() {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);

		System.out.println("TRANSFERENCIA");

		System.out.print("Dni destino: ");
		long dniDestino = Long.parseLong(teclado.nextLine());

		System.out.print("Dni origen: ");
		long dniOrigen = Long.parseLong(teclado.nextLine());

		System.out.print("Cantidad a transferir: ");
		double cantidad = Double.parseDouble(teclado.nextLine());

		System.out.println("----------- INICIO TRANSFERECIA ------");
		bancoNegocio.transferencia(dniDestino, dniOrigen, cantidad);
		System.out.println("----------- FIN TRANSFERECIA ------");
	}

	public BancoNegocio getBancoNegocio() {
		return bancoNegocio;
	}

	public void setBancoNegocio(BancoNegocio bancoNegocio) {
		this.bancoNegocio = bancoNegocio;
	}
}
