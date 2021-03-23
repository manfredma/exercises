package manfred.end.power.mock;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(MathUtil.class) //准备被mock的类，在这里为MathUtil.class
public class CalculatorTest {

    /**
     * Unit under test.
     */
    private Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();

        //调用PowerMockito.mockStatic()
        PowerMockito.mockStatic(MathUtil.class);
        //使用PowerMockito.when()定义mock动作
        PowerMockito.when(MathUtil.addInteger(1, 1)).thenReturn(0);
        PowerMockito.when(MathUtil.addInteger(2, 2)).thenReturn(1);
    }

    @Test
    public void shouldCalculateInAStrangeWay() {
        assertEquals(0, calc.add(1, 1));
        assertEquals(1, calc.add(2, 2));
    }
}