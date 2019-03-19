package camelcoredemo;

import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;

public class TimerRouteBuilder extends RouteBuilder {
    static Logger LOG = LoggerFactory.getLogger(TimerRouteBuilder.class);

    public void configure() {
        from("timer://timer1?period=1000")
                .process(new Processor() {
                    public void process(Exchange msg) {
                        LOG.info("Processing {}", msg);
                    }
                });
    }
} 