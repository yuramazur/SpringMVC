package ua.service.implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.Client;
import ua.entity.Name;
import ua.entity.Role;
import ua.entity.User;
import ua.form.UserForm;
import ua.repository.ClientRepository;
import ua.repository.NameRepository;
import ua.repository.UserRepository;
import ua.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private NameRepository nameRepository;

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
	public void saveAdmin() {
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

	@Override
	public void save(UserForm userForm) {
		User user = new User();
		Client client = new Client();
		user.setRole(Role.ROLE_USER);
		user.setLogin(userForm.getLogin());
		user.setMail(userForm.getMail());
		user.setPassword(encoder.encode(userForm.getPasswordConfirm()));
		if (nameRepository.findByNames(userForm.getName()) == null) {
			Name name = new Name();
			name.setNames(userForm.getName());
			nameRepository.save(name);
		}
		client.setName(nameRepository.findByNames(userForm.getName()));
		client.setLastName(userForm.getLastName());
		client.setPhone(userForm.getPhone());
		user.setClient(client);
		clientRepository.save(client);
		repository.save(user);

	}

	@Override
	public void addToWishList(int UserId, int productId) {
		repository.findOne(UserId).getWishList().add(productId);
		
	}

	
}