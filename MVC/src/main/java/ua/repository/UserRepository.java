package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>,
		JpaSpecificationExecutor<User> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.wishList WHERE u.id=:id")
	User findById(@Param("id") int id);

	User findByLogin(String login);

	User findByMail(String mail);

	@Query("SELECT u FROM User u JOIN u.client c WHERE c.phone=:phone")
	User findByPhone(@Param("phone") String phone);

}
