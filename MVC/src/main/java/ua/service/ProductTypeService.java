package ua.service;

import java.util.List;

import ua.entity.*;

public interface ProductTypeService {
	void save(String name);

	void save(ProductType productType);

	ProductType findByName(String name);

	ProductType findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<ProductType> findAll();
}
