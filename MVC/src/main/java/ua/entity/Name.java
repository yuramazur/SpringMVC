package ua.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(indexes=@Index(columnList="names"))
public class Name {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String names;
	@OneToMany(mappedBy = "name")
	private List<Client> client;

	public Name() {

	}

	public Name(String names) {
		this.names = names;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "id: " + id + " - " + names;
	}

}
