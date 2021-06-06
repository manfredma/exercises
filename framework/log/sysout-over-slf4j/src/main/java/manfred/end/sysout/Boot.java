package manfred.end.sysout;

import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

/**
 * @author manfred on 2021/6/5.
 */
public class Boot {
    public static void main(String[] args) {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        System.out.println("xxxxxxx");
        System.err.println("xxxxxxx");
    }
}
