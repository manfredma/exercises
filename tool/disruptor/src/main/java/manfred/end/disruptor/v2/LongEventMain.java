package manfred.end.disruptor.v2;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        // 消费者线程池
        Executor executor = Executors.newCachedThreadPool();
        // 事件工厂
        LongEventFactory eventFactory = new LongEventFactory();
        // 指定RingBuffer大小
        int bufferSize = 1024;

        // 构造事件分发器
        Disruptor<LongEvent> disruptor = new Disruptor<>(
                eventFactory,
                bufferSize,
                executor,
                ProducerType.SINGLE, // 1.ProducerType.SINGLE 单生产者模式 2.ProducerType.MULTI 多生产者模式
                new YieldingWaitStrategy());//消费者等待策略
        // 注册消费者
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动事件分发
        disruptor.start();
        // 获取RingBuffer 用于生产事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i = 0; true; i++) {
            bb.putLong(0, i);
            // 发送事件
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}