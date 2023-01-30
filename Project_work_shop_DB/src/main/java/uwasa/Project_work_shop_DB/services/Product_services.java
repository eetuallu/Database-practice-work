package uwasa.Project_work_shop_DB.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uwasa.Project_work_shop_DB.JPAUtil;
import uwasa.Project_work_shop_DB.entities.Customer;
import uwasa.Project_work_shop_DB.entities.Orders;
import uwasa.Project_work_shop_DB.entities.Product;
import uwasa.Project_work_shop_DB.entities.Product_review;
import uwasa.Project_work_shop_DB.entities.Warehouse;

public class Product_services {
	
	private Order_services order_services = new Order_services();
	private Product_review_services prs = new Product_review_services();
	
	private EntityManager em;

	public Product_services() {
		em = JPAUtil.getEntityManager();
	}

	
	
	// add new product to the database
	public void AddNewProduct(int warehouse_id, String d, String c, double price) {
		Warehouse warehouse = em.find(Warehouse.class, warehouse_id);
		em.getTransaction().begin();
		Product p = new Product();
		p.setDescription(d);
		p.setPrice(price);
		p.setWarehouse(warehouse);
		p.setColour(c);
		em.persist(p);
		em.getTransaction().commit();
		System.out.println("\n\nNew product "+p.getDescription()+ " has been added to the warehouse "+ p.getWarehouse().getWarehouse_id());

	}
	
	public void PrintAllProducts() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> Query = criteriaBuilder.createQuery(Product.class);
		Root<Product> fromProduct = Query.from(Product.class);
		Query.select(fromProduct); // select all from Customer
		
		List<Product> list = em.createQuery(Query).getResultList();
		System.out.println("\n\nid\tdescription\tcolor\tprice\n------------------------------------------------------");
		for(Product p : list) {
			System.out.println(p.getProduct_id()+"\t"+p.getDescription()+"\t\t"+p.getColour()+"\t"+p.getPrice());
		}
		System.out.println("------------------------------------------------------");
	}

	
	public void DeleteProduct(int id) {
		//deletes product by product_id, and delete all related orders
		if(em.find(Product.class, id) == null) {
			System.out.println("Product with product id "+id+" does not exist");
		}
		else {
			List<Orders> orders = order_services.FindOrderByProductId(id);
			System.out.println("related orders:\n--------------------------------------------------------------------");
			for(Orders order : orders) {
				System.out.println(order);
			}
			List<Product_review> reviews = prs.FindReviewByProductId(id);
			System.out.println("\nrelated reviews:\n--------------------------------------------------------------------");
			for(Product_review r : reviews) {
				System.out.println(r);
			}
			if(orders.size() == 0) {
				System.out.println("Related orders were not found.");
				if(reviews.size() == 0) {
					System.out.println("Related reviews were not found.");
					Product product = findProductById(id);
					removeProduct(product);
					System.out.println("Product " + product + " has been deleted");
				}else {
					for (Product_review r : reviews) {
						prs.removeReview(r);
					}
					
					System.out.println("related reviews have been deleted.");
					Product pro = findProductById(id);
					removeProduct(pro);
					System.out.println("\nProduct " + pro + " has been deleted");
				}	
			}else {
				for (Orders order : orders) {
					order_services.removeOrder(order);
				}
				System.out.println("related orders have been deleted.");
				
				if(reviews.size() == 0) {
					System.out.println("Related reviews were not found.");
					Product product = findProductById(id);
					removeProduct(product);
					System.out.println("Product " + product + " has been deleted");
				}else {
					for (Product_review r : reviews) {
						prs.removeReview(r);
					}
					System.out.println("related reviews have been deleted.");
					Product pro = findProductById(id);
					removeProduct(pro);
					System.out.println("Product " + pro + " has been deleted");
				}	

			}	
			
			
			
		}
	}
	
	//update warehouse info for given product
	public void updateWarehouseForProduct(int p_id, int w_id) {
		Product p = em.find(Product.class, p_id);
		em.getTransaction().begin();
		Warehouse w = em.find(Warehouse.class, w_id);
		p.setWarehouse(w);
		em.getTransaction().commit();
		System.out.println("New warehouse for product "+p.getDescription()+" is warehouse "+w.getWarehouse_id()+" in "+w.getCity());
	}
	
	//find product by product id
	public Product findProductById(int id)
	{
		Product product = em.find(Product.class,id);
		return product;
	}
	
	public void removeProduct(Product p) 
	{
		//remove customer from the database
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}

	public List<Product> FindProductByWarehouseId(int w_id) {
		//find products that are connected to given warehouse
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> Query = criteriaBuilder.createQuery(Product.class);
		Root<Product> fromWarehouse = Query.from(Product.class);
		Query.select(fromWarehouse); // select all from Product

		Query.where(criteriaBuilder.equal(fromWarehouse.get("warehouse"), w_id));
	
		List<Product> list = em.createQuery(Query).getResultList();
				
		return list;
	}


	
	

}
