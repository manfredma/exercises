package nativej;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * @author manfred
 * @since 2019-11-11 下午3:20
 */
public class ApplicationGet {
    public static void main(String[] args) {
        // config instance is singleton for each namespace and is never null
        Config config = ConfigService.getAppConfig();

        String someKey = "test";
        String someDefaultValue = "someDefaultValueForTheKey";
        String value = config.getProperty(someKey, someDefaultValue);

        System.out.println(value);
    }
}
