package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import ua.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.wishList WHERE u.id=:id")
	User findById(@Param("id") int id);

	User findByLogin(String login);
		
}
