package by.academy.it.reunova.repository;

import by.academy.it.reunova.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>, JpaSpecificationExecutor<Buyer> {
}
