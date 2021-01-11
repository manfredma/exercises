package manfred.end.java.basic.operator;

import org.junit.Test;

/**
 * @author Manfred since 2019/4/22
 */
public class Boot {

    @Test
    public void testParameter() {

        // + 号操作符从左到右计算
        for (int i = 0; i < 10; i++) {
            System.out.println("i++" + i++ + ", (i++)" + (i++));
        }

        // 参数按照从左到右计算
        for (int i = 0; i < 10; i++) {
            print(i++, i++);
        }

        // + 号操作符从左到右计算，后一个 i++ 会使用前一个 i++ 的计算结果开始计算
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i + ", (i++)(i++)" + (i++) * (i++));
        }
    }

    private void print(int a, int b) {
        System.out.println("a=" + a + ",b=" + b);
    }

}
