package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Product;
import ua.service.ProductService;

public class ProductEditor extends PropertyEditorSupport {
	private final ProductService productService;

	public ProductEditor(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Product product = productService.findById(Integer.valueOf(text));
		setValue(product);
	}
	
	
}
