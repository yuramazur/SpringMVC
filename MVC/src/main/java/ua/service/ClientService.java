package ua.service;

import java.util.List;

import ua.entity.Client;

public interface ClientService {

	void save(String name, String lastName, String phone);

	List<Client> findBy(String name, String lastName, String phone);

	List<Client> findAll();

}
