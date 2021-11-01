package manfred.end.java.basic.big;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTests {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal(2).setScale(10, RoundingMode.DOWN);
        BigDecimal y = new BigDecimal("2.33333").setScale(11, RoundingMode.DOWN);
        System.out.println(x.add(y) + ":" + x.add(y).scale());
        System.out.println(x.subtract(y) + ":" + x.subtract(y).scale());
        System.out.println(x.multiply(y) + ":" + x.multiply(y).scale());
        System.out.println(x.divide(y, RoundingMode.DOWN) + ":" +
                x.divide(y, RoundingMode.DOWN).scale());
        System.out.println(x.divide(y, 12, RoundingMode.DOWN) + ":" +
                x.divide(y, 12, RoundingMode.DOWN).scale());
    }
}
