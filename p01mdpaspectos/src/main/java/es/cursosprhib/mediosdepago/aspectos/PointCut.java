package es.cursosprhib.mediosdepago.aspectos;

import org.aspectj.lang.annotation.Pointcut;

public class PointCut {
	
	@Pointcut("within(es.cursosprhib.mediosdepago.persistencia..*)")
	public void enPersistencia(){}
	
	@Pointcut("within(es.cursosprhib.mediosdepago.persistencia..*)")
	public void enServicio(){}
	
	public final String SERVICIO = "es.cursosprhib.mediosdepago.aspectos.PointCut.enServicio()";
	public final String PERSISTENCIA = "es.cursosprhib.mediosdepago.aspectos.PointCut.enPersistencia()";

}
