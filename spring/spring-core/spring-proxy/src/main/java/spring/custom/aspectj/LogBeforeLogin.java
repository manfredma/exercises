package spring.custom.aspectj;

import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

@Aspect
public class LogBeforeLogin {

    @Resource
    private LoginService loginService;

    @Setter
    private String name;
	
    @Pointcut("@annotation(spring.custom.aspectj.UseAop)")
    public void xxx(){}

    @Before("xxx()")
    public void beforeLogin(JoinPoint joinPoint){
        System.out.println("有人要登录了。。。, interceptor name = " + name);
        System.out.println("before " + joinPoint);
    }
}