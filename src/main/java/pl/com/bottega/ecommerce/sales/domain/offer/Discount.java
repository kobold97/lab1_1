package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {

//	public Money totalCost;
	public Money discount;
	public String discountCause;

	public Discount(BigDecimal discount, String discountCause, String currency) {
		this.discount = new Money(discount, "PLN");
		this.discountCause = discountCause;
	}
}
