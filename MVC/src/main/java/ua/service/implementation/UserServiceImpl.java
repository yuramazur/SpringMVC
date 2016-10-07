package ua.service.implementation;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Client;
import ua.entity.Name;
import ua.entity.Product;
import ua.entity.Role;
import ua.entity.User;
import ua.form.UserForm;
import ua.form.filter.ProductFilterForm;
import ua.form.filter.UserFilterForm;
import ua.repository.ClientRepository;
import ua.repository.NameRepository;
import ua.repository.ProductRepository;
import ua.repository.UserRepository;
import ua.service.UserService;
import ua.service.implementation.specification.ProductFilterAdapter;
import ua.service.implementation.specification.UserFilterAdapter;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private NameRepository nameRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
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
		return repository.findById(id);
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
	@Transactional
	public void addToWishList(int userId, int productId) {
		User user = repository.findOne(userId);
		user.getWishList().add(productRepository.findOne(productId));
		repository.save(user);

	}

	@Override
	public Page<Product> findWishList(int id, Pageable pageable,
			ProductFilterForm filter) {
		return productRepository.findAll(new ProductFilterAdapter(id, filter),
				pageable);
	}

	@Override
	public void deleteFromWishList(Principal principal, int id) {
		User user = findById(Integer.valueOf(principal.getName()));
		Iterator<Product> iterator = user.getWishList().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
				break;
			}
		}
		save(user);
	}

	@Override
	public List<Product> getWishList(int id) {
		
		return productRepository.findWishList(id);
	}

	@Override
	public Page<User> findAllPageable(Pageable pageable,
			UserFilterForm filter) {
		
		return userRepository.findAll(new UserFilterAdapter(filter), pageable);
	}
}