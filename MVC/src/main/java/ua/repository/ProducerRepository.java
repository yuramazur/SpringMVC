package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer>,JpaSpecificationExecutor<Producer> {

	Producer findByName(String name);

	Producer findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

}
