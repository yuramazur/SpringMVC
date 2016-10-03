package ua.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Product;
import ua.entity.User;
import ua.service.implementation.specification.ProductFilterAdapter;

public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<Product> {
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.wishList WHERE u.id=:id")
	User findById(@Param("id") int id);

	User findByLogin(String login);
	
	@Query("SELECT p FROM User u JOIN u.wishList p WHERE u.id=:id")
	Page<Product>findWishList(@Param("id") int id, ProductFilterAdapter filter, Pageable pageable);
}
