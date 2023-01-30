package uwasa.Project_work_shop_DB.entities;



import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	private Timestamp date;
	
	@ManyToOne
	@JoinColumn(name= "product_id", nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable=false)
	private Customer customer;
	
	
	//constructor
	public Orders() {
		super();
		this.setDate(new Timestamp(System.currentTimeMillis()));
	}
	

	//Getters and setters
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Product getProduct_id() {
		return product;
	}

	public void setProduct_id(Product product_id) {
		this.product = product_id;
	}

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", date=" + date + ", product_id=" + product + ", customer="
				+ customer + "]";
	}
	
	
}
