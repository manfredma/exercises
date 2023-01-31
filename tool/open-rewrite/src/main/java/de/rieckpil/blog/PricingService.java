package de.rieckpil.blog;

import java.math.BigDecimal;

public class PricingService {

  private final ProductVerifier productVerifier;
  private final ProductReporter productReporter;

  public PricingService(ProductVerifier productVerifier, ProductReporter productReporter) {
    this.productVerifier = productVerifier;
    this.productReporter = productReporter;
  }

  public BigDecimal calculatePrice(String productName) {
    if (productVerifier.isCurrentlyInStockOfCompetitor(productName)) {
      productReporter.notify(productName);
      return new BigDecimal("99.99");
    }

    return new BigDecimal("149.99");
  }
}