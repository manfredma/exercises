package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import model.Order;

/**
 * @author manfred on 2022/5/23.
 */
public class Boot {
    public static void main(String[] args) throws Exception {

        Customer customer = new Customer("manfred", 100);
        Order order = new Order();

        order.setCustomer(customer);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(customer));
    }

}
