package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Producer;
import ua.repository.ProducerRepository;
import ua.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {
	@Autowired
	ProducerRepository producerReposetory;

	public void save(String name) {
		if (producerReposetory.findByName(name) == null) {
			Producer producer = new Producer();
			producer.setName(name);
			producerReposetory.save(producer);
		}

	}

	public Producer findByName(String name) {

		return producerReposetory.findByName(name);
	}

	public Producer findById(int id) {

		return producerReposetory.findById(id);
	}

	public void deleteByName(String name) {
		if(producerReposetory.findByName(name)!=null){
		producerReposetory.delete(producerReposetory.findByName(name));
		}
	}

	public void deleteById(int id) {
		producerReposetory.delete(id);

	}

	public List<Producer> findAll() {

		return producerReposetory.findAll();
	}

}
