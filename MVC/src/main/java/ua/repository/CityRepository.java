package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.City;

public interface CityRepository extends JpaRepository<City, Integer>{

	void deleteById(int id);

	void deleteByName(String name);

	City findById(int id);

	City findByName(String name);

}
