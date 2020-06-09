package com.hqyj.SpringBootDemo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ControllerAspect {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	@Pointcut("execution(public * com.hqyj.SpringBootDemo.modules.*.controller.*.*(..))")
	@Order(1)
	public void controllerPointCut(){}
	
	@Before(value = "com.hqyj.SpringBootDemo.aspect.ControllerAspect.controllerPointCut()")
	public void beforeController (JoinPoint joinPoint) {
		LOGGER.debug("-------- Before Controller --------");
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LOGGER.debug("请求来源：" + request.getRemoteAddr());
		LOGGER.debug("请求URL：" + request.getRequestURL().toString());
		LOGGER.debug("请求方式：" + request.getMethod());
		LOGGER.debug("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + 
				joinPoint.getSignature().getName());
		LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
	}
	
	@After(value = "com.hqyj.SpringBootDemo.aspect.ControllerAspect.controllerPointCut()")
	public void afterController() {
		LOGGER.debug("-------- After Controller --------");
	}
	
	@Around(value = "com.hqyj.SpringBootDemo.aspect.ControllerAspect.controllerPointCut()")
	public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LOGGER.debug("-------- Around Controller --------");
		return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
	}
}
