package ua.service.implementation;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Delivery;
import ua.entity.MyOrder;
import ua.entity.Product;
import ua.form.AddOrderForm;
import ua.form.DeliveryForm;
import ua.form.filter.OrderFilterForm;
import ua.repository.DeliveryRepository;
import ua.repository.MyOrderRepository;
import ua.repository.ProductRepository;
import ua.repository.UserRepository;
import ua.service.MailSender;
import ua.service.OrderService;
import ua.service.UserService;
import ua.service.implementation.specification.OrderFilterAdapter;

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
	@Autowired
	private MailSender mailSender;

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
		} else {
			delivery.setCity(deliveryForm.getCity());
			delivery.setCarrier(deliveryForm.getCarrier());
			delivery.setNumCerrDep(Integer.valueOf(deliveryForm
					.getNumberDepartment()));
			deliveryRepository.save(delivery);
		}
		order = orderRepository.findOne(id);
		order.setDelivery(delivery);
		orderRepository.save(order);
	}

	@Override
	public void saveOrder(DeliveryForm deliveryForm, AddOrderForm addForm,
			Principal principal) {
		MyOrder order = new MyOrder();
		Delivery delivery = new Delivery();
		if (deliveryRepository.findDelivery(deliveryForm.getCity().getName(),
				deliveryForm.getCarrier().getName(),
				Integer.valueOf(deliveryForm.getNumberDepartment())) != null) {
			delivery = deliveryRepository.findDelivery(deliveryForm.getCity()
					.getName(), deliveryForm.getCarrier().getName(), Integer
					.valueOf(deliveryForm.getNumberDepartment()));
		} else if(deliveryRepository.findDelivery(deliveryForm.getCity().getName(),
				deliveryForm.getCarrier().getName(),
				Integer.valueOf(deliveryForm.getNumberDepartment())) == null) {
			delivery.setCity(deliveryForm.getCity());
			delivery.setCarrier(deliveryForm.getCarrier());
			delivery.setNumCerrDep(Integer.valueOf(deliveryForm
					.getNumberDepartment()));
			deliveryRepository.save(delivery);
		}
		order.setDelivery(delivery);
		order.setClient(userService.findById(Integer.valueOf(principal.getName())).getClient());
		order.setDate(LocalDate.now());
		orderRepository.save(order);
		order.setProducts(productRepository.findAllInited(addForm.getProductIds()));
		orderRepository.save(order);
		String content = "Congratulations, you made the order";
		String email = userService.findById(Integer.valueOf(principal.getName())).getMail();
		String mailBody ="";
		double totalPrice = 0;
		for (Product product : order.getProducts()) {
			mailBody =mailBody+product.getProductType().getName()+" "+product.getProducer().getName()+" "+product.getName()+" "+product.getPrice()+"\n";
			totalPrice =totalPrice+product.getPrice();
		}
		mailBody = mailBody+"\n"+"Total price: "+totalPrice+" UAH";
		mailSender.sendMail(content, email, mailBody);
		
	}

	@Override
	public Page<MyOrder> findAllPageable(OrderFilterForm form, Pageable pageable) {
		
		return orderRepository.findAll(new OrderFilterAdapter(form),pageable);
	}

	

}
