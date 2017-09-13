package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.repositories.Repository;

import java.util.Optional;

public interface InventoryRepository extends Repository<Inventory> {

    Optional<Inventory> loadForProduct(Long productId);

}
