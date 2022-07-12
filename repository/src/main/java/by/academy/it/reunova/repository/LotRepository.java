package by.academy.it.reunova.repository;

import by.academy.it.reunova.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer>, JpaSpecificationExecutor<Lot> {
}
