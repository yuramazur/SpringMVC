package ua.service.implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.Role;
import ua.entity.User;
import ua.repository.UserRepository;
import ua.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User findByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public void save(User user) {
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
	}
	
	@PostConstruct
	public void saveAdmin(){
		User user = new User();
		user.setRole(Role.ROLE_ADMIN);
		user.setPassword(encoder.encode("admin"));
		user.setLogin("admin");
		user.setId(1);
		repository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}

	@Override
	public User findById(int id) {
		return repository.findOne(id);
	}
}