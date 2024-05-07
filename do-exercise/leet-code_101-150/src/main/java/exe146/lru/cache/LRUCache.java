package exe146.lru.cache;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private final ListNode head = new ListNode(-1, -1);
    private final ListNode tail = new ListNode(-1, -1);
    private final int capacity;
    private final Map<Integer, ListNode> indexMap;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        indexMap = new HashMap<>(capacity);
        this.size = 0;

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!indexMap.containsKey(key)) {
            return -1;
        }

        ListNode cur = indexMap.get(key);
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        cur.next = head.next;
        head.next.pre = cur;

        cur.pre = head;
        head.next = cur;

        return cur.val;
    }

    public void put(int key, int value) {
        if (indexMap.containsKey(key)) {
            ListNode old = indexMap.get(key);
            old.pre.next = old.next;
            old.next.pre = old.pre;
            indexMap.remove(key);
            size--;
        }
        // 处理集合满的情况
        if (size == capacity) {
            size--;
            ListNode preTail = tail.pre;
            indexMap.remove(preTail.key);

            tail.pre = preTail.pre;
            preTail.pre.next = tail;

            preTail.next = null;
            preTail.pre = null;
        }
        size++;
        ListNode cur = new ListNode(key, value);
        cur.next = head.next;
        head.next.pre = cur;

        head.next = cur;
        cur.pre = head;

        indexMap.put(key, cur);
    }

    private static class ListNode {
        int val;
        int key;
        ListNode next;
        ListNode pre;

        ListNode(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }
}