package exe225.implement.stack.using.queues;

import java.util.ArrayDeque;
import java.util.Queue;

class MyStack {

    Queue<Integer> data = new ArrayDeque<>();
    Queue<Integer> data2 = new ArrayDeque<>();
    boolean isData = true;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        if (isData) {
            data.add(x);
            while (!data2.isEmpty()) {
                data.add(data2.poll());
            }
            isData = false;
        } else {
            data2.add(x);
            while (!data.isEmpty()) {
                data2.add(data.poll());
            }
            isData = true;
        }

    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        if (!isData) {
            return data.poll();
        } else {
            return data2.poll();
        }
    }

    /**
     * Get the top element.
     */
    public int top() {
        if (!isData) {
            return data.element();
        } else {
            return data2.element();
        }
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        if (!isData) {
            return data.isEmpty();
        } else {
            return data2.isEmpty();
        }
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */