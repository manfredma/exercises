package message.pack.v1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import message.pack.UserInfo;

/**
 * @author manfred on 2019/9/11.
 */
public class EchoClientHandle extends ChannelInboundHandlerAdapter {

    private int sendNumber;

    public EchoClientHandle(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = new UserInfo[sendNumber];
        for (int i = 0; i < infos.length; i++) {
            infos[i] = new UserInfo();
            infos[i].setUserId(i);
            infos[i].setUserName("ABCDEFG ---> " + i);
        }
        for (int i = 0; i < infos.length; i++) {
            ctx.write(infos[i]);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msg pack message : " + msg);
//        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
