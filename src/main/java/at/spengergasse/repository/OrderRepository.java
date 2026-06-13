package at.spengergasse.repository;


import at.spengergasse.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query("SELECT COALESCE(MAX(o.orderId), 0) + 1 FROM Order o")
    Long findNextOrderId();
}
