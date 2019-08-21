package manfred.end.algorithm.dynamic.programming;

import org.junit.Test;

/**
 * @author Manfred since 2019/8/21
 */
public class KnapsackTest {

    private int loop = 1_000_000;

    @Test
    public void test() {
        Knapsack knapsack = new Knapsack();
        long begin = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            knapsack = new Knapsack();
            knapsack.bruteForce(0, 0);
        }

        System.out.println("最大重量=" + knapsack.maxWeight + ", cost=" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            knapsack = new Knapsack();
            knapsack.bruteForceWithMem(0, 0);
        }
        System.out.println("最大重量=" + knapsack.maxWeight + ", cost=" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            knapsack = new Knapsack();
            knapsack.dynamicProgramming();
        }
        System.out.println("最大重量=" + knapsack.maxWeight + ", cost=" + (System.currentTimeMillis() - begin));
    }
}
