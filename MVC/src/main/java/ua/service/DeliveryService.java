package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Delivery;
import ua.form.DeliveryFilterForm;

public interface DeliveryService {

	void save(int cityId, int carrierId, int numCerrDep);

	void save(String cityName, String carrierName, int numCerrDep);

	List<Delivery> findAll();

	void deleteById(int id);

	void save(Delivery delivery);

	Delivery findById(int id);

	Delivery findDelivery(Delivery delivery);

	Page<Delivery> findAllPagebleFilter(Pageable pageable, DeliveryFilterForm filter);
}
