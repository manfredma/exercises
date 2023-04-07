package manfred.end.generic.get;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ParentGeneric<T> {

}

class SubClass extends ParentGeneric<String> {

}

class SubClass2<T> extends ParentGeneric<T> {

}

public class GenericGet {

    //获取实际的泛型类型
    public static <T> Type findGenericType(Class<T> cls) {
        Type genType = cls.getGenericSuperclass();
        Type finalNeedType = null;
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            finalNeedType = params[0];
        }
        return finalNeedType;
    }

    public static void main(String[] args) {
        // 1.
        SubClass subClass = new SubClass();
        SubClass2<Integer> subClass2 = new SubClass2<>();
        //打印 subClass 获取的泛型
        System.out.println("subClass: " + findGenericType(subClass.getClass()));
        //打印subClass2获取的泛型
        System.out.println("subClass2: " + findGenericType(subClass2.getClass()));



        // 2.
        ParentGeneric<String> parentGeneric1 = new ParentGeneric<String>();
        ParentGeneric<String> parentGeneric2 = new ParentGeneric<String>(){};

        //打印 parentGeneric1 获取的泛型
        System.out.println("parentGeneric1: " + findGenericType(parentGeneric1.getClass()));
        //打印 parentGeneric2 获取的泛型
        System.out.println("parentGeneric2: " + findGenericType(parentGeneric2.getClass()));

        // 3.
        System.out.println(subClass.getClass().getGenericSuperclass());
        Class<SubClass> subKlass = SubClass.class;
        System.out.println(subClass.getClass());
        System.out.println(subClass2.getClass().getGenericSuperclass());

        // 4.
        System.out.println(parentGeneric1.getClass().getGenericSuperclass());

    }
}


