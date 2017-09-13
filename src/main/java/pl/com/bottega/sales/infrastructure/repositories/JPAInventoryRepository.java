package pl.com.bottega.sales.infrastructure.repositories;

import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;
import pl.com.bottega.sales.domain.Inventory;
import pl.com.bottega.sales.domain.InventoryRepository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public class JPAInventoryRepository extends JPABaseRepository<Inventory> implements InventoryRepository {


    @Override
    public Optional<Inventory> loadForProduct(Long productId) {
        List<Inventory> inventories = entityManager.
                createQuery("FROM Inventory i WHERE i.productId = :pid").
                setParameter("pid", productId).
                setLockMode(LockModeType.PESSIMISTIC_WRITE).
                getResultList();
        return inventories.stream().findFirst();
    }
}
