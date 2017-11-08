package pl.com.bottega.sales.read;

import java.util.List;

public interface OrderBrowser {

    List<OrderDto> browse(Long customerId);

    byte[] exportOrders(Long customerId);

}
