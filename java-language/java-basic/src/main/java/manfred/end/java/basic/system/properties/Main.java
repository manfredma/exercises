package manfred.end.java.basic.system.properties;

/**
 * @author Manfred since 2019/5/8
 */
public class Main {
    public static void main(String[] args) {
        for (String stringPropertyName : System.getProperties().stringPropertyNames()) {
            System.out.println(stringPropertyName + "=" + System.getProperty(stringPropertyName));
        }
    }
}
