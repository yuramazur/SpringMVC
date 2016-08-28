package ua.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Client;
import ua.entity.Name;
import ua.repository.ClientRepository;
import ua.repository.NameRepository;
import ua.service.ClientService;


@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private NameRepository nameRepository;
	

	public void save(String name, String lastName, String phone) {
		Client client = new Client();
		if (nameRepository.findByNames(name) == null) {
			Name nameVal = new Name();
			nameVal.setNames(name);
			nameRepository.save(nameVal);
		}
		Name nameVal = nameRepository.findByNames(name);
		client.setName(nameVal);
		client.setLastName(lastName);
		client.setPhone(phone);
		clientRepository.save(client);
	}

	@Transactional
	public List<Client> findBy(String name, String lastName, String phone) {
		List<Client> rezList = new ArrayList<Client>();
		Iterator<Client> iter = clientRepository.findAll().iterator();
		while (iter.hasNext()) {
			Client client = iter.next();
			
			if (isTrueClient(name, lastName, phone, client)) {
				rezList.add(client);
			}
		}
		return rezList;
	}

	private boolean isTrueClient(String name, String lastName, String phone, Client client) {
		boolean swch = true;
		if (!name.equals("")) {
			if (!client.getName().getNames().equals(name)) {
				swch = false;
				return swch;
			}
		}
		if (!lastName.equals("")) {
			if (!client.getLastName().equals(lastName)) {
				swch = false;
				return swch;
			}
		}
		if (!phone.equals("")) {
			if (!client.getPhone().equals(phone)) {
				swch = false;
				return swch;
			}
		}

		return swch;
	}

	public List<Client> findAll() {

		return clientRepository.findAll();
	}

}
