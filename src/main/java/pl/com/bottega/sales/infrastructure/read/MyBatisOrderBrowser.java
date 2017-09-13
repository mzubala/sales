package pl.com.bottega.sales.infrastructure.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;
import pl.com.bottega.sales.read.SearchResults;

import java.util.List;

@Component
public class MyBatisOrderBrowser implements OrderBrowser {

    @Autowired
    private MyBatisOrderMapper mapper;

    @Override
    public SearchResults<OrderDto> browse(Long customerId, int pageNumber, int pageSize) {
        Long total = mapper.totalCount(customerId);
        long pagesCount = (total / pageSize + (total % pageSize > 0 ? 1 : 0));
        List<OrderDto> page = mapper.orders(customerId, (pageNumber - 1) * pageSize, pageSize);
        return new SearchResults<OrderDto>(page, (int) pagesCount, pageNumber, pageSize, total);
    }

    @Override
    public byte[] exportOrders(Long customerId) {
        return new byte[0];
    }
}
