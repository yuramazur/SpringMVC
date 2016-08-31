package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Delivery;
import ua.repository.CarrierRepository;
import ua.repository.CityRepository;
import ua.repository.DeliveryRepository;
import ua.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CarrierRepository carrierRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;

	public void save(int cityId, int carrierId, int numCerrDep) {
		Delivery delivery = new Delivery();
		delivery.setCity(cityRepository.findById(cityId));
		delivery.setCarrier(carrierRepository.findById(carrierId));
		delivery.setNumCerrDep(numCerrDep);
		if (deliveryRepository.findDelivery(delivery.getCity().getName(),
				delivery.getCarrier().getName(),
				delivery.getNumCerrDep()) == null){
			deliveryRepository.save(delivery);
		}
	}

	public void save(String cityName, String carrierName, int numCerrDep) {
		Delivery delivery = new Delivery();
		delivery.setCity(cityRepository.findByName(cityName));
		delivery.setCarrier(carrierRepository.findByName(carrierName));
		delivery.setNumCerrDep(numCerrDep);
		if (deliveryRepository.findDelivery(delivery.getCity().getName(),
				delivery.getCarrier().getName(),
				delivery.getNumCerrDep()) == null){
			deliveryRepository.save(delivery);
		}

	}

	public List<Delivery> findAll() {

		return deliveryRepository.findAllDeliveryIntited();
	}

	public void deleteById(int id) {
		deliveryRepository.delete(id);

	}

}
