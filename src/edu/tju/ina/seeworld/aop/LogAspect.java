package edu.tju.ina.seeworld.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public Object loggingAround(ProceedingJoinPoint joinpoint) throws Throwable {
		logger.info("method starts...."
				+ joinpoint.getSignature().getDeclaringTypeName() + "_"
				+ joinpoint.getSignature().getName()+" with "+arrayToString(joinpoint.getArgs()));
		Object result = joinpoint.proceed();
		logger.info("method ends...."
				+ joinpoint.getSignature().getDeclaringTypeName() + "_"
				+ joinpoint.getSignature().getName());
		return result;
	}

	public void loggingBefore(JoinPoint joinpoint) throws Throwable {
		logger.info("method starts...."
				+ joinpoint.getSignature().getDeclaringTypeName() + "_"
				+ joinpoint.getSignature().getName());
	}

	public void loggingAfter(JoinPoint joinpoint) throws Throwable {
		logger.info("method ends...."
				+ joinpoint.getSignature().getDeclaringTypeName() + "_"
				+ joinpoint.getSignature().getName());
	}

	public void afterThrowing(JoinPoint joinpoint, Throwable throwable)
			throws Throwable {
		logger.error("Logging that a exception " + throwable
				+ " \n\tException was thrown in "
				+ joinpoint.getSignature().getDeclaringTypeName() + "_"
				+ joinpoint.getSignature().getName() + "\n\t\tStackTrace:\n\t\t\t"
				+ arrayToString(throwable.getStackTrace()));
	}

	private String arrayToString(Object[] traces) {
		StringBuilder trace = new StringBuilder();
		for (Object ste : traces) {
			trace.append(ste==null?"":ste.toString()+"\t");
		}
		return trace.toString();
	}
}
