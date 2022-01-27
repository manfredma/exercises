package manfred.end.invoke.jsr292;

import java.lang.invoke.*;

public class Boot {

    public static void main(String[] args) throws Throwable {

        Boot boot = new Boot();
        boot.constantCallSite();
        boot.useMutableCallSite();
    }

    public void constantCallSite() throws Throwable {
        MethodType type = MethodType.methodType(String.class, int.class, int.class);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.findVirtual(String.class, "substring", type);

        ConstantCallSite callSite = new ConstantCallSite(handle);
        MethodHandle invoker = callSite.dynamicInvoker();
        String result = (String) invoker.invoke("Hello", 2, 4);
        System.out.println(result);
    }

    /**
     * MutableCallSite 允许对其所关联的目标方法句柄通过setTarget方法来进行修改。
     * 以下为 创建一个 MutableCallSite，指定了方法句柄的类型，则设置的其他方法也必须是这种类型。
     */
    public void useMutableCallSite() throws Throwable {
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MutableCallSite callSite = new MutableCallSite(type);
        MethodHandle invoker = callSite.dynamicInvoker();

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle maxHandle = lookup.findStatic(Math.class, "max", type);
        callSite.setTarget(maxHandle);
        int result = (int) invoker.invoke(3, 5);
        System.out.println(result == 5);

        MethodHandle minHandle = lookup.findStatic(Math.class, "min", type);
        callSite.setTarget(minHandle);
        result = (int) invoker.invoke(3, 5);
        System.out.println(result == 3);
    }

}
