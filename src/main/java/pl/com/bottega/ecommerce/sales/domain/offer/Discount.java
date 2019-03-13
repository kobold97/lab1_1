package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {

	public Money totalCost;
	public int quantity;
	public Money discount;
	public String discountCause;

	public Discount(BigDecimal productPrice, BigDecimal discount, int quantity, String discountCause) {
		this.quantity = quantity;
		this.discount = new Money(discount, "PLN");
		this.discountCause = discountCause;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount != null) {
			discountValue = discountValue.subtract(discount);
		}

		this.totalCost = new Money(productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue), "PLN");

	}
}
