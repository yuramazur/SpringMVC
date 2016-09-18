package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.ProductType;

import ua.form.ProductTypeFilterForm;
import ua.repository.ProductTypeRepository;
import ua.service.ProductTypeService;
import ua.service.implementation.specification.ProductTypeFilterAdapter;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	ProductTypeRepository productTypeReposetory;

	public void save(String name) {
		if (productTypeReposetory.findByName(name) == null) {
			ProductType productType = new ProductType();
			productType.setName(name);
			productTypeReposetory.save(productType);
		}

	}

	public ProductType findByName(String name) {

		return productTypeReposetory.findByName(name);
	}

	public ProductType findById(int id) {

		return productTypeReposetory.findById(id);
	}

	public void deleteByName(String name) {
		if (productTypeReposetory.findByName(name) != null) {
			productTypeReposetory.delete(productTypeReposetory.findByName(name));
		}
	}

	public void deleteById(int id) {
		productTypeReposetory.delete(id);

	}

	public List<ProductType> findAll() {

		return productTypeReposetory.findAll();
	}

	@Override
	public void save(ProductType productType) {
		if (productTypeReposetory.findByName(productType.getName()) == null) {
			productTypeReposetory.save(productType);
		}

	}

	@Override
	public Page<ProductType> findAllPageableFilter(Pageable pageable,
			ProductTypeFilterForm form) {
		
		return productTypeReposetory.findAll(new ProductTypeFilterAdapter(form), pageable);
	}

}
