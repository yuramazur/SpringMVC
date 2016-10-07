package ua.service;

import java.security.Principal;
import java.util.List;









import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.MyOrder;
import ua.entity.Product;
import ua.form.AddOrderForm;
import ua.form.DeliveryForm;
import ua.form.filter.OrderFilterForm;


public interface OrderService {

	void save();

	MyOrder createOrder(Principal principal, List<Integer> productIds);

	List<Product> findAllProducts(int id);

	void saveOrder(int id, DeliveryForm deliveryForm);

	void saveOrder(DeliveryForm deliveryForm, AddOrderForm addForm,
			Principal principal);

	Page<MyOrder> findAllPageable(OrderFilterForm form,Pageable pageable);
	
	
}
