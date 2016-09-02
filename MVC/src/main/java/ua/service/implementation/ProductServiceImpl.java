package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Product;
import ua.repository.ProducerRepository;
import ua.repository.ProductRepository;
import ua.repository.ProductTypeRepository;
import ua.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProducerRepository producerRepository;
	@Autowired
	private ProductTypeRepository productTypeRepository;

	public void save(String productName, double price, String productType,
			String producerName) {
		Product product = new Product();
		product.setName(productName);
		product.setPrice(price);
		product.setProductType(productTypeRepository.findByName(productType));
		product.setProducer(producerRepository.findByName(producerName));
		if (productRepository.findProduct(product.getProducer().getName(),
				product.getProductType().getName(), product.getName(),
				product.getPrice()) == null) {
			productRepository.save(product);
		}

	}

	public void save(String productName, double price, int productTypeId,
			int producerId) {
		Product product = new Product();
		product.setName(productName);
		product.setPrice(price);
		product.setProductType(productTypeRepository.findById(productTypeId));
		product.setProducer(producerRepository.findById(producerId));
		if (productRepository.findProduct(product.getProducer().getName(),
				product.getProductType().getName(), product.getName(),
				product.getPrice()) == null) {
			productRepository.save(product);
		}

	}

	public List<Product> findAll() {

		return productRepository.fingAllProducerTypeInited();
	}

	public List<Product> findBy(int id, String productName, double price,
			String productType, String producerName) {
		//
		return null;
	}

	public void deleteBy(int id, String productName, double price,
			String productType, String producerName) {

	}

	public void deleteById(int id) {
		productRepository.delete(id);

	}

	@Override
	public Product findById(int id) {

		return productRepository.findOneInited(id);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
		
	}

}
