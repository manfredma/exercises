package proto.buf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author manfred on 2019/9/14.
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            SubscribeReqProto.SubscribeReq req = SubscribeReqProto.SubscribeReq.newBuilder()
                    .setSubReqId(i)
                    .setUserName("manfred")
                    .addAddress("bj")
                    .build();
            System.out.println("send client request : " + req);
            ctx.writeAndFlush(req);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeRespProto.SubscribeResp subscribeResp = (SubscribeRespProto.SubscribeResp) msg;
        System.out.println("receive server response : " + subscribeResp.getSubReqId() + ":" + subscribeResp.getDesc());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
