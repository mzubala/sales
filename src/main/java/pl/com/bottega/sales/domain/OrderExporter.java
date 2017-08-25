package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

import java.util.Map;

public interface OrderExporter {

    void addLines(Map<String, Integer> lines);

    void addTotal(Money total);

}
