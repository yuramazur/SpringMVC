package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Name;
import ua.form.NameFilterForm;
import ua.repository.NameRepository;
import ua.service.NameService;
import ua.service.implementation.specification.NameFilterAdapter;

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
		value = value + '%';
		return nameRepository.findAllByCoincidence(value);
	}

	public void save(Name name) {
		if (nameRepository.findByNames(name.getNames()) == null) {
			nameRepository.save(name);
		}
	}

	@Override
	public Page<Name> findAllPageble(Pageable pageable) {
		
		return nameRepository.findAll(pageable);
	}

	@Override
	public Page<Name> findAllPagebleForm(Pageable pageable, NameFilterForm form) {
		
		return nameRepository.findAll(new NameFilterAdapter(form), pageable);
	}

}
