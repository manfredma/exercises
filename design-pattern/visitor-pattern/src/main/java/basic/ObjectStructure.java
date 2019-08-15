package basic;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {
    private List<Node> list = new ArrayList<>();

    public void action(Visitor visitor) {
        for (Node n : list) {
            //看完LZ的例子后再来看这里，你会知道visit是有2个方法的，一个参数是ConcreteNodeA，另一个是ConcreteNodeB。
            //在编译期，肯定不能确定这个Node是那个具体的类型，只有到运行期间，代码走到这，才知道具体的类型，
            // 假如是ConcreteNodeA类型，走visit(ConcreteNodeA concreteNodeA)；假如是ConcreteNodeB类型，走visit(ConcreteNodeA concreteNodeB)，
            // 所以这个是动态分派的地方。主要是利用多态特性
            n.accept(visitor);
        }
    }

    public void add(Node node) {
        list.add(node);
    }
}
