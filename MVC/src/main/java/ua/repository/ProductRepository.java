package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product>{

	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.producer LEFT JOIN FETCH p.productType")
	List<Product> fingAllProducerTypeInited();

	@Query("SELECT p FROM Product p JOIN p.productType pt JOIN p.producer pr WHERE pt.name=:productType AND pr.name=:producer AND p.price=:price AND p.name=:name")
	Product findProduct(@Param("producer") String producer,
			@Param("productType") String productType,
			@Param("name") String name, @Param("price") double price);

	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.producer LEFT JOIN FETCH p.productType WHERE p.id=:id")
	Product findOneInited(@Param("id") int id);

}
