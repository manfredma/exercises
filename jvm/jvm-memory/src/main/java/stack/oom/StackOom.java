package stack.oom;

import java.util.Scanner;

public class StackOom {
    public static void main(String[] args) {
        StackOom stackOom = new StackOom();
        while (true) {
            new Thread(stackOom::alwaysRecursion).start();
        }
    }

    private void alwaysRecursion() {
        new Scanner(System.in).next();
    }
}
