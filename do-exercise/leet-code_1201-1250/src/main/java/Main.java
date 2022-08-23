public class Main {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(4);
        skipList.insert(2);
        skipList.printAll();

        skipList.delete(4);
        skipList.printAll();
    }
}
