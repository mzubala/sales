package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

public interface OrderBuilder {

    void addStatus(String status);

    void addTotal(Money money);

}
