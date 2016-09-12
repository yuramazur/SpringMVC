package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.*;

public interface CityService {
	void save(String name);

	void save(City city);

	City findByName(String name);

	City findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<City> findAll();

	Page<City> findAllPageable(Pageable pageable);
}
