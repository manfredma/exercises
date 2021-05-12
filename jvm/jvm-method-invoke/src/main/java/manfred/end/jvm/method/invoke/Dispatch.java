package manfred.end.jvm.method.invoke;

import org.junit.Test;

/**
 * Created by chen on 2016/3/23.
 */
public class Dispatch {
    static class QQ{

    }
    static class _360{

    }

    public static class Father{
        public void hardChoice(QQ  arg){
            System.out.println("father choose qq");
        }
        public void hardChoice(_360  arg){
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father{
        public void hardChoice(QQ  arg){
            System.out.println("son choose qq");
        }
        public void hardChoice(_360  arg){
            System.out.println("son choose 360");
        }
    }
    @Test
    public void test(){
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}