package lazy;

public class SingletonPatternDemo {
   public static void main(String[] args) {

      //获取唯一可用的对象
      Singleton object = Singleton.getInstance();
 
      //显示消息
      object.showMessage();
   }
}