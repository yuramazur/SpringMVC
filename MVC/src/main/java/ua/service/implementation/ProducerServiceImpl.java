package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Producer;
import ua.form.filter.ProducerFilterForm;
import ua.repository.ProducerRepository;
import ua.service.ProducerService;
import ua.service.implementation.specification.ProducerFilterAdapter;

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
		if (producerReposetory.findByName(name) != null) {
			producerReposetory.delete(producerReposetory.findByName(name));
		}
	}

	public void deleteById(int id) {
		producerReposetory.delete(id);

	}

	public List<Producer> findAll() {

		return producerReposetory.findAll();
	}

	@Override
	public void save(Producer producer) {
		if (producerReposetory.findByName(producer.getName()) == null) {
			producerReposetory.save(producer);
		}

	}

	@Override
	public Page<Producer> findAllPageable(Pageable pageable) {
		return producerReposetory.findAll(pageable);
	}

	@Override
	public Page<Producer> findAllPageableFilter(Pageable pageable,
			ProducerFilterForm form) {

		return producerReposetory.findAll(new ProducerFilterAdapter(form),
				pageable);
	}

}
