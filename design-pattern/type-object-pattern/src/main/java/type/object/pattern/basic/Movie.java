package type.object.pattern.basic;

/**
 * TypeClass - Movie
 */
public class Movie {

    private final String title;

    private final double rentalPrice;

    public Movie(String title, double rentalPrice) {
        this.title = title;
        this.rentalPrice = rentalPrice;
    }

    public String getTitle() {
        return title;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

}
