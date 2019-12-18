package spring.custom.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogBeforeLogin {
	
    @Pointcut("@annotation(spring.custom.aspectj.UseAop)")
    public void xxx(){}

    @Before("xxx()")
    public void beforeLogin(JoinPoint joinPoint){
        System.out.println("有人要登录了。。。");
        System.out.println("before " + joinPoint);
    }
}