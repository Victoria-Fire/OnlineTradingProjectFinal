package by.academy.it.reunova.repository;

import by.academy.it.reunova.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer>, JpaSpecificationExecutor<Lot> {

    List<Lot> getLotOfBuyerDao(Integer buyerId);
    List<Lot> getLotOfBuyerStatusTrueForPurchaseDao(Integer buyerId);
    List<Lot> getLotOfBuyerStatusFalseForPurchaseDao(Integer buyerId);
}
