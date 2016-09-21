package ua.service;

import ua.entity.User;

public interface UserService {
	
	User findByLogin(String login);

	User findById(int id);

	void save(User user);
}
