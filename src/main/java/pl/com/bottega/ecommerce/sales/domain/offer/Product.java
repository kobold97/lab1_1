package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private String productId;

	public Money productPrice = null;

	private String productName;

	private Date productSnapshotDate;

	private String productType;

	public Product(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
			String productType) {
		this.productId = productId;
		this.productPrice = new Money(productPrice, "PLN");
		this.productName = productName;
		this.productSnapshotDate = productSnapshotDate;
		this.productType = productType;
	}

	public String getProductId() {
		return productId;
	}

	public BigDecimal getProductPrice() {
		return productPrice.value;
	}

	public String getProductName() {
		return productName;
	}

	public Date getProductSnapshotDate() {
		return productSnapshotDate;
	}

	public String getProductType() {
		return productType;
	}
}
