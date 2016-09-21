package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Carrier;
import ua.form.filter.CarrierFilterForm;

public class CarrierFilterAdapter implements Specification<Carrier> {
	private String search = "";

	public CarrierFilterAdapter(CarrierFilterForm form) {
		if (form.getSearch() != null) {
			this.search = form.getSearch();
		}
	}

	@Override
	public Predicate toPredicate(Root<Carrier> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<String> ex = root.get("name");
		return cb.like(cb.upper(ex), search.toUpperCase() + "%");
	}

}
