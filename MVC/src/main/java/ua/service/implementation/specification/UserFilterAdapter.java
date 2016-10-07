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

import ua.entity.Client;
import ua.entity.Name;
import ua.entity.User;
import ua.form.filter.UserFilterForm;

public class UserFilterAdapter implements Specification<User> {
	private final UserFilterForm form;
	private final List<Specification<User>> filters = new ArrayList<>();

	public UserFilterAdapter(UserFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new UserFilterForm();
		}
	}

	private void findByLogin() {
		if (form.getLoginSearch() != null) {
			filters.add((root, query, cb) -> {
				Expression<String> exp = root.get("login");
				return cb.like(cb.upper(exp), form.getLoginSearch()
						.toUpperCase() + "%");
			});

		}
	}

	private void findByMail() {
		if (form.getMailSearch() != null) {
			filters.add((root, query, cb) -> {
				Expression<String> exp = root.get("mail");
				return cb.like(cb.upper(exp), "%"
						+ form.getMailSearch().toUpperCase() + "%");
			});

		}
	}

	private void findByName() {
		if (form.getNameSearch() != null) {
			filters.add((root, query, cb) -> {
				Join<User, Client> clientJoin = root.join("client");
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
				Join<User, Client> clientJoin = root.join("client");
				Expression<String> exp = clientJoin.get("lastName");
				return cb.like(cb.upper(exp), form.getLastNameSearch()
						.toUpperCase() + "%");
			});

		}
	}

	private void findByPhone() {
		if (form.getPhoneSearch() != null) {
			filters.add((root, query, cb) -> {
				Join<User, Client> clientJoin = root.join("client");
				Expression<String> exp = clientJoin.get("phone");
				return cb.like(cb.upper(exp), form.getPhoneSearch()
						.toUpperCase() + "%");
			});

		}
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
			root.fetch("client", JoinType.LEFT).fetch("name", JoinType.LEFT);
			root.fetch("client", JoinType.LEFT);

		}
		findByLogin();
		findByMail();
		findByName();
		findByLastName();
		findByPhone();
		if (!filters.isEmpty()) {
			Specifications<User> specification = Specifications.where(filters
					.get(0));
			for (Specification<User> s : filters.subList(1, filters.size())) {
				specification = specification.and(s);
			}
			return specification.toPredicate(root, query, cb);
		}
		return null;
	}

}
