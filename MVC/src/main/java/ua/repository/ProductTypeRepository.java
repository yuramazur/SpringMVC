package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>,JpaSpecificationExecutor<ProductType>{

	ProductType findByName(String name);

	ProductType findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

}
