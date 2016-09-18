package ua.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Carrier;


public interface CarrierRepository extends JpaRepository<Carrier, Integer>,JpaSpecificationExecutor<Carrier> {

	Carrier findByName(String name);

	Carrier findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	

}
