package manfred.end.orthogonal.design;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;

/**
 * @author manfred on 2022/11/18.
 */
public class StudentTest {

    @Test
    public void sortStudentsByHeight() {
        Student[] x = new Student[5];
        for (int i = 0; i < x.length; i++) {
            x[i] = new Student();
            x[i].setHeight(ThreadLocalRandom.current().nextInt(100));
        }
        Arrays.stream(x).forEach(System.out::println);
        Student.sortStudentsByHeight(x);
        System.out.println("after sort：");
        Arrays.stream(x).forEach(System.out::println);
    }
}