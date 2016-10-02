package ua.service;

import ua.entity.User;
import ua.form.UserForm;

public interface UserService {

	User findByLogin(String login);

	User findById(int id);

	void save(User user);

	void save(UserForm userForm);

	void addToWishList(int uId, int id);
}
