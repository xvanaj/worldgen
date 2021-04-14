/*
package com.vana.util.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class TimingAspect {

    private static Map<String, Long> counts; {
        counts = new HashMap<>();
    }

    private static Map<String, Long> methodExecTimes; {
        methodExecTimes = new HashMap<>();
    }

    public static Map<String, Long> getExecutionTimes() {
        return methodExecTimes;
    }

    //public methods excluding getters and setters
    @Pointcut("execution(public * *(..)) && !execution(void set*(*)) && !execution(!void get*())")
    private void anyPublicOperation() {}

    @Pointcut("execution(* *(..))")
    private void anyOperation() {}

    @Pointcut("within(com.vana..*)")
    private void inPackage() {}

    */
/**
     * Logs execution time of methods annotated with @Timed
     *//*

    @Around("anyOperation() && @annotation(Timed)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    */
/**
     * Tracks how many times was executed method with annotation @Profiled
     *//*

    */
/*@Around("anyPublicOperation() && inPackage()")
    public Object processMethodExecutionCounts(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();

        String name = joinPoint.getSignature().toString();

        if (counts.get(name) == null) {
            counts.put(name, 1l);
        } else {
            counts.put(name, counts.get(name) + 1);
        }

        if (counts.get(name) >= 1000000) {
            System.out.println(name + " method executed " + counts.get(name) + " times");
        }

        return joinPoint;
    }*//*


    */
/**
     * Tracks total execution time of method with annotation @Profiled
     *//*

    @Around("anyPublicOperation() && inPackage()")
    public Object processMethodExecutionTimes(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        String name = joinPoint.getSignature().toString();

        if (methodExecTimes.get(name) == null) {
            methodExecTimes.put(name, executionTime);
        } else {
            methodExecTimes.put(name, methodExecTimes.get(name) + executionTime);
        }

        return joinPoint;
    }

}
*/
