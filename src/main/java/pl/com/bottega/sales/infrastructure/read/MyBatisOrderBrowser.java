package pl.com.bottega.sales.infrastructure.read;

import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;

import java.util.List;

public class MyBatisOrderBrowser implements OrderBrowser {
    @Override
    public List<OrderDto> browse(Long customerId) {
        return null;
    }
}
