package jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Customer;

/**
 * @author manfred on 2022/5/23.
 */
public class SimpleJackson {
    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String nullJson = objectMapper.writeValueAsString(null);
        if (nullJson != null && !nullJson.isEmpty()) {
            System.out.println("output: " + nullJson);
        }

        Customer customer = new Customer("manfred", 100);
        System.out.println(objectMapper.writeValueAsString(customer));

        System.out.println(objectMapper.readValue("null", String.class));
    }

}
