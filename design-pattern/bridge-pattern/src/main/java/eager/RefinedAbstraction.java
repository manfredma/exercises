package eager;

public class RefinedAbstraction extends Abstraction {
     @Override
     protected void operation() {
         super.getImplementor().operation();
     }
 }