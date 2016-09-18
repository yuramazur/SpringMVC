package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.City;
import ua.form.CityFilterForm;

public class CityFilterAdapter implements Specification<City>{

	private String search = "";
	
	
	public CityFilterAdapter(CityFilterForm form) {
		if(form.getSearch() != null){
			this.search = form.getSearch();
		}
	}


	@Override
	public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<String> ex = root.get("name");
 		return cb.like(cb.upper(ex), search.toUpperCase()+"%");
	}

}
