package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.MyOrder;
import ua.entity.Product;

public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {
	
	@Query("SELECT p FROM MyOrder o JOIN o.products p LEFT JOIN FETCH p.producer LEFT JOIN FETCH p.productType WHERE o.id=:id")
	
	List<Product> findAllProducts(@Param("id") int id);

}
