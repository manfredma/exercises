import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * @author manfred on 2019/9/13.
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserId(100).buildUserName("manfred");
        MessagePack msgPack = new MessagePack();
        byte[] userInfoBytes = msgPack.write(userInfo);

        UserInfo cloneUserInfo = msgPack.read(userInfoBytes, UserInfo.class);

        System.out.println(cloneUserInfo);
    }
}
