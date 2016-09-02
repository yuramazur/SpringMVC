package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.ProductType;
import ua.service.ProductTypeService;

public class ProductTypeEditor extends PropertyEditorSupport {

	private final ProductTypeService productTypeService;

	public ProductTypeEditor(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ProductType productType = productTypeService.findById(Integer.valueOf(text));
		setValue(productType);
	}

}
