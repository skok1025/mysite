package com.cafe24.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {

	// 여러개 패키지에 Around 하기
	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..)) ") 
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();

		// method 실행
		Object result = pjp.proceed();

		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		//PointCut 당한  클래스의 이름 및 메서드 이름
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time] ["+taskName+"] : "+totalTime + "mils");
		
		return result;
	}
}
