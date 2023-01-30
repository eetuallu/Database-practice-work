package uwasa.Project_work_shop_DB.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Warehouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int warehouse_id;
	private String city;
	private String address;
	
	@OneToMany(mappedBy="warehouse")
	private Set<Product> products;
	
	//constructors
	public Warehouse(String city, String address) {
		super();
		this.city = city;
		this.address = address;
	}
	public Warehouse() {
		super();
	}
	
	//Getters and setters
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Warehouse [warehouse_id=" + warehouse_id + ", city=" + city + ", address=" + address + "]";
	}
	
	
	
}
