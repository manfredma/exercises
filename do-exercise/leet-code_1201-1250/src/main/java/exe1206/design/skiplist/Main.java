package exe1206.design.skiplist;

public class Main {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.add(4);
        // System.out.println("init: \n" + skiplist);

        System.out.println(skiplist.search(0));   // return false.
        System.out.println(skiplist.search(1));   // return true.
        System.out.println(skiplist.erase(0));    // return false, 0 is not in skiplist.
        // System.out.println("after erase 0: \n" + skiplist);
        System.out.println(skiplist.erase(1));    // return true.
        // System.out.println("after erase 1: \n" + skiplist);
        System.out.println(skiplist.search(1));   // return false, 1 has already been erased.
        // System.out.println("after erase 1: \n" + skiplist);
        System.out.println(skiplist.erase(3));   // return true
        System.out.println("after erase 3: \n" + skiplist);

        System.out.println("=============================================================");

    }
}
