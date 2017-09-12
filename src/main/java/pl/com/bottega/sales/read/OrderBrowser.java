package pl.com.bottega.sales.read;

import java.util.List;

public interface OrderBrowser {

    SearchResults<OrderDto> browse(Long customerId, int pageNumber, int pageSize);

    byte[] exportOrders(Long customerId);

}
