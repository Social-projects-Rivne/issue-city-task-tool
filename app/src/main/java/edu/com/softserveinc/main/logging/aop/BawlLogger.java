package edu.com.softserveinc.main.logging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BawlLogger {

	@Pointcut("execution(public * *(..))")
	public void anyPublicMethod() {
	}

	@Before("anyPublicMethod()")
	public void myAspect(JoinPoint point) throws Throwable {

		System.out.println("in aspect!");
	}
}
// log.debug("{}(): {}", methodName, args);