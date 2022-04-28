package type.object.pattern.basic;

public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void chargeForRental(double rental) {
        System.out.println("chargeForRental: " + rental + ", customer: " + name);
    }

    public String getName() {
        return name;
    }
}
