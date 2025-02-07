package es.cursosprhib.mediosdepago.vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.cursosprhib.mediosdepago.modelo.Cliente;
import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Movimiento;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;
import es.cursosprhib.mediosdepago.servicio.GeneraExtracto;

@Component
@Profile({"default"})
public class ConsultaExtracto {

	private GeneraExtracto neg ;
	
	public ConsultaExtracto(GeneraExtracto neg) {
		this.neg = neg;
	}

	public  void pantallaExtracto() {
		
		@SuppressWarnings("resource")
		Scanner tec = new Scanner(System.in);
		List<Cliente> clientes;
		
		System.out.println("EXTRACTOS DE CLIENTES\n");
		boolean ok;
		do {
			ok = true;
			System.out.println("Busqueda Clientes");
			System.out.print("Ingrese cliente a buscar: ");
			String cliBusca = tec.nextLine();
			clientes = neg.getClientes(cliBusca);
			if (clientes == null || clientes.size() == 0) {
				System.out.println("--- No existen clientes con ese criterio de busqueda ---");
				ok = false;
			}
		} while(!ok);
		
		int orden = 0;
		for (Cliente cli : clientes) {
			System.out.printf("%2d - %s\n", ++orden, cli.getNombreYApellidos());
		}
		
		List<Cuenta> cuentas;
		int opcion;
		do {
			ok = true;
			System.out.print("Seleccione cliente: ");
			opcion = tec.nextInt();
			if (opcion < 1 || opcion > clientes.size()) {
				System.out.println("--- Seleccion incorrecta ---");
				cuentas = null;
				ok = false;
			} else {
				Cliente seleccionado = neg.getClienteConCuentas(clientes.get(opcion-1));
				cuentas = new ArrayList<>(seleccionado.getCuentas());
			}
		} while(!ok);
		
		System.out.println();
		orden = 0;
		if (cuentas.size() > 1) {
			System.out.println("Cuentas del cliente");
			for (Cuenta cuenta : cuentas) {
				System.out.printf("%2d - %s\n", ++orden, cuenta);
			}
			System.out.print("Seleccione cuenta: ");
			opcion = tec.nextInt();
		} else {
			opcion = 1;
		}
		
		Cuenta elegida = cuentas.get(opcion-1); 
		
		System.out.print("Ingrese año: ");
		int anyo = tec.nextInt();
		System.out.print("Ingrese mes: ");
		int mes = tec.nextInt();
		
		String tituloPrincipal = "\n\nExtracto mensual cuenta: " + elegida;
		String nombre = elegida.getCliente().getNombreYApellidos();
		
		System.out.println(tituloPrincipal);
		System.out.println("Año " + anyo);
		System.out.println("Mes " + mes);
		System.out.println(nombre);
		System.out.println();

		Extracto ext = neg.generaExtracto(elegida, 2020, mes);

		if (ext != null) {
			Calendar c = Calendar.getInstance();
			c.set(ext.getAnyo(), ext.getMes()-1, 1);
			
			String nombreMes = new SimpleDateFormat("MMMM").format(c.getTime());
			
			String titulo = "*** Mes: " + nombreMes + " de " + ext.getAnyo() + " ***";
			
			System.out.println(titulo);
			
			
			Set<Tarjeta> tarjetas = neg.getTarjetas(ext); 
			
			double totalExtracto = 0;
			for (Tarjeta tjta : tarjetas) {
				double totalTarjeta = 0;
				System.out.println("   Movimientos tarjeta: " + tjta.getPan());

				List<Movimiento> movimientos = neg.getMovimientos(ext, tjta);
				for (Movimiento movs : movimientos) {
					System.out.printf("     %-12s%25s%,10.2f\n", movs.getFechaFormat(), movs.getProveedor(), movs.getImporte());
					totalTarjeta += movs.getImporte();
				}
				System.out.printf("   %s%,10.2f\n\n", "Total " + tjta.getPan() + ": € ", totalTarjeta);
				totalExtracto += totalTarjeta;
			}
			System.out.printf("  %s%,10.2f\n\n", "Total: € ", totalExtracto);
		} else {
			System.out.println("--- SIN MOVIMIENTOS ---");
		}
	}
}
