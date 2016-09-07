package ua.service;

import java.util.List;

import ua.entity.Product;

public interface ProductService {
	void save(String productName, String price, String productType,
			String producerName);

	void save(String productName, String price, int productTypeId,
			int producerId);

	List<Product> findAll();

	List<Product> findBy(int id, String productName, String price,
			String productType, String producerName);

	void deleteBy(int id, String productName, String price, String productType,
			String producerName);
	void deleteById(int id);
		
	Product findById(int id);

	void save(Product product);

	Product findProduct(Product product);
	
}
