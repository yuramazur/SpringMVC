package ua.service;

import java.util.List;

import ua.entity.Delivery;

public interface DeliveryService {

	void save(int cityId, int carrierId, String numCerrDep);

	void save(String cityName, String carrierName, String numCerrDep);

	List<Delivery> findAll();

	void deleteById(int id);

	void save(Delivery delivery);

	Delivery findById(int id);

	Delivery findDelivery(Delivery delivery);
}
