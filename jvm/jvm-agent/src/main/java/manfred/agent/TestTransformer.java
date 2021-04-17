package manfred.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            ClassLoader classLoader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classFileBuffer) {
        System.out.println(
                classLoader + " Load " + className + "[" + this.getClass().getClassLoader() + "]");
        //进行对应类字节码的操作，并返回新字节码数据的byte数组，如果返回null，则代码不对此字节码作任何操作
        return null;
    }
}
