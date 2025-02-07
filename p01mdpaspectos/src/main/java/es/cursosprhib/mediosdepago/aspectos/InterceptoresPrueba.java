package es.cursosprhib.mediosdepago.aspectos;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InterceptoresPrueba {
	
	//@Before("enPersistencia() and args(*)") //lo definido en Persistencia() y solo un argumento
	@Before("enPersistencia() or enServicio()")
	//@Before(PointCut.SERVICIO + " or " + PointCut.PERSISTENCIA)
	public void advice01(JoinPoint jp) {
		System.out.println("Interceptado por advice01");
		System.out.println(jp.getSignature().getName());
		Object[] args = jp.getArgs();
		for(Object arg : args) 
			System.out.println(arg);
	}
	
	
	@Around("enServicio()")
	public Object advice02(ProceedingJoinPoint pjp) throws Throwable{
		long t0 = System.currentTimeMillis();
		System.out.println("Antes del metodo");
		Object ret = pjp.proceed();
		System.out.println("Después del metodo");
		long t1 = System.currentTimeMillis();
		System.out.println("Tardo " + (t1-t0));
		return ret;
	}
	
	@AfterReturning(pointcut = "enPersistencia()",returning = "obj")
	public void advice03(Object obj) {
		System.out.println(obj);
	}
	
	@AfterThrowing(pointcut = "enServicio()",throwing = "exc")
	public void advice04(Exception exc) {

	}
	
	
	@Pointcut("within(es.cursosprhib.mediosdepago.persistencia..*)")
	public void enPersistencia(){}
	
	@Pointcut("within(es.cursosprhib.mediosdepago.persistencia..*)")
	public void enServicio(){}
		
	

}
