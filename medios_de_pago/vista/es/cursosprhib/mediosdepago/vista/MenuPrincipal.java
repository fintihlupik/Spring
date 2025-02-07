package es.cursosprhib.mediosdepago.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Profile({"default", "consola"})
public class MenuPrincipal {

	private ConsultaExtracto consultaE;
	
	
	public MenuPrincipal(ConsultaExtracto consultaE) {
		this.consultaE = consultaE;
	}

	@PostConstruct
	private void init() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean salir = false;
		int opcion = 0;
		String[] opciones = {"1 - Consultar Extracto", "2 - Cargar Compras", "9 - Salir"};
		
		do {
			for (int i = 0; i < opciones.length; i++) {
				System.out.println(opciones[i]);
			}
			System.out.print("\nOpcion: ");
			try {
				opcion = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
			}
			
			salir = procesaOpcion(opcion);
		} while(!salir);
	}
	
	private boolean procesaOpcion(int opcion) {
		
		boolean salir = false;
		
		switch (opcion) {
		case 1:
			consultaE.pantallaExtracto();
			break;
		case 2:
			System.out.println("Carga Compras");
			break;
		case 9:
			System.out.println("Adios...");
			salir = true;
			break;

		default:
			break;
		}
		
		return salir;
	}
}
