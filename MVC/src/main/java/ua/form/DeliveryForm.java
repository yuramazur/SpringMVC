package ua.form;

import ua.entity.Carrier;
import ua.entity.City;

public class DeliveryForm {
	private int id;
	private City city;
	private Carrier carrier;
	private String numberDepartment;
	private String error;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public String getNumberDepartment() {
		return numberDepartment;
	}

	public void setNumberDepartment(String numberDepartment) {
		this.numberDepartment = numberDepartment;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
