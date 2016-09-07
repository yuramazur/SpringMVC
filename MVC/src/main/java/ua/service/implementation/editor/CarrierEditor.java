package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Carrier;
import ua.service.CarrierService;

public class CarrierEditor extends PropertyEditorSupport {

	private final CarrierService carrierService;

	public CarrierEditor(CarrierService carrierService) {
		this.carrierService = carrierService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Carrier carrier = carrierService.findById(Integer.valueOf(text));
		setValue(carrier);
	}

}
