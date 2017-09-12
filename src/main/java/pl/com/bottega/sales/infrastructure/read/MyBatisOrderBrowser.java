package pl.com.bottega.sales.infrastructure.read;

import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;
import pl.com.bottega.sales.read.SearchResults;

import java.util.List;

public class MyBatisOrderBrowser implements OrderBrowser {

    @Override
    public SearchResults<OrderDto> browse(Long customerId, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public byte[] exportOrders(Long customerId) {
        return new byte[0];
    }
}
