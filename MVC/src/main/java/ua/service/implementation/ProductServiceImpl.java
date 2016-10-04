package ua.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Product;
import ua.form.ProductForm;
import ua.form.filter.ProductFilterForm;
import ua.repository.ProducerRepository;
import ua.repository.ProductRepository;
import ua.repository.ProductTypeRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.ProductService;
import ua.service.implementation.specification.ProductFilterAdapter;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProducerRepository producerRepository;
	@Autowired
	private ProductTypeRepository productTypeRepository;
	@Autowired
	private FileWriter fileWriter;

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

		return null;
	}

	public void deleteBy(int id, String productName, double price,
			String productType, String producerName) {

	}

	@Override
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

	@Override
	public Product findProduct(Product product) {

		return productRepository.findProduct(product.getProducer().getName(),
				product.getProductType().getName(), product.getName(),
				product.getPrice());
	}

	@Override
	public Page<Product> findAllPagebleFilter(Pageable pageable,
			ProductFilterForm filter) {
		return productRepository.findAll(new ProductFilterAdapter(filter),
				pageable);
	}

	@Override
	public ProductForm findFormById(int id) {
		Product product = productRepository.findOneInited(id);
		ProductForm productForm = new ProductForm();
		productForm.setId(id);
		productForm.setProductType(product.getProductType());
		productForm.setProducer(product.getProducer());
		productForm.setTitle(product.getName());
		productForm.setPrice(String.valueOf(product.getPrice()));
		productForm.setPath(product.getPath());
		productForm.setVersion(product.getVersion());
		return productForm;
	}

	@Override
	public void save(ProductForm productForm) {
		Product product = new Product();
		product.setId(productForm.getId());
		product.setProductType(productForm.getProductType());
		product.setProducer(productForm.getProducer());
		product.setName(productForm.getTitle());
		product.setPrice(Double.valueOf(productForm.getPrice()));
		product.setPath(productForm.getPath());
		product.setVersion(productForm.getVersion());
		productRepository.saveAndFlush(product);
		String extension = fileWriter.write(Folder.PRODUCT, productForm.getFile(), product.getId());
		if(extension != null){
			product.setVersion(productForm.getVersion()+1);
			product.setPath(extension);
			productRepository.save(product);
		}
	}

	@Override
	public Product findProduct(ProductForm productForm) {
		return productRepository.findProduct(productForm.getProducer().getName(),
				productForm.getProductType().getName(), productForm.getTitle(),
				Double.valueOf(productForm.getPrice()));
	}

	@Override
	public List<Product> findAllSelected(List<Integer> productIds) {
		List<Product> selected = new ArrayList<Product>();
		for (Integer id : productIds) {
			selected.add(productRepository.findOneInited(id));
		}
		return selected;
	}

}
