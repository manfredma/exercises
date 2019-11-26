package customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = CustomerConfig.class)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepo;

    @Test
    public void createSimpleCustomer() {

        Customer customer = new Customer();
        customer.dob = LocalDate.of(1904, 5, 14);
        customer.firstName = "Albert";

        Customer saved = customerRepo.save(customer);

        // assertThat(saved.id).isNotNull();
        System.out.println(saved.id);

        saved.firstName = "Hans Albert";

        customerRepo.save(saved);

        Optional<Customer> reloaded = customerRepo.findById(saved.id);

        // assertThat(reloaded).isNotEmpty();

        // assertThat(reloaded.get().firstName).isEqualTo("Hans Albert");
        reloaded.ifPresent(a -> System.out.println(a.getFirstName()));
    }

    @Test
    public void findByName() {

        Customer customer = new Customer();
        customer.dob = LocalDate.of(1904, 5, 14);
        customer.firstName = "Albert";

        Customer saved = customerRepo.save(customer);

        System.out.println(saved.getId());
        customer.id= null;
        customer.firstName = "Bertram";

        customerRepo.save(customer);

        customer.id= null;
        customer.firstName = "Beth";

        customerRepo.save(customer);

        List<Customer> customers = customerRepo.findByName("bert");
        customers.forEach(System.out::println);
    }

}