package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.*;

import ua.form.ProductTypeFilterForm;

public interface ProductTypeService {
	void save(String name);

	void save(ProductType productType);

	ProductType findByName(String name);

	ProductType findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<ProductType> findAll();
	
	Page<ProductType> findAllPageableFilter(Pageable pageable,
			ProductTypeFilterForm form);
}
