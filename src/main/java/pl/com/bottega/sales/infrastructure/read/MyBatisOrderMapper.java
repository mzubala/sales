package pl.com.bottega.sales.infrastructure.read;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pl.com.bottega.sales.read.OrderDto;

import java.util.List;

@Mapper
public interface MyBatisOrderMapper {

    @Select("SELECT count(*) FROM Orders WHERE customer_id = #{customerId}")
    Long totalCount(Long customerId);

    @Select("SELECT placed_at, value as totalValue, currency_code as totalCurrency " +
            "FROM Orders " +
            "WHERE customer_id = #{customerId} OFFSET #{offset} LIMIT #{limit}")
    List<OrderDto> orders(@Param("customerId") Long customerId,
                          @Param("offset") int offset,
                          @Param("limit") int limit);


}
