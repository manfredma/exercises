package manfre.end.mockito;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class Main {
    /**
     * mock 静态方法来创建一个 Mock 对象。mock 方法接收一个 class 类型，即我们需要 mock 的类型
     */
    @Test
    public void createMockObject() {
        // 使用 mock 静态方法创建 Mock 对象.
        List mockedList = mock(List.class);
        Assert.assertTrue(mockedList instanceof List);

        // mock 方法不仅可以 Mock 接口类, 还可以 Mock 具体的类型.
        ArrayList mockedArrayList = mock(ArrayList.class);
        Assert.assertTrue(mockedArrayList instanceof List);
        Assert.assertTrue(mockedArrayList instanceof ArrayList);
    }

    /**
     * 配置 Mock 对象
     * <p>
     * <p>
     * 我们使用 when(​...).thenReturn(​...) 方法链来定义一个行为, 例如 "when(mockedList.add("one")).thenReturn(true)" 表示:
     * 当调用了mockedList.add("one"), 那么返回 true.. 并且要注意的是, when(​...).thenReturn(​...) 方法链不仅仅要匹配方法的调用, 而且要方法的参数一样才行.
     * 而且有趣的是, when(​...).thenReturn(​...) 方法链可以指定多个返回值, 当这样做后, 如果多次调用指定的方法, 那么这个方法会依次返回这些值. 例如 "when(i.next())
     * .thenReturn("Hello,").thenReturn("Mockito!");", 这句代码表示: 第一次调用 i.next() 时返回 "Hello,", 第二次调用 i.next() 时返回
     * "Mockito!".
     */
    @Test
    public void configMockObject() {
        List mockedList = mock(List.class);

        // 我们定制了当调用 mockedList.add("one") 时, 返回 true
        when(mockedList.add("one")).thenReturn(true);
        // 当调用 mockedList.size() 时, 返回 1
        when(mockedList.size()).thenReturn(1);

        Assert.assertTrue(mockedList.add("one"));
        // 因为我们没有定制 add("two"), 因此返回默认值, 即 false.
        Assert.assertFalse(mockedList.add("two"));
        Assert.assertEquals(mockedList.size(), 1);

        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello,").thenReturn("Mockito!");
        String result = i.next() + " " + i.next();
        //assert
        Assert.assertEquals("Hello, Mockito!", result);
    }

    /**
     * doThrow(ExceptionX).when(x).methodCall, 它的含义是: 当调用了 x.methodCall 方法后, 抛出异常 ExceptionX.
     * 因此 doThrow(new NoSuchElementException()).when(i).next() 的含义就是: 当第三次调用 i.next() 后, 抛出异常 NoSuchElementException.
     * (因为 i 这个迭代器只有两个元素)
     *
     * @throws Exception
     */
    @Test(expected = NoSuchElementException.class)
    public void testForIOException() throws Exception {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello,").thenReturn("Mockito!"); // 1
        String result = i.next() + " " + i.next(); // 2
        Assert.assertEquals("Hello, Mockito!", result);

        doThrow(new NoSuchElementException()).when(i).next(); // 3
        i.next(); // 4
    }

    /**
     * 校验 Mock 对象的方法调用
     * <p>
     * <p>
     * Mockito 会追踪 Mock 对象的所用方法调用和调用方法时所传递的参数. 我们可以通过 verify() 静态方法来来校验指定的方法调用是否满足断言.
     */
    @Test
    public void testVerify() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        when(mockedList.size()).thenReturn(5);
        Assert.assertEquals(mockedList.size(), 5);

        // mockedList.add("one") 至少被调用了 1 次(atLeastOnce)
        verify(mockedList, atLeastOnce()).add("one");
        // mockedList.add("two") 被调用了 1 次(times(1))
        verify(mockedList, times(1)).add("two");
        // mockedList.add("three times") 被调用了 3 次(times(3))
        verify(mockedList, times(3)).add("three times");
        // mockedList.isEmpty() 从未被调用(never)
        verify(mockedList, never()).isEmpty();
    }

    /**
     * 使用 spy() 部分模拟对象
     * <p>
     * Mockito 提供的 spy 方法可以包装一个真实的 Java 对象, 并返回一个包装后的新对象. 若没有特别配置的话, 对这个新对象的所有方法调用, 都会委派给实际的 Java 对象.
     * <p>
     * 这个例子中我们实例化了一个 LinkedList 对象, 然后使用 spy() 方法对 list 对象进行部分模拟. 接着我们使用 when(...).thenReturn(...) 方法链来规定 spy.size()
     * 方法返回值是 100. 随后我们给 spy 添加了两个元素, 然后再 调用 spy.get(0) 获取第一个元素.
     * 这里有意思的地方是: 因为我们没有定制 add("one"), add("two"), get(0), get(1), 因此通过 spy 调用这些方法时, 实际上是委派给 list 对象来调用的.
     * 然而我们 定义了 spy.size() 的返回值, 因此当调用 spy.size() 时, 返回 100.
     */
    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        // 对 spy.size() 进行定制.
        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        // 因为我们没有对 get(0), get(1) 方法进行定制,
        // 因此这些调用其实是调用的真实对象的方法.
        Assert.assertEquals(spy.get(0), "one");
        Assert.assertEquals(spy.get(1), "two");

        Assert.assertEquals(spy.size(), 100);
    }

}
