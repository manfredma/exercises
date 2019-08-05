package object.adapter;

public class Adapter implements Target {

    // 适配者是对象适配器的一个属性
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        //...一些操作...
        adaptee.adapteeRequest();
        //...一些操作...
    }
}
