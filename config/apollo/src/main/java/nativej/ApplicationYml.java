package nativej;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * @author manfred
 * @since 2019-11-11 下午3:34
 */
public class ApplicationYml {
    public static void main(String[] args) {
        Config config = ConfigService.getConfig("application");
        String someKey = "xxx";
        String someDefaultValue = "someDefaultValueForTheKey";
        String value = config.getProperty(someKey, someDefaultValue);

        System.out.println(value);

    }
}
