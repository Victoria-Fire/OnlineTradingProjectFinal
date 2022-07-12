package by.academy.it.reunova.repository;

import by.academy.it.reunova.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer>, JpaSpecificationExecutor<OrderHistory> {
}
