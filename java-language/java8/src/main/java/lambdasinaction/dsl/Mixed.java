package lambdasinaction.dsl;

import lambdasinaction.dsl.model.Order;

import static lambdasinaction.dsl.MixedBuilder.buy;
import static lambdasinaction.dsl.MixedBuilder.sell;
import static lambdasinaction.dsl.MixedBuilder.forCustomer;

public class Mixed {
    public void mixed() {
        Order order =
                forCustomer( "BigBank",
                             buy( t -> t.quantity( 80 )
                                        .stock( "IBM" )
                                        .on( "NYSE" )
                                        .at( 125.00 )),
                             sell( t -> t.quantity( 50 )
                                         .stock( "GOOGLE" )
                                         .on( "NASDAQ" )
                                         .at( 125.00 )) );

    }
}