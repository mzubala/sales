package pl.com.bottega.sales.infrastructure.repositories;

import org.springframework.stereotype.Component;
import pl.com.bottega.common.domain.repositories.Repository;
import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;
import pl.com.bottega.sales.domain.Product;
import pl.com.bottega.sales.domain.repositories.ProductRepository;

@Component
public class JPAProductRepository extends JPABaseRepository<Product> implements ProductRepository {

}
