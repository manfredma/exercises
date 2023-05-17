package exe1206.design.skiplist;

class SkiplistV2 {

    private static final int LEVEL_SIZE = 32;

    /**
     * 最大层数
     */
    private static final int DEFAULT_MAX_LEVEL = LEVEL_SIZE - 1;
    /**
     * 随机层数概率，也就是随机出的层数，在 第1层以上(不包括第一层)的概率，层数不超过maxLevel，层数的起始号为1
     */
    private static final double DEFAULT_P_FACTOR = 0.5;

    Node head = new Node(null, LEVEL_SIZE); //头节点

    /**
     * 表示当前nodes的实际层数
     */
    int currentLevel = 0;

    public boolean search(int target) {
        Node searchNode = head;
        for (int i = currentLevel; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, target);
            if (searchNode.next[i] != null && searchNode.next[i].value == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param num
     */
    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num, level + 1);
        // 计算出当前num 索引的实际层数，从该层开始添加索引
        for (int i = currentLevel; i >= 0; i--) {
            //找到本层最近离num最近的
            updateNode = findClosest(updateNode, i, num);
            if (i <= level) {
                if (updateNode.next[i] == null) {
                    updateNode.next[i] = newNode;
                } else {
                    Node temp = updateNode.next[i];
                    updateNode.next[i] = newNode;
                    newNode.next[i] = temp;
                }
            }
        }
        //如果随机出来的层数比当前的层数还大，那么超过currentLevel的head 直接指向newNode
        if (level > currentLevel) {
            for (int i = currentLevel + 1; i <= level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;
        }

    }

    public boolean erase(int num) {
        boolean flag = false;
        Node searchNode = head;
        for (int i = currentLevel; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, num);
            if (searchNode.next[i] != null && searchNode.next[i].value == num) {
                //找到该层中该节点
                searchNode.next[i] = searchNode.next[i].next[i];
                flag = true;
                continue;
            }
        }
        return flag;
    }

    /**
     * 找到level层 value 大于node 的节点
     */
    private Node findClosest(Node node, int levelIndex, int value) {
        while ((node.next[levelIndex]) != null && value > node.next[levelIndex].value) {
            node = node.next[levelIndex];
        }
        return node;
    }


    /**
     * 随机一个层数
     */
    private static int randomLevel() {
        int level = 0;
        while (Math.random() < DEFAULT_P_FACTOR && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }


    private static class Node {
        Integer value;
        Node[] next;

        public Node(Integer value, int size) {
            this.value = value;
            this.next = new Node[size];
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

}