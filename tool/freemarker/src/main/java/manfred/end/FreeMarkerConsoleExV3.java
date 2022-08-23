package manfred.end;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerConsoleExV3 {

    public static void main(String[] args) throws IOException, TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.31"));

        cfg.setClassForTemplateLoading(FreeMarkerConsoleExV3.class, "/views");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("testV3.ftlh");


        try (StringWriter out = new StringWriter()) {

            template.process(null, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }
}