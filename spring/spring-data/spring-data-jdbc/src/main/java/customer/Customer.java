package customer;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * @author manfred
 * @since 2019-11-26 下午7:45
 */
@Data
public class Customer {
    @Id
    Long id;
    String firstName;
    LocalDate dob;
}