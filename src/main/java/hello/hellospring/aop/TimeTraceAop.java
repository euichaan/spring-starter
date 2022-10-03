package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

  @Around("execution(* hello.hellospring..*(..))") // 적용 범위
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("START: "+ joinPoint.toString());
    try {
      // 다음 메서드로 진행
      return joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = start - finish;
      System.out.println("END: "+ joinPoint.toString()+ " " + timeMs + "ms");

    }
  }
}
