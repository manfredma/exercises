import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavassistDemo {

    public static void main(String[] args) throws CannotCompileException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        //初始化Javassist的类池
        ClassPool classPool = ClassPool.getDefault();

        //创建一个HelloWorld类
        CtClass ctClass = classPool.makeClass("HelloWorld");

        //添加一个test方法，会打印Hello World，直接传入方法的字符串
        CtMethod ctMethod = CtNewMethod.make("" +
                "public static void test(){" +
                "    System.out.println(\"Hello World\");" +
                "}", ctClass);

        ctClass.addMethod(ctMethod);

        //生成类
        Class aClass = ctClass.toClass();

        //通过反射调用这个类的实例
        Object object = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("test", null);
        method.invoke(object, null);
    }
}