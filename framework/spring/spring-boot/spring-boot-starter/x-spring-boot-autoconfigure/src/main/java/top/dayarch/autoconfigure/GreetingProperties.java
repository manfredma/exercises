package top.dayarch.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "rgyb.greeting")
public class GreetingProperties {

    /**
     * GreetingProperties 开关
     */
    boolean enable = false;

    /**
     * 需要打招呼的成员列表
     */
    List<String> members = new ArrayList<>();

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}