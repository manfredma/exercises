package lambdasinaction.dsl;

import lambdasinaction.dsl.model.Order;
import lambdasinaction.dsl.model.Tax;

import java.util.function.Function;

public class TaxCalculator {

    public static double calculate( Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge ) {
        double value = order.getValue();
        if (useRegional) value = Tax.regional(value);
        if (useGeneral) value = Tax.general(value);
        if (useSurcharge) value = Tax.surcharge(value);
        return value;
    }

    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator withTaxRegional() {
        useRegional = true;
        return this;
    }

    public TaxCalculator withTaxGeneral() {
        useGeneral= true;
        return this;
    }

    public TaxCalculator withTaxSurcharge() {
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order) {
        return calculate( order, useRegional, useGeneral, useSurcharge );
    }

    public Function<Double, Double> taxFuncion = Function.identity();

    public TaxCalculator with(Function<Double, Double> f) {
        taxFuncion.andThen( f );
        return this;
    }

    public double calculateF(Order order) {
        return taxFuncion.apply( order.getValue() );
    }

    public static void main(String[] args) {
        Order order = new Order();

        double value = TaxCalculator.calculate( order, true, false, true );

        value = new TaxCalculator().withTaxRegional()
                                   .withTaxSurcharge()
                                   .calculate( order );

        value = new TaxCalculator().with(Tax::regional)
                                   .with(Tax::surcharge)
                                   .calculate( order );
    }
}