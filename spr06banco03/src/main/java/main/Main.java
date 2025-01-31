package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;

public class Main {
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
