package pl.com.bottega.sales.read;

import pl.com.bottega.common.domain.Money;

public class OrderItemDto {

    private String productName;
    private Integer count;
    private Money itemTotal;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Money getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Money itemTotal) {
        this.itemTotal = itemTotal;
    }
}
