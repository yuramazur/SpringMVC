package ua.service;

import java.util.List;

import ua.entity.*;

public interface CarrierService {
	void save(String name);

	void save(Carrier carrier);

	Carrier findByName(String name);

	Carrier findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<Carrier> findAll();
}
