package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Product;
import ua.entity.User;
import ua.form.UserForm;
import ua.form.filter.ProductFilterForm;

public interface UserService {

	User findByLogin(String login);

	User findById(int id);

	void save(User user);

	void save(UserForm userForm);

	void addToWishList(int uId, int id);

	Page<Product> findWishList(int id, Pageable pageable,
			ProductFilterForm filter);

	void deleteFromWishList(Principal principal,int id);

	List<Product> getWishList(int id);

}
