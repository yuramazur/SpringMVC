package ua.service;

import java.util.List;

import ua.entity.*;

public interface CityService {
	void save(String name);

	City findByName(String name);

	City findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<City> findAll();
}
