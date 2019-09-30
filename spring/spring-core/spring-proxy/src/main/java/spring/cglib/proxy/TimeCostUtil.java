package spring.cglib.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class TimeCostUtil implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation)
            throws Throwable {
        // 获取服务开始时间
        long beginTime = System.currentTimeMillis();

        // 获取类名和方法名
        String srcClassName = "";
        String methodName = "";
        if (invocation != null) {
            String className = invocation.getClass() != null ? invocation.getClass().getName() : "";
            System.out.println("The proxy class name is : " + className);
            if (invocation.getMethod() != null) {
                methodName = invocation.getMethod().getName();
            }
            if (invocation.getThis() != null && invocation.getThis().getClass() != null) {
                srcClassName = invocation.getThis().getClass().getName();

            }
        }

        // 调用原来的方法
        Object result = invocation.proceed();

        // 打印耗时
        System.out.println(srcClassName + "." + methodName + " cost time: " + (System.currentTimeMillis() - beginTime));

        return result;
    }

}