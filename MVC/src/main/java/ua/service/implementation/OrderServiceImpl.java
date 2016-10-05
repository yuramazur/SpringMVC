package ua.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ua.entity.MyOrder;
import ua.entity.Product;

import ua.repository.DeliveryRepository;
import ua.repository.MyOrderRepository;
import ua.repository.ProductRepository;
import ua.repository.UserRepository;
import ua.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MyOrderRepository orderRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private UserRepository userRepositori;

	@Override
	public void save() {

	}

	@Override
	public MyOrder createOrder(int id, List<Integer> productIds) {
		MyOrder order = new MyOrder();
		order.setDate(LocalDate.now());
		order.setClient(userRepositori.findById(id).getClient());
		order.setProducts(productRepository.findAll(productIds));
		return order;
	}

	@Override
	public List<Product> findAllProducts(int id) {
		
		return orderRepository.findAllProducts(id);
	}

}
