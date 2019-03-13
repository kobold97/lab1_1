package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money {
	public BigDecimal value;
	public String currency;

	public Money(BigDecimal value, String currency) {
		this.value = value;
		this.currency = currency;
	}

}
