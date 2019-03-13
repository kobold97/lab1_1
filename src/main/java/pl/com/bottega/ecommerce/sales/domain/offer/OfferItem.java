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

    // product
//    private String productId;
//
//    private BigDecimal productPrice;
//
//    private String productName;
//
//    private Date productSnapshotDate;
//
//    private String productType;
	public Product product = null;
	
    private int quantity;

    private BigDecimal totalCost;

    private String currency;

    // discount
    private String discountCause;

    private BigDecimal discount;

    public OfferItem(Product product,
            int quantity) {
        this(product, quantity, null, null);
    }

    public OfferItem(Product product,
            int quantity, BigDecimal discount, String discountCause) {

    	this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

        this.totalCost = product.getProductPrice().multiply(new BigDecimal(quantity))
                                     .subtract(discountValue);
    }

//    public String getProductId() {
//        return productId;
//    }
//
//    public BigDecimal getProductPrice() {
//        return productPrice;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public Date getProductSnapshotDate() {
//        return productSnapshotDate;
//    }
//
//    public String getProductType() {
//        return productType;
//    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return currency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, discount, discountCause, product.getProductId(), product.getProductName(), product.getProductPrice(), product.getProductSnapshotDate(), product.getProductType(),
                quantity, totalCost);
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
        return Objects.equals(currency, other.currency)
               && Objects.equals(discount, other.discount)
               && Objects.equals(discountCause, other.discountCause)
               && Objects.equals(product.getProductId(), other.product.getProductId())
               && Objects.equals(product.getProductName(), other.product.getProductName())
               && Objects.equals(product.getProductPrice(), other.product.getProductPrice())
               && Objects.equals(product.getProductSnapshotDate(), other.product.getProductSnapshotDate())
               && Objects.equals(product.getProductType(), other.product.getProductType())
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param item
     * @param delta
     *            acceptable percentage difference
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

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
