package manfred.end.algorithm.dynamic.programming;

/**
 * @author Manfred since 2019/8/21
 */
public class Knapsack {

    /**
     * 结果放到 maxW 中
     */
    int maxWeight = 0;

    /**
     * 物品重量
     */
    private int[] weight = {2, 2, 4, 6, 3};

    /**
     * 物品个数
     */
    private int n = 5;

    /**
     * 背包承受的最大重量
     */
    private int w = 9;

    /**
     * 回溯求解
     */
    public void bruteForce(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxWeight) {
                maxWeight = cw;
            }
            return;
        }

        // 选择不装第 i 个物品
        bruteForce(i + 1, cw);
        if (cw + weight[i] <= w) {
            // 选择装第 i 个物品
            bruteForce(i + 1, cw + weight[i]);
        }
    }

    private boolean[][] mem = new boolean[5][10];

    /**
     * 回溯求解
     */
    public void bruteForceWithMem(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxWeight) {
                maxWeight = cw;
            }
            return;
        }

        if (mem[i][cw]) {
            return;
        }

        mem[i][cw] = true;
        // 选择不装第 i 个物品
        bruteForce(i + 1, cw);
        if (cw + weight[i] <= w) {
            // 选择装第 i 个物品
            bruteForce(i + 1, cw + weight[i]);
        }
    }

}

