package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import model.AnnotatedCustomer;
import model.Item;
import model.MyMixInForIgnoreType;
import model.User;
import model.Zoo;

public class JacksonAnnotatedMain {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        // serializeJson();
        // serializationWithTypeInfo();
        serializeMixin();
    }

    private static void serializeJson() throws Exception {
        AnnotatedCustomer customer = new AnnotatedCustomer();
        customer.id = 1000;
        customer.name = "John Doe";
        customer.json = "{\"attr\":false}";
        Map<String, String> properties = customer.getProperties();
        properties.put("phone", "1234567890");
        properties.put("email", "qxfkt@example.com");

        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(customer));
    }

    private static void serializationWithTypeInfo() throws Exception {
        Zoo zoo1 = new Zoo();

        zoo1.animal = new Zoo.Dog();
        zoo1.animal.name = "Max";
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(zoo1));
    }

    private static void serializeMixin() throws Exception {
        Item item = new Item();
        item.id = 1;
        item.itemName = "Example Item";
        item.owner = new User();
        item.owner.name =  "John Doe";
        // System.out.println(MAPPER.writeValueAsString(item));
        MAPPER.addMixIn(User.class, MyMixInForIgnoreType.class);
        System.out.println(MAPPER.writeValueAsString(item));
    }

}
