package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.ProductType;
import ua.form.ProductTypeFilterForm;

public class ProductTypeFilterAdapter implements Specification<ProductType> {

	private String search = "";

	public ProductTypeFilterAdapter(ProductTypeFilterForm form) {
		if (form.getSearch() != null) {
			this.search = form.getSearch();
		}
	}

	@Override
	public Predicate toPredicate(Root<ProductType> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		Expression<String> exp = root.get("name");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
