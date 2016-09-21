package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Carrier;
import ua.form.filter.CarrierFilterForm;
import ua.repository.CarrierRepository;
import ua.service.CarrierService;
import ua.service.implementation.specification.CarrierFilterAdapter;

@Service
public class CarrierServiceImpl implements CarrierService {
	@Autowired
	private CarrierRepository carrierRepository;

	public void save(String name) {
		if (carrierRepository.findByName(name) == null) {
			Carrier carrier = new Carrier();
			carrier.setName(name);
			carrierRepository.save(carrier);
		}

	}

	public Carrier findByName(String name) {

		return carrierRepository.findByName(name);
	}

	public Carrier findById(int id) {
		return carrierRepository.findById(id);
	}

	public void deleteByName(String name) {
		if (carrierRepository.findByName(name) != null) {
			carrierRepository.delete(carrierRepository.findByName(name));
		}

	}

	public void deleteById(int id) {
		carrierRepository.delete(id);

	}

	public List<Carrier> findAll() {

		return carrierRepository.findAll();
	}

	@Override
	public void save(Carrier carrier) {
		if (carrierRepository.findByName(carrier.getName()) == null) {
			carrierRepository.save(carrier);
		}
	}

	@Override
	public Page<Carrier> findAllPageable(Pageable pageable) {
		return carrierRepository.findAll(pageable);
	}

	@Override
	public Page<Carrier> findAllPageableForm(Pageable pageable,
			CarrierFilterForm form) {
		
		return carrierRepository.findAll(new CarrierFilterAdapter(form), pageable);
	}

}
