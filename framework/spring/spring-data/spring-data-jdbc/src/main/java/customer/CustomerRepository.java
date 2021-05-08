package customer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select id, first_name, dob from customer where upper(first_name) like '%' || upper(:name) || '%' ")
    List<Customer> findByName(@Param("name") String name);
}
