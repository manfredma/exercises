package basic;

public interface Node {
    //这里是动态分派，接口类不好体现。我们看下结构类
    void accept(Visitor visitor);
}