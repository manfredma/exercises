package manfred.end.spring.boot.tomcat.model;

import javax.validation.constraints.NotNull;
import manfred.end.spring.boot.tomcat.constraint.LengthConstraint;

public class User {

    @LengthConstraint(min = 12, max = 15)
    @NotNull(message = "Please enter a valid email Id")
    private String email;
    private String name;
    private String address;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
