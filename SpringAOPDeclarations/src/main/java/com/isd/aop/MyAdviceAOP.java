package com.isd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdviceAOP {
	public void simpleBeforeAdvice(JoinPoint jp, int intValue) {
		System.out.println("Выполняется: DeclaringTypeName=" + jp.getSignature().getDeclaringTypeName() + " Signature="
				+ jp.getSignature().getName());
		
		if(intValue==100)System.out.println("Абзац. Значение действительно="+intValue);
	}
	
	public Object simpleAroundAdvice(ProceedingJoinPoint jp, int intValue) throws Throwable {
		System.out.println("перед исполнением: "+jp.getSignature().getDeclaringTypeName()+" "+jp.getSignature() );
		Object proc=jp.proceed();
		System.out.println("исполнение после: "+jp.getSignature().getDeclaringTypeName()+" "+jp.getSignature() );
		return proc;
	}

}
