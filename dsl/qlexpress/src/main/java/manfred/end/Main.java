package manfred.end;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class Main {
    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", "1L");
        context.put("b", 2);
        context.put("c", 3);


        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r.getClass() + " " + r);

        Customer c = new Customer();
        c.setName("John");
        context.put("c", c);

        r = runner.execute("c.name", context, null, true, false);
        System.out.println(r);

        context.put("c.c", "new");

        r = runner.execute("c.c", context, null, true, false);
        System.out.println(r);
    }
}