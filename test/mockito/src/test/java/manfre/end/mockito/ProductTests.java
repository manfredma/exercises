package manfre.end.mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;

import java.util.LinkedList;

public class ProductTests {
    @Test
    public void demo() {
        Product product = mock(Product.class);
        assert !product.getDel();
        assert product.getOpReason() == null;
        assert product.getProductId() == 0;
        assert product.getProductInfoList().size() == 0;
        assert product.getProductTitle() == null;
    }

    @Test
    public void demo2() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(0)).thenReturn("second");
        // 对同一个方法进行多次mock时，最后一次mock对象的行为会覆盖之前的行为
        assert mockedList.get(0) == "second";

        // 同一个方法的多次调用mock不同的返回值或异常
        when(mockedList.get(1))
                .thenThrow(new IllegalArgumentException())
                .thenReturn("foo");

        // 第一次调用 : 抛出运IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> mockedList.get(1));
        // 第二次调用 : 输出"foo"
        assert mockedList.get(1) == "foo";

        // 后续调用 : 也是输出"foo"
        assert mockedList.get(1) == "foo";
    }
}
