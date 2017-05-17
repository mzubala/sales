package pl.com.bottega.sales.read;

import pl.com.bottega.common.domain.Money;

import java.time.LocalDate;

public class OrderDto {

    private LocalDate placedAt;
    private Money total;

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
}
