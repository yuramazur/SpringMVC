package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Producer;
import ua.form.ProducerFilterForm;

public class ProducerFilterAdapter implements Specification<Producer> {
	private String search = "";

	public ProducerFilterAdapter(ProducerFilterForm form) {
		if (form.getSearch() != null) {
			this.search = form.getSearch();
		}
	}

	@Override
	public Predicate toPredicate(Root<Producer> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<String> exp = root.get("name");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
