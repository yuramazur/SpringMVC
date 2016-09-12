package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Name;
import ua.form.NameFilterForm;

public class NameFilterAdapter implements Specification<Name>{

	private String search = "";
	
	
	
	public NameFilterAdapter(NameFilterForm form) {
		if(form.getSearch()!=null)
			search = form.getSearch();
	}



	@Override
	public Predicate toPredicate(Root<Name> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<String> ex = root.get("names");
		return cb.like(cb.upper(ex), search.toUpperCase()+"%");
	}

}
