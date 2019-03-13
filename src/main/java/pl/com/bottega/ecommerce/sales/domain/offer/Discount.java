package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {
	
    public BigDecimal totalCost;
    public int quantity;
    public BigDecimal discount;
    public String discountCause;

	
	public Discount(BigDecimal productPrice, BigDecimal discount, int quantity, String discountCause
) {
	this.quantity = quantity;
	this.discount = discount;
    this.discountCause = discountCause;

    BigDecimal discountValue = new BigDecimal(0);
    if (discount != null) {
        discountValue = discountValue.subtract(discount);
    }

    this.totalCost = productPrice.multiply(new BigDecimal(quantity))
                                 .subtract(discountValue);
	}
}
