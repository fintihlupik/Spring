package es.cursosprhib.mediosdepago.inicio;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.cursosprhib.mediosdepago.config.AppConfig;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
