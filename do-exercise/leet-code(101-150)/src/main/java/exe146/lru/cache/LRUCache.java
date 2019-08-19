package exe146.lru.cache;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private int capacity;
    private int size;

    private ListNode head = new ListNode(-1, -1);
    private ListNode tail = new ListNode(-1, -1);

    private Map<Integer, ListNode> indexMap;

    public LRUCache(int capacity) {
        indexMap = new HashMap<>(capacity);
        this.capacity = capacity;
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
            put(key, value);
            return;
        }

        if (size < capacity) {
            size++;
            ListNode cur = new ListNode(key, value);
            cur.next = head.next;
            head.next.pre = cur;

            head.next = cur;
            cur.pre = head;

            indexMap.put(key, cur);
        } else {
            size--;
            ListNode preTail = tail.pre;
            indexMap.remove(preTail.key);

            tail.pre = preTail.pre;
            preTail.pre.next = tail;

            preTail.next = null;
            preTail.pre = null;
            put(key, value);
        }
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