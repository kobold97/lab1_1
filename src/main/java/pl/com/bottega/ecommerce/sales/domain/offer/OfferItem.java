/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

	private Discount discount = null;
	public Product product = null;
    private Money totalCost;
    private int quantity;

	public OfferItem(Product product, int quantity) {
		this(product, quantity, null, null);
	}

	public OfferItem(Product product, int quantity, Discount discount) {

		this.product = product;
        this.quantity = quantity;
		this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
		if (discount.discount.value != null) {
			discountValue = discountValue.subtract(discount.discount.value);
		}
                                 .subtract(discountValue);
        this.totalCost = new Money(product.getProductPrice()
                                  .multiply(new BigDecimal(quantity))
                                  .subtract(discountValue),
        "PLN");
        
        this.totalCost.value = product.getProductPrice()
                                       .multiply(new BigDecimal(quantity))
                                       .subtract(discountValue);
	}

	public BigDecimal getTotalCost() {
		return discount.totalCost.value;
	}

	public String getTotalCostCurrency() {
		return currency;
	}

	public BigDecimal getDiscount() {
		return discount.discount.value;
	}

	public String getDiscountCause() {
		return discount.discountCause;
	}

	public int getQuantity() {
		return discount.quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, discount, discount.discountCause, product.getProductId(),
				product.getProductName(), product.getProductPrice(), product.getProductSnapshotDate(),
				product.getProductType(), discount.quantity, discount.totalCost.value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OfferItem other = (OfferItem) obj;
		return Objects.equals(currency, other.currency) && Objects.equals(discount, other.discount)
				&& Objects.equals(discount.discountCause, other.discount.discountCause)
				&& Objects.equals(product.getProductId(), other.product.getProductId())
				&& Objects.equals(product.getProductName(), other.product.getProductName())
				&& Objects.equals(product.getProductPrice(), other.product.getProductPrice())
				&& Objects.equals(product.getProductSnapshotDate(), other.product.getProductSnapshotDate())
				&& Objects.equals(product.getProductType(), other.product.getProductType())
				&& discount.quantity == other.discount.quantity
				&& Objects.equals(discount.totalCost.value, other.discount.totalCost.value);
	}

	/**
	 *
	 * @param item
	 * @param delta acceptable percentage difference
	 * @return
	 */
	public boolean sameAs(OfferItem other, double delta) {
		if (product.getProductPrice() == null) {
			if (other.product.getProductPrice() != null) {
				return false;
			}
		} else if (!product.getProductPrice().equals(other.product.getProductPrice())) {
			return false;
		}
		if (product.getProductName() == null) {
			if (other.product.getProductName() != null) {
				return false;
			}
		} else if (!product.getProductName().equals(other.product.getProductName())) {
			return false;
		}

		if (product.getProductId() == null) {
			if (other.product.getProductId() != null) {
				return false;
			}
		} else if (!product.getProductId().equals(other.product.getProductId())) {
			return false;
		}
		if (product.getProductType() != other.product.getProductType()) {
			return false;
		}

		if (discount.quantity != other.discount.quantity) {
			return false;
		}

		BigDecimal max;
		BigDecimal min;
		if (discount.totalCost.value.compareTo(other.discount.totalCost.value) > 0) {
			max = discount.totalCost.value;
			min = other.discount.totalCost.value;
		} else {
			max = other.discount.totalCost.value;
			min = discount.totalCost.value;
		}

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

}
