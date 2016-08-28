package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	
	@Query("SELECT d FROM Delivery d LEFT JOIN FETCH d.city LEFT JOIN FETCH d.carrier")
	List<Delivery> findAllDeliveryIntited();

}
