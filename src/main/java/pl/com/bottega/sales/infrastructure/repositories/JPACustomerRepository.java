package pl.com.bottega.sales.infrastructure.repositories;

import org.springframework.stereotype.Component;
import pl.com.bottega.common.domain.repositories.Repository;
import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;
import pl.com.bottega.sales.domain.Customer;
import pl.com.bottega.sales.domain.repositories.CustomerRepository;

@Component
public class JPACustomerRepository extends JPABaseRepository<Customer> implements CustomerRepository {
}
