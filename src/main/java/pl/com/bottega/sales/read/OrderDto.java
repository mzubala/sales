package pl.com.bottega.sales.read;

import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.domain.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDto {

    private LocalDate placedAt;
    private Money total;
    private BigDecimal totalValue;
    private String totalCurrency;

    public OrderDto(LocalDate placedAt, Money total) {
        this.placedAt = placedAt;
        this.total = total;
    }

    OrderDto() {}

    public LocalDate getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDate placedAt) {
        this.placedAt = placedAt;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void setTotalCurrency(String totalCurrency) {
        this.totalCurrency = totalCurrency;
    }
}
