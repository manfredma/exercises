package exe460.lfu.cache;

import java.util.ArrayList;
import java.util.HashMap;

class LFUCache {

    private java.util.List<List> index;

    private HashMap<Integer, ListNode> cache;

    private int capacity;
    private int count = 0;

    public LFUCache(int capacity) {
        index = new ArrayList<>();
        cache = new HashMap<>(capacity * 2);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (cache.containsKey(key)) {
            ListNode listNode = cache.get(key);
            List list = listNode.list;
            removeListNode(listNode);
            int fre = list.frequency + 1;
            addListNode(listNode, fre);
            return listNode.val;
        }
        return -1;
    }

    private void addListNode(ListNode listNode, int fre) {
        List newList = null;
        for (List list : index) {
            if (list.frequency == fre) {
                newList = list;
            }
        }
        if (newList == null) {
            newList = new List();
            newList.frequency = fre;
            index.add(newList);
        }

        listNode.next = newList.tail;
        listNode.pre = newList.tail.pre;
        listNode.list = newList;

        newList.tail.pre.next = listNode;
        newList.tail.pre = listNode;
    }

    private void removeListNode(ListNode listNode) {
        listNode.pre.next = listNode.next;
        listNode.next.pre = listNode.pre;

        if (listNode.list.head.next == listNode.list.tail) {
            index.remove(listNode.list);
        }
        listNode.pre = null;
        listNode.next = null;
        listNode.list = null;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            ListNode listNode = cache.get(key);
            List list = listNode.list;
            removeListNode(listNode);
            addListNode(listNode, list.frequency + 1);
            listNode.val = value;
        } else {
            if (count >= capacity) {
                List reList = index.get(0);
                for (List list : index) {
                    if (list.frequency < reList.frequency) {
                        reList = list;
                    }
                }
                cache.remove(reList.head.next.key);
                removeListNode(reList.head.next);
                count--;
            }
            ListNode listNode = new ListNode(key, value, null);
            addListNode(listNode, 1);
            count++;
            cache.put(key, listNode);
        }
    }


    class List {
        ListNode head = new ListNode(-1, -1, this);
        ListNode tail = new ListNode(-1, -1, this);

        {
            head.next = tail;
            tail.pre = head;
        }

        int frequency;

        @Override
        public String toString() {
            String s = frequency + " -> ";
            ListNode cur = head.next;
            while (cur != tail) {
                s += ("--> [" + cur.key + " -- " + cur.val + "]");
                cur = cur.next;
            }
            return s;
        }
    }

    class ListNode {
        int val;
        int key;
        ListNode next;
        ListNode pre;
        List list;

        public ListNode(int key, int val, List list) {
            this.val = val;
            this.list = list;
            this.key = key;
        }
    }

    @Override
    public String toString() {
        String r = "index: \n";
        for (int i = 0; i < index.size(); i++) {
            r += "  " + index.get(i) + "\n";
        }
        return r;
    }
}
