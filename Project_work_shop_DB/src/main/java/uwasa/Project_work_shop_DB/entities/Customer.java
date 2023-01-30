package uwasa.Project_work_shop_DB.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	private String first_name;
	private String last_name;
	private String city;
	private String address;
	private String phonenumber;
	private String email;
	private String password;
	private String username;
	
	@OneToMany(mappedBy="customer")		//the owner table
	private List<Orders> order; 		//list of orders

	//constructors
	public Customer() {
		super();
	}

	public Customer(String first_name, String last_name, String city, String address, String phonenumber, String email,
			String password, String username) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.city = city;
		this.address = address;
		this.phonenumber = phonenumber;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	//Getters and setters
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", city=" + city + ", address=" + address + ", phonenumber=" + phonenumber + ", email=" + email
				+ ", password=" + password + ", username=" + username + "]";
	}

}
