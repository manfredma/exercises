package camelcoredemo;


import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.impl.*;
import org.apache.camel.builder.*;


public class TimerMain {
    static Logger LOG = LoggerFactory.getLogger(TimerMain.class);

    public static void main(String[] args) throws Exception {
        new TimerMain().run();
    }

    void run() throws Exception {
        final CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(createRouteBuilder());
        camelContext.setTracing(true);
        camelContext.start();


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    camelContext.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        waitForStop();
    }

    RouteBuilder createRouteBuilder() {
        return new TimerRouteBuilder();
    }

    void waitForStop() {
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
} 