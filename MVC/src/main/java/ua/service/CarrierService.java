package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.*;
import ua.form.filter.CarrierFilterForm;

public interface CarrierService {
	void save(String name);

	void save(Carrier carrier);

	Carrier findByName(String name);

	Carrier findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<Carrier> findAll();

	Page<Carrier> findAllPageable(Pageable pageable);

	Page<Carrier> findAllPageableForm(Pageable pageable, CarrierFilterForm form);
}
