package uwasa.Project_work_shop_DB.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	private String description;
	private double price;
	private String colour;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id", nullable=false)
	private Warehouse warehouse;
	
	@OneToMany(mappedBy="product")
	private Set<Product_review> reviews;
	
	@OneToMany(mappedBy="product")
	private List<Orders> order;
	
	
	//constructors
	public Product(String description, double price, String colour, Warehouse warehouse_id) {
		super();
		this.description = description;
		this.price = price;
		this.colour = colour;
		this.warehouse = warehouse_id;
	}

	public Product() {
		super();
	}

	//Getters and setters
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	
	
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Set<Product_review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Product_review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", description=" + description + ", price=" + price + ", colour="
				+ colour + ", warehouse_id=" + warehouse.getWarehouse_id()  + "]";
	}
	
	
}
