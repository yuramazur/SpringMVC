package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Carrier;
import ua.repository.CarrierRepository;
import ua.service.CarrierService;

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

}
