package ua.service.implementation;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Delivery;
import ua.entity.MyOrder;
import ua.entity.Product;
import ua.form.DeliveryForm;
import ua.repository.DeliveryRepository;
import ua.repository.MyOrderRepository;
import ua.repository.ProductRepository;
import ua.repository.UserRepository;
import ua.service.OrderService;
import ua.service.UserService;

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
	@Autowired
	private UserService userService;

	@Override
	public void save() {

	}

	@Override
	public MyOrder createOrder(Principal principal, List<Integer> productIds) {
		MyOrder order = new MyOrder();
		order.setDate(LocalDate.now());
		order.setClient(userRepositori.findById(
				Integer.valueOf(principal.getName())).getClient());
		order.setProducts(productRepository.findAll(productIds));
		orderRepository.save(order);
		return order;
	}

	@Override
	public List<Product> findAllProducts(int id) {

		return productRepository.findOrderProducts(id);
	}

	@Override
	public void saveOrder(int id, DeliveryForm deliveryForm) {
		Delivery delivery = new Delivery();
		MyOrder order = new MyOrder();
		if (deliveryRepository.findDelivery(deliveryForm.getCity().getName(),
				deliveryForm.getCarrier().getName(),
				Integer.valueOf(deliveryForm.getNumberDepartment())) != null) {
			delivery = deliveryRepository.findDelivery(deliveryForm.getCity()
					.getName(), deliveryForm.getCarrier().getName(), Integer
					.valueOf(deliveryForm.getNumberDepartment()));
		}else{
			delivery.setCity(deliveryForm.getCity());
			delivery.setCarrier(deliveryForm.getCarrier());
			delivery.setNumCerrDep(Integer.valueOf(deliveryForm.getNumberDepartment()));
			deliveryRepository.save(delivery);
		}
		order = orderRepository.findOne(id);
		order.setDelivery(delivery);
		orderRepository.save(order);
	}

}
