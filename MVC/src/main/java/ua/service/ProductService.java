package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Product;
import ua.form.ProductForm;
import ua.form.filter.ProductFilterForm;

public interface ProductService {
	void save(String productName, double price, String productType,
			String producerName);

	void save(String productName, double price, int productTypeId,
			int producerId);

	List<Product> findAll();

	List<Product> findBy(int id, String productName, double price,
			String productType, String producerName);

	void deleteBy(int id, String productName, double price, String productType,
			String producerName);

	void deleteById(int id);

	Product findById(int id);

	void save(Product product);

	Product findProduct(Product product);

	Page<Product> findAllPagebleFilter(Pageable pageable, ProductFilterForm filter);

	ProductForm findFormById(int id);

	void save(ProductForm productForm);

	Product findProduct(ProductForm productForm);

}
