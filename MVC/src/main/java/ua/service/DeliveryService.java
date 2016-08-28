package ua.service;

import java.util.List;

import ua.entity.Delivery;

public interface DeliveryService {

	void save(int cityId, int carrierId, int numCerrDep);

	void save(String cityName, String carrierName, int numCerrDep);

	List<Delivery> findAll();

	void deleteById(int id);
}
