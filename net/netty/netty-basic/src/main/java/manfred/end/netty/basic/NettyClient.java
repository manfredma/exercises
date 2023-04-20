package manfred.end.netty.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Manfred since 2019/8/16
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer())
                .remoteAddress("127.0.0.1", 8000);

        ChannelFuture regFuture = bootstrap.connect();

        Channel channel = regFuture.channel();

        while (true) {
            ChannelFuture channelFuture = channel.writeAndFlush(new Date() + ": hello world!");
            TimeUnit.MILLISECONDS.sleep(1000);
            if (channelFuture.isSuccess()) {
                System.out.println("发送成功了");
            }
        }
    }
}

class ClientChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline().addLast(new StringEncoder());
    }
}