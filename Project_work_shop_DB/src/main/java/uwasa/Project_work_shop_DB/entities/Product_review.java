package uwasa.Project_work_shop_DB.entities;



import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product_review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private int rating;
	private String content;
	private Timestamp published;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable=false)
	private Product product;
	
	//constructors
	public Product_review() {
		super();
		this.setPublished(new Timestamp(System.currentTimeMillis()));
	}

	public Product_review(String title, int rating, String content, Product product) {
		super();
		this.title = title;
		this.rating = rating;
		this.content = content;
		this.setPublished(new Timestamp(System.currentTimeMillis()));
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getPublished() {
		return published;
	}

	public void setPublished(Timestamp published) {
		this.published = published;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Product_review [id=" + id + ", title=" + title + ", rating=" + rating + ", content=" + content
				+ ", published=" + published + ", product=" + product + "]";
	}
	
}
