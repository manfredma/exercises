package manfred.end.generic.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1）、泛型协变，假设我定义了一个 Class<T> 的泛型类，其中 A 是 B 的子类，同时 Class<A> 也是 Class<B> 的子类，那么我们说 Class 在 T 这个泛型上是协变的
 * 2）、泛型逆变，假设我定义了一个 Class<T> 的泛型类，其中 A 是 B 的子类，同时 Class<B> 也是 Class<A> 的子类，那么我们说 Class 在 T 这个泛型上是逆变的
 * 3）、泛型不变，假设我定义了一个 Class<T>  的泛型类，其中 A 是 B 的子类，同时 Class<B> 和 Class<A> 没有继承关系，那么我们说 Class 在 T 这个泛型上是不变的
 */
public class WildCard {
    public static void main(String[] args) {

        // ============================================================================================================
        // 协变！
        // 泛型的上边界通配符语法结构：<? extends Bound>，使得泛型支持协变，它限定的类型是当前上边界类或者其子类，
        // 如果是接口的话就是当前上边界接口或者实现类，使用上边界通配符的变量只读，不可以写，可以添加 null ，但是没意义
        // 问题：为啥使用上边界通配符的变量只读，而不能写？
        //    1、<? extends Bound>,它限定的类型是当前上边界类或者其子类，它无法确定自己具体的类型，因此编译器无法验证类型的安全，所以不能写
        //    2、假设可以写，我们向它里面添加若干个子类，然后用一个具体的子类去接收，势必会造成类型转换异常
        // ============================================================================================================
        Number number = new Integer(1111);


        // ArrayList<Number> x = new ArrayList<Integer>();



        ArrayList<? extends Number> numberList = new ArrayList<Integer>();

        // numberList.add(number);

        List<Integer> integerList = new ArrayList<>();
        List<Number> numberList2 = new ArrayList<>();
        integerList.add(666);
        numberList2.add(123);

        getNumberData(integerList);
        getNumberData(numberList2);


        // ============================================================================================================
        // 逆变
        // 问题：为啥使用下边界通配符的变量可以写，而不建议读？
        // 1、<? super Bound>，它限定的类型是当前下边界类或者其父类，虽然它也无法确定自己具体的类型，但根据多态，
        //    它能保证自己添加的元素是安全的，因此可以写
        // 2、获取值的时候，会返回一个 Object 类型的值，而不能获取实际类型参数代表的类型，因此建议不要去读，
        //    如果你实在要去读也行，但是要注意类型转换异常
        // ============================================================================================================
        List<Number> numberList3 = new ArrayList<>();
        List<Object> objectList3 = new ArrayList<>();
        setNumberData(numberList3);
        setNumberData(objectList3);


        List<String> stringList4 = new ArrayList<>();
        List<Number> numberList4 = new ArrayList<>();
        List<Integer> integerList4 = new ArrayList<>();
        stringList4.add("erdai");
        numberList4.add(666);
        integerList4.add(123);
        getData(stringList4);
        getData(numberList4);
        getData(integerList4);
    }

    public static void getData(List<?> data) {
        System.out.println("data: " + data.get(0));
    }



    public static void getNumberData(List<? extends Number> data) {
        System.out.println("Number data :" + data.get(0));
    }

    public static void setNumberData(List<? super Number> data) {
        Number number = new Integer(666);
        data.add(number);

        // Object object = new Integer(666);
        // data.add(object);

        System.out.println(data.get(0));
    }

}
