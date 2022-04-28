package type.object.pattern.basic;

/**
 * Class - Videotape
 */
public class Videotape {

    private final Movie movie;

    private Customer renter;

    private boolean isRented = false;

    public Videotape(Movie movie) {
        this.movie = movie;
    }

    public void rentTo(Customer customer) {
        System.out.println(customer.getName() + " rental " + movie.getTitle());
        isRented = true;
        this.renter = customer;
        renter.chargeForRental(this.movie.getRentalPrice());
    }

    public Customer getRenter() {
        return renter;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isRented() {
        return isRented;
    }
}
