package edu.com.softserveinc.main.logging.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BawlLogger {

	@Before("execution(* edu.com.softserveinc.main..*.*(..))")
	public void myAspect(JoinPoint point) throws Throwable {
		final Logger log = LoggerFactory.getLogger(point.getTarget().getClass());
		log.debug("{}() {}", point.getSignature().getName(), Arrays.asList(point.getArgs()));
	}
}