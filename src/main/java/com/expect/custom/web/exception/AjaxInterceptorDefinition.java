package com.expect.custom.web.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * ajax请求的切点定义
 */
@Component
@Aspect
public class AjaxInterceptorDefinition {

	/**
	 * 异常处理的切点
	 */
	@Pointcut(value = "@annotation(com.expect.custom.web.exception.AjaxRequest)")
	public void ajaxPointCut() {
	}

	/**
	 * 异常处理通知
	 */
	@Around("ajaxPointCut()")
	public Object ajaxAdvice(ProceedingJoinPoint pjp) throws AjaxRequestException {
		try {
			Object object = pjp.proceed();
			return object;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new AjaxRequestException();
		}
	}

}
