package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.spi.Producer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Client;
import ua.entity.Delivery;
import ua.entity.MyOrder;
import ua.entity.Name;
import ua.entity.Product;
import ua.entity.ProductType;
import ua.entity.User;
import ua.form.filter.OrderFilterForm;

public class OrderFilterAdapter implements Specification<MyOrder> {

	private final OrderFilterForm form;
	private final List<Specification<MyOrder>> filters = new ArrayList<>();

	public OrderFilterAdapter(OrderFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new OrderFilterForm();
		}
	}

	private void findByName() {
		if (form.getNameSearch() != null) {
			filters.add((root, query, cb) -> {
				Join<MyOrder, Client> clientJoin = root.join("client");
				Join<Client, Name> nameJoin = clientJoin.join("name");
				Expression<String> exp = nameJoin.get("names");
				return cb.like(cb.upper(exp), form.getNameSearch()
						.toUpperCase() + "%");
			});

		}
	}

	private void findByLastName() {
		if (form.getLastNameSearch() != null) {

			filters.add((root, query, cb) -> {
				Join<MyOrder, Client> clientJoin = root.join("client");
				Expression<String> exp = clientJoin.get("lastName");
				return cb.like(cb.upper(exp), form.getLastNameSearch()
						.toUpperCase() + "%");
			});

		}
	}

	private void findByPhone() {
		if (form.getPhoneSearch() != null) {
			filters.add((root, query, cb) -> {
				Join<MyOrder, Client> clientJoin = root.join("client");
				Expression<String> exp = clientJoin.get("phone");
				return cb.like(cb.upper(exp), form.getPhoneSearch()
						.toUpperCase() + "%");
			});

		}
	}

	private void findByCity() {
		if (form.getCitySearch() != null) {
			filters.add((root, query, cb) -> {
				Join<MyOrder, Delivery> clientJoin = root.join("city");
				Expression<String> exp = clientJoin.get("name");
				return cb.like(cb.upper(exp), form.getCitySearch()
						.toUpperCase() + "%");
			});

		}
	}
	private void findByCarrier() {
		if (form.getCarrierSearch() != null) {
			filters.add((root, query, cb) -> {
				Join<MyOrder, Delivery> clientJoin = root.join("carrier");
				Expression<String> exp = clientJoin.get("name");
				return cb.like(cb.upper(exp), form.getCarrierSearch()
						.toUpperCase() + "%");
			});

		}
	}
	private void findByProducer() {
		if (form.getProducerSearch() != null) {
			
			filters.add((root, query, cb) -> {
				Join<MyOrder, Product> productJoin = root.join("products");
				Join<Product, Producer> producerJoin = productJoin.join("producer");
				Expression<String> exp = producerJoin.get("name");
				return cb.like(cb.upper(exp), form.getProducerSearch()
						.toUpperCase() + "%");
			});

		}
	}
	private void findByProductType() {
		if (form.getProductTypeSearch() != null) {
			
			filters.add((root, query, cb) -> {
				Join<MyOrder, Product> productJoin = root.join("products");
				Join<Product, ProductType> productTypeJoin = productJoin.join("productType");
				Expression<String> exp = productTypeJoin.get("name");
				return cb.like(cb.upper(exp), form.getProductTypeSearch()
						.toUpperCase() + "%");
			});

		}
	}
	
	@Override
	public Predicate toPredicate(Root<MyOrder> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
			root.fetch("client", JoinType.LEFT).fetch("name", JoinType.LEFT);
			root.fetch("client", JoinType.LEFT);
			root.fetch("delivery", JoinType.LEFT).fetch("city", JoinType.LEFT);
			root.fetch("delivery", JoinType.LEFT).fetch("carrier",
					JoinType.LEFT);
			root.fetch("delivery", JoinType.LEFT);
			
//			root.fetch("products", JoinType.LEFT).fetch("productType",
//					JoinType.LEFT);
//			root.fetch("products", JoinType.LEFT).fetch("producer",
//					JoinType.LEFT);
//			root.fetch("products", JoinType.LEFT);
			
		}
		findByName();
		findByLastName();
		findByPhone();
		findByCity();
		findByCarrier();
		findByProducer();
		findByProductType();
		if (!filters.isEmpty()) {
			Specifications<MyOrder> specification = Specifications
					.where(filters.get(0));
			for (Specification<MyOrder> s : filters.subList(1, filters.size())) {
				specification = specification.and(s);
			}
			return specification.toPredicate(root, query, cb);
		}
		return null;
	}

}