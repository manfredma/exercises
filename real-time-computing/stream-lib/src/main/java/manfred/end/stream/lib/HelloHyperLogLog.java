package manfred.end.stream.lib;

import com.clearspring.analytics.stream.cardinality.HyperLogLog;
import com.clearspring.analytics.stream.cardinality.ICardinality;

public class HelloHyperLogLog {

    public static void main(String[] args) {
        ICardinality card = new HyperLogLog(10);
        for (int i : new int[] { 1, 2, 3, 2, 4, 3 }) {
            card.offer(i);
        }
        System.out.println(card.cardinality()); // 4
    }
}
