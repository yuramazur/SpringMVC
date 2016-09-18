package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ua.entity.City;
import ua.form.CityFilterForm;
import ua.form.NameFilterForm;
import ua.repository.CityRepository;
import ua.service.CityService;
import ua.service.implementation.specification.CityFilterAdapter;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityRepository cityReposetory;

	public void save(String name) {
		if (cityReposetory.findByName(name) == null) {
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

	@Override
	public void save(City city) {
		if (cityReposetory.findByName(city.getName()) == null) {
			cityReposetory.save(city);
		}

	}

	@Override
	public Page<City> findAllPageable(Pageable pageable) {

		return cityReposetory.findAll(pageable);
	}

	@Override
	public Page<City> findAllPageableForm(Pageable pageable, CityFilterForm form) {

		return cityReposetory.findAll(new CityFilterAdapter(form), pageable);
	}
	@SuppressWarnings("unused")
	private String getParams(Pageable pageable, NameFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
