package message.pack.v1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import sun.plugin2.main.client.MessagePassingExecutionContext;

import java.util.List;

/**
 * @author manfred on 2019/9/13.
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final int length = msg.readableBytes();
        final byte[] array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);

        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(array));
    }
}
