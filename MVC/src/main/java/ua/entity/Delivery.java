package ua.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(indexes={@Index(columnList="numCerrDep")})
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private City city;
	@ManyToOne(fetch = FetchType.LAZY)
	private Carrier carrier;
	@OneToMany(mappedBy = "delivery")
	private List<MyOrder> order;
	private String numCerrDep;
	@Transient
	private String error;

	public Delivery() {

	}

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

	public List<MyOrder> getOrder() {
		return order;
	}

	public void setOrder(List<MyOrder> order) {
		this.order = order;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public String getNumCerrDep() {
		return numCerrDep;
	}

	public void setNumCerrDep(String numCerrDep) {
		this.numCerrDep = numCerrDep;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
	// @Override
	// public String toString() {
	// return "id: " + id + " city: " + city + ", carrier: " + carrier + ", � "
	// + numCerrDep;
	// }

}
