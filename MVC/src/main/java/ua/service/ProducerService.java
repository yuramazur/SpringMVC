package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.*;

public interface ProducerService {
	void save(String name);

	void save(Producer producer);

	Producer findByName(String name);

	Producer findById(int id);

	void deleteByName(String name);

	void deleteById(int id);

	List<Producer> findAll();

	Page<Producer> findAllPageable(Pageable pageable);
}
