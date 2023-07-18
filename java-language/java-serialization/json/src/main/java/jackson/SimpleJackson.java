package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;

/**
 * @author manfred on 2022/5/23.
 */
public class SimpleJackson {
    public static void main(String[] args) throws Exception {

        Customer customer = new Customer("manfred", 100);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(customer));

        System.out.println(objectMapper.readValue("null", String.class));
    }

}
