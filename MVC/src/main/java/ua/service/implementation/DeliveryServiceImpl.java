package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Delivery;
import ua.form.DeliveryForm;

import ua.form.filter.DeliveryFilterForm;
import ua.repository.CarrierRepository;
import ua.repository.CityRepository;
import ua.repository.DeliveryRepository;
import ua.service.DeliveryService;
import ua.service.implementation.specification.DeliveryFilterAdapter;

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
				delivery.getCarrier().getName(), delivery.getNumCerrDep()) == null) {
			deliveryRepository.save(delivery);
		}
	}

	public void save(String cityName, String carrierName, int numCerrDep) {
		Delivery delivery = new Delivery();
		delivery.setCity(cityRepository.findByName(cityName));
		delivery.setCarrier(carrierRepository.findByName(carrierName));
		delivery.setNumCerrDep(numCerrDep);
		if (deliveryRepository.findDelivery(delivery.getCity().getName(),
				delivery.getCarrier().getName(), delivery.getNumCerrDep()) == null) {
			deliveryRepository.save(delivery);
		}

	}

	public List<Delivery> findAll() {

		return deliveryRepository.findAllDeliveryIntited();
	}

	public void deleteById(int id) {
		deliveryRepository.delete(id);

	}

	@Override
	public void save(Delivery delivery) {
		deliveryRepository.save(delivery);

	}

	@Override
	public Delivery findById(int id) {

		return deliveryRepository.findOneIntited(id);
	}

	@Override
	public Delivery findDelivery(Delivery delivery) {

		return deliveryRepository.findDelivery(delivery.getCity().getName(),
				delivery.getCarrier().getName(), delivery.getNumCerrDep());
	}

	@Override
	public Page<Delivery> findAllPagebleFilter(Pageable pageable,
			DeliveryFilterForm filter) {

		return deliveryRepository.findAll(new DeliveryFilterAdapter(filter),
				pageable);
	}

	@Override
	public void save(DeliveryForm deliveryForm) {
		Delivery delivery = new Delivery();
		delivery.setId(deliveryForm.getId());
		delivery.setCity(deliveryForm.getCity());
		delivery.setCarrier(deliveryForm.getCarrier());
		delivery.setNumCerrDep(Integer.valueOf(deliveryForm
				.getNumberDepartment()));
		deliveryRepository.save(delivery);
	}

	@Override
	public DeliveryForm findFormById(int id) {
		Delivery delivery = deliveryRepository.findOneIntited(id);
		DeliveryForm deliveryForm = new DeliveryForm();
		deliveryForm.setId(delivery.getId());
		deliveryForm.setCity(delivery.getCity());
		deliveryForm.setCarrier(delivery.getCarrier());
		deliveryForm.setNumberDepartment(String.valueOf(delivery
				.getNumCerrDep()));
		return deliveryForm;
	}

	@Override
	public Delivery findDelivery(DeliveryForm deliveryForm) {
		return deliveryRepository.findDelivery(
				deliveryForm.getCity().getName(), deliveryForm.getCarrier()
						.getName(), Integer.valueOf(deliveryForm
						.getNumberDepartment()));
	}

}
