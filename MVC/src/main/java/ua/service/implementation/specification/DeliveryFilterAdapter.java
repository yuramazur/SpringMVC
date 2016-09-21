package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Delivery;
import ua.form.filter.DeliveryFilterForm;

public class DeliveryFilterAdapter implements Specification<Delivery> {
	private final DeliveryFilterForm form;
	private final List<Specification<Delivery>> filters = new ArrayList<>();

	public DeliveryFilterAdapter(DeliveryFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new DeliveryFilterForm();
		}
	}

	private void findByCity() {
		if (!form.getCityIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("city").in(
					form.getCityIds()));
		}
	}

	private void findByCarrier() {
		if (!form.getCarrierIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("carrier").in(
					form.getCarrierIds()));
		}
	}

	private void findByNumCerrDep() {
		if (form.getNumCerrDepInt() != 0) {
			filters.add((root, query, cb) -> {
				Expression<Integer> exp = root.get("numCerrDep");
				return cb.equal(exp, form.getNumCerrDepInt());
			});
		}
	}

	@Override
	public Predicate toPredicate(Root<Delivery> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
			root.fetch("city", JoinType.LEFT);
			root.fetch("carrier", JoinType.LEFT);
		}
		findByCity();
		findByCarrier();
		findByNumCerrDep();
		if (!filters.isEmpty()) {
			Specifications<Delivery> spec = Specifications
					.where(filters.get(0));
			for (Specification<Delivery> specification : filters.subList(1,
					filters.size())) {
				spec = spec.and(specification);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
