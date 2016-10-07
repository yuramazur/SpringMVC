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

	@Query("SELECT u FROM User u WHERE u.login=:login")
	User findByLogin(@Param("login") String login);

	@Query("SELECT u FROM User u WHERE u.mail=:mail")
	User findByMail(@Param("mail") String mail);

	@Query("SELECT u FROM User u JOIN u.client c WHERE c.phone=:phone")
	User findByPhone(@Param("phone") String phone);

}
