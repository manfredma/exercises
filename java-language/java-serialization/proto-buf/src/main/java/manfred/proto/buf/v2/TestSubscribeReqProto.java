package manfred.proto.buf.v2;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sun.org.apache.xerces.internal.impl.io.UCSReader;

/**
 * @author manfred on 2019/9/14.
 */
public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] req) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(req);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        return SubscribeReqProto.SubscribeReq.newBuilder()
                .setSubReqId(1)
                .setUserName("manfred")
                .setProductName("netty")
                .addAddress("Nj")
                .addAddress("bj")
                .addAddress("sz")
                .build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("before encode : \n" + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("after decode : \n" + req2.toString());
        System.out.println("Assert equal : --> " + req2.equals(req));
    }


}
