package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import ua.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

	@Query("SELECT d FROM Delivery d LEFT JOIN FETCH d.city LEFT JOIN FETCH d.carrier")
	List<Delivery> findAllDeliveryIntited();

	@Query("SELECT d FROM Delivery d JOIN d.city dcy JOIN d.carrier dcr WHERE dcy.name=:cityName AND dcr.name=:carrierName AND d.numCerrDep=:numCerrDep")
	Delivery findDelivery(@Param("cityName") String cityName,
			@Param("carrierName") String carrierName,
			@Param("numCerrDep") String string);

	@Query("SELECT d FROM Delivery d LEFT JOIN FETCH d.city LEFT JOIN FETCH d.carrier WHERE d.id =:id")
	Delivery findOneIntited(@Param("id") int id);

}
