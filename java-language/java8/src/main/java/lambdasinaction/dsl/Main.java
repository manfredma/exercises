package lambdasinaction.dsl;

import lambdasinaction.dsl.model.Order;
import lambdasinaction.dsl.model.Stock;
import lambdasinaction.dsl.model.Trade;

import static lambdasinaction.dsl.MethodChainingOrderBuilder.forCustomer;
import static lambdasinaction.dsl.NestedFunctionOrderBuilder.*;

public class Main {

    public void plain() {
        Order order = new Order();
        order.setCustomer( "BigBank" );

        Trade trade1 = new Trade();
        trade1.setType( Trade.Type.BUY );

        Stock stock1 = new Stock();
        stock1.setSymbol( "IBM" );
        stock1.setMarket( "NYSE" );

        trade1.setStock( stock1 );
        trade1.setPrice( 125.00 );
        trade1.setQuantity( 80 );
        order.addTrade( trade1 );

        Trade trade2 = new Trade();
        trade2.setType( Trade.Type.BUY );

        Stock stock2 = new Stock();
        stock2.setSymbol( "GOOGLE" );
        stock2.setMarket( "NASDAQ" );

        trade2.setStock( stock2 );
        trade2.setPrice( 375.00 );
        trade2.setQuantity( 50 );
        order.addTrade( trade2 );
    }

    public void methodChaining() {
        Order order = forCustomer( "BigBank" )
                        .buy( 80 ).stock( "IBM" ).on( "NYSE" ).at( 125.00 )
                        .sell( 50 ).stock( "GOOGLE" ).on( "NASDAQ" ).at( 375.00 )
                        .end();

    }

    public void nestedFunction() {
        Order order = order("BigBank",
                            buy(80,
                                stock( "IBM", on( "NYSE" ) ),
                                at(125.00)),
                            sell(50,
                                 stock("GOOGLE", on("NASDAQ")),
                                 at(375.00))
                           );
    }

    public void lambda() {
        Order order = LambdaOrderBuilder.order( o -> {
            o.forCustomer( "BigBank" );
            o.buy( t -> {
                t.quantity( 80 );
                t.price( 125.00 );
                t.stock( s -> {
                    s.symbol( "IBM" );
                    s.market( "NYSE" );
                } );
            });
            o.sell( t -> {
                t.quantity( 50 );
                t.price( 375.00 );
                t.stock( s -> {
                    s.symbol( "GOOGLE" );
                    s.market( "NASDAQ" );
                } );
            });
        } );
    }

}