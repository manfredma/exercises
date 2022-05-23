package jackson.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

public class Boot {
    @Test
    public void testJsonSchema2() {
        String failure = "{\"foo\":1234}";
        String Schema = "{\"type\": \"object\", \"properties\" : {\"foo\" : {\"type\" : " +
                "\"string\"}}}";
        ProcessingReport report = null;
        try {
            JsonNode data = JsonLoader.fromString(failure);
            JsonNode schema = JsonLoader.fromString(Schema);
            report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Assert.assertTrue(report.isSuccess());
        Iterator<ProcessingMessage> it = report.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
