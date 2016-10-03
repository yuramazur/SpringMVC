package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Product;
import ua.entity.User;
import ua.form.filter.ProductFilterForm;


public class ProductFilterAdapter implements Specification<Product> {
	private final ProductFilterForm form;
	private final List<Specification<Product>> filters = new ArrayList<>();

	public ProductFilterAdapter(ProductFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new ProductFilterForm();
		}
	}

	public ProductFilterAdapter(int id, ProductFilterForm form) {
		this.form = form;
		filters.add((root, query, cb) -> {
			Join<Product, User> join = root.join("users");
			Expression<Integer> exp = join.get("id");
			return cb.equal(exp, id);
		});
	}

	private void findByPrice() {

		if (form.getMinPriceDouble() != 0 && form.getMaxPriceDouble() != 0) {
			filters.add((root, query, cb) -> {
				Expression<Double> exp = root.get("price");
				return cb.between(exp, form.getMinPriceDouble(),
						form.getMaxPriceDouble());
			});

		} else if (form.getMinPriceDouble() != 0) {
			filters.add((root, query, cb) -> {
				Expression<Double> exp = root.get("price");
				return cb.ge(exp, form.getMinPriceDouble());
			});

		} else if (form.getMaxPriceDouble() != 0) {
			filters.add((root, query, cb) -> {
				Expression<Double> exp = root.get("price");
				return cb.le(exp, form.getMaxPriceDouble());
			});

		}

	}

	private void findByProductType() {
		if (!form.getProductTypeIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("productType").in(
					form.getProductTypeIds()));
		}
	}

	private void findByProducer() {
		if (!form.getProducerIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("producer").in(
					form.getProducerIds()));
		}
	}

	private void findByName() {
		if (form.getNameSearch() != null) {
			filters.add((root, query, cb) -> {
				Expression<String> exp = root.get("name");
				return cb.like(cb.upper(exp), form.getNameSearch()
						.toUpperCase() + "%");
			});
		}
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
			root.fetch("productType", JoinType.LEFT);
			root.fetch("producer", JoinType.LEFT);
		}
		
		findByProductType();
		findByProducer();
		findByName();
		findByPrice();
		
		if(!filters.isEmpty()){
			Specifications<Product> specification = Specifications.where(filters.get(0));
			for (Specification<Product> s : filters.subList(1, filters.size())) {
				specification = specification.and(s);
			}
			return specification.toPredicate(root, query, cb);
		}
		return null;
	}

}
