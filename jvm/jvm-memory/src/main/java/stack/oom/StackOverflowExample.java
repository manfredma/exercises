package stack.oom;

public class StackOverflowExample {
    public static void main(String[] args) {
        alwaysRecursion(0);
    }

    public static void alwaysRecursion(int depth) {
        alwaysRecursion(depth + 1);
    }
}
