package com.stock.market.stockprice.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

	@Around("controllerPointcut()||servicePointcut()")
	public Object logMethod(ProceedingJoinPoint pjb) throws Throwable {
		Signature signature = pjb.getSignature();
		log.debug("{} has started execution", signature);
		Object result = pjb.proceed();
		log.debug("{} has ended execution", signature);
		return result;
	}

	@Pointcut("execution(* com.stock.market.stockprice.controller..*(..)) ")
	public void controllerPointcut() {
	}

	@Pointcut("execution(* com.stock.market.stockprice.service..*(..)) ")
	public void servicePointcut() {
	}

}
