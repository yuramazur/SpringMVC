package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Client;
import ua.entity.Name;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query("SELECT n FROM Name n WHERE n.names=:name")
	Name findByName(@Param("name") String name);

	@Query("SELECT c FROM Client c LEFT JOIN FETCH c.name")
	List<Client> findAllInstance();

}
