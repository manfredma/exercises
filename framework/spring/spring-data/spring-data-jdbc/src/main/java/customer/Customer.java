package customer;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * @author manfred
 * @since 2019-11-26 下午7:45
 */
public class Customer {
    @Id
    Long id;
    String firstName;
    LocalDate dob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}