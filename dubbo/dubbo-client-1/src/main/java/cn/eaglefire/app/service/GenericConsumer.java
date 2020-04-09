package cn.eaglefire.app.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manfred
 * @since 2020-04-08 4:15 下午
 */
public class GenericConsumer {
    public static void main(String[] args) throws Exception {
        ApplicationConfig applicationConfig = new ApplicationConfig("hello-world-app-consumer");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        List<ReferenceConfig<GenericService>> referenceConfigs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ReferenceConfig<GenericService> referenceConfig = refer(applicationConfig, registryConfig);
            try {
                GenericService genericService = referenceConfig.get();
                System.out.println(genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"04985712094785102945780124758129457"}));
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
            }

            if (i % 5 != 0) {
                referenceConfigs.add(referenceConfig);
                // referenceConfig.destroy();
            }
            Thread.sleep(1000L);
        }
        referenceConfigs.forEach(ReferenceConfig::destroy);

        System.in.read();
    }

    public static ReferenceConfig<GenericService> refer(ApplicationConfig config, RegistryConfig registryConfig) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(config);
        referenceConfig.setRegistry(registryConfig);

        referenceConfig.setInterface("cn.eaglefire.app.service.DemoService");
        // referenceConfig.setVersion("1.0.0");
        referenceConfig.setGeneric(true);

        referenceConfig.setGroup("xxx");
        referenceConfig.setCheck(false);

        List<MethodConfig> methodConfigs = new ArrayList<>(1);
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHello");

        methodConfig.setRetries(2);
        methodConfig.setTimeout(1000);
        methodConfigs.add(methodConfig);
        referenceConfig.setMethods(methodConfigs);

        return referenceConfig;
    }
}
