package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Name;

public interface NameRepository extends JpaRepository<Name, Integer>,JpaSpecificationExecutor<Name> {

	Name findByNames(String nameNames);

	Name findById(int nameId);

	@Modifying
	@Query("DELETE FROM Name n WHERE n.names=:name")
	void deleteByNames(@Param("name") String name);

	void deleteById(int nameId);

	@Query("SELECT n FROM Name n WHERE n.names LIKE LOWER(:value)")
	List<Name> findAllByCoincidence(String value);

}
