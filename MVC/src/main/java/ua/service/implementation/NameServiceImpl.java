package ua.service.implementation;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Name;
import ua.repository.NameRepository;
import ua.service.NameService;

@Service
public class NameServiceImpl implements NameService {

	@Autowired
	private NameRepository nameRepository;

	public void save(String nameNames) {
		if (nameRepository.findByNames(nameNames) == null) {
			Name nameVal = new Name();
			nameVal.setNames(nameNames);
			nameRepository.save(nameVal);
		}

	}

	public Name findByNames(String nameNames) {

		return nameRepository.findByNames(nameNames);
	}

	public Name findById(int nameId) {

		return nameRepository.findById(nameId);
	}

	public void delete(String nameNames) {
		if (nameRepository.findByNames(nameNames) != null) {
			nameRepository.delete(nameRepository.findByNames(nameNames));
		}
	}

	public void deleteById(int nameId) {
		nameRepository.delete(nameId);

	}

	public List<Name> findAll() {

		return nameRepository.findAll();

	}

	public List<Name> findAllByCoincidence(String value) {
		List<Name> rezult = new ArrayList<Name>();
		List<Name> names = nameRepository.findAll();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).getNames().toLowerCase().startsWith(value.toLowerCase())) {
				rezult.add(names.get(i));
			}
		}
		names.clear();
		return rezult;
	}

}
