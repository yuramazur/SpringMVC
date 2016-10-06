package ua.service;

import java.security.Principal;
import java.util.List;





import ua.entity.MyOrder;
import ua.entity.Product;
import ua.form.DeliveryForm;


public interface OrderService {

	void save();

	MyOrder createOrder(Principal principal, List<Integer> productIds);

	List<Product> findAllProducts(int id);

	void saveOrder(int id, DeliveryForm deliveryForm);
	
	
}
