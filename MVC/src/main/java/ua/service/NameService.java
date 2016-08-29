package ua.service;

import java.util.List;

import ua.entity.Name;

public interface NameService {
	
	void save(String nameNames);
	
	void save(Name name);
	
	Name findByNames(String nameNames);

	Name findById(int nameId);

	void delete(String nameNames);

	void deleteById(int nameId);

	List<Name> findAll();
	
	List<Name> findAllByCoincidence(String value);
}
