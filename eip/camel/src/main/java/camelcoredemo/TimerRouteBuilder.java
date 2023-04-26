package camelcoredemo;

import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;

public class TimerRouteBuilder extends RouteBuilder {
    static Logger LOG = LoggerFactory.getLogger(TimerRouteBuilder.class);

    @Override
    public void configure() {
        from("timer://timer1?period=1000")
                .process(msg -> LOG.info("Processing {}", msg));
    }
} 