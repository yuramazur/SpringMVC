package ua.service;

import java.util.List;

import ua.entity.MyOrder;
import ua.entity.Product;


public interface OrderService {

	void save();

	MyOrder createOrder(int id, List<Integer> productIds);

	List<Product> findAllProducts(int id);
	
	
}
