package manfred.spring.boot.config;

import manfred.spring.boot.config.boot.Boot;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.Bootstrapper;

public class MyBootstrapper implements Bootstrapper {
    @Override
    public void intitialize(BootstrapRegistry registry) {
        registry.register(Boot.class, context -> new Boot());
    }
}
