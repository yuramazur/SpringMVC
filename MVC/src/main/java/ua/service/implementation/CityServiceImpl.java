package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.City;
import ua.repository.CityRepository;
import ua.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityRepository cityReposetory;

	public void save(String name) {
		if(cityReposetory.findByName(name)==null){
			City city = new City();
			city.setName(name);
			cityReposetory.save(city);
		}

	}

	public City findByName(String name) {
		
		return cityReposetory.findByName(name);
	}

	public City findById(int id) {
		
		return cityReposetory.findById(id);
	}

	public void deleteByName(String name) {
		cityReposetory.deleteByName(name);

	}

	public void deleteById(int id) {
		cityReposetory.delete(id);

	}

	public List<City> findAll() {
		
		return cityReposetory.findAll();
	}

}