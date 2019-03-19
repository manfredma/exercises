import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.impl.DefaultCamelContext;

public class MoveAndProcessFileWithCamel {

    public static class FileConvertProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            try {
                // 读入数据
                InputStream body = exchange.getIn().getBody(InputStream.class);

                // 中间处理
                StringBuilder builder = new StringBuilder();

                BufferedReader in = new BufferedReader(new InputStreamReader(body));
                String str = in.readLine();
                while (str != null) {
                    System.out.println(str);
                    builder.append("Hello " + str + "\n");
                    str = in.readLine();
                }
                // 修改输出的文件名
                GenericFile file = exchange.getProperty("CamelFileExchangeFile", GenericFile.class);
                exchange.getOut().setHeader(Exchange.FILE_NAME, file.getFileName());

                // 写入结果
                exchange.getOut().setBody(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                Processor processor = new FileConvertProcessor();
                from("file:d://tmp//inbox?delay=5000").process(processor).to("file:d://tmp//outbox");
            }
        });
        context.start();
        boolean loop = true;

        while (loop) {
            Thread.sleep(25000);
        }
        context.stop();
    }
}