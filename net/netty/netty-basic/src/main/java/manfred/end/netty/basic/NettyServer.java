package manfred.end.netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import java.util.concurrent.TimeUnit;

/**
 * @author Manfred since 2019/8/16
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                .bind(8000);
    }
}

class ServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
            @Override
            protected void channelRead0(
                    ChannelHandlerContext ctx, String msg)
                    throws InterruptedException {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("读取到数据 -_-_-_-_-_-_-_-_-_-_-\n" +
                        msg.length() + "\n" + msg);
            }
        });
    }
}
