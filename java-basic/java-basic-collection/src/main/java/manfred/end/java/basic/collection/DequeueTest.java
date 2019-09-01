package manfred.end.java.basic.collection;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author manfred on 2019/9/1.
 */
public class DequeueTest {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        deque.push(2);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
    }
}
