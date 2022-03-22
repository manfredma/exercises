package manfred.end.ognl;

import java.util.HashMap;
import java.util.Map;
import ognl.Ognl;
import ognl.OgnlException;

public class Boot {

    public static void main(String[] args) throws OgnlException {

        User user = new User();
        user.setId(1);
        user.setName("downpour");

        // 创建上下文环境
        Map context = new HashMap();
        context.put("introduction", "My name is ");

        // 测试从Root对象中进行表达式计算并获取结果
        Object name = Ognl.getValue(Ognl.parseExpression("name"), user);
        System.out.println(name.toString());

        // 测试从上下文环境中进行表达式计算并获取结果
        Object contextValue = Ognl.getValue(Ognl.parseExpression("#introduction"), context, user);
        System.out.println(contextValue);
        // 测试同时从将Root对象和上下文环境作为表达式的一部分进行计算
        Object hello = Ognl.getValue(Ognl.parseExpression("#introduction + name"), context, user);
        System.out.println(hello);

        // 对Root对象进行写值操作
        Ognl.setValue("group.name", user, "dev");
        Ognl.setValue("age", user, "18");

        System.out.println(user.getGroup().getName());

    }
}
