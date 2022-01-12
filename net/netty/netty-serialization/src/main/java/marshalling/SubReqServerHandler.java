package marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import proto.buf.SubscribeReqProto;
import proto.buf.SubscribeRespProto;

/**
 * @author manfred on 2019/9/14.
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq subscribeReq = (SubscribeReqProto.SubscribeReq) msg;
        System.out.println("Server accept client subscriber req : \n" + subscribeReq);
        SubscribeRespProto.SubscribeResp subscribeResp = SubscribeRespProto.SubscribeResp.newBuilder()
                .setSubReqId(subscribeReq.getSubReqId())
                .setRespCode(200)
                .setDesc("恭喜你，成功了！")
                .build();
        ctx.writeAndFlush(subscribeResp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
