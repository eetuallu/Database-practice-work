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

/**services regarding the orders table*/

public class Order_services {
	
	private EntityManager em;

	public Order_services() {
		em = JPAUtil.getEntityManager();
	}

	
	public void CreateNewOrder(int customer_id, int product_id) {
		//create a new order
		Customer customer = em.find(Customer.class, customer_id);
		Product product = em.find(Product.class, product_id);
		em.getTransaction().begin();
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setProduct_id(product);
		em.persist(order);
		em.getTransaction().commit();
		System.out.println("New order created for customer: "+ customer.getFirst_name()+" "+customer.getLast_name());
	}
	
	public void PrintAllOrders() {
		//print out all orders
	}
	
	public Orders FindOrderById(int orderID) {
		Orders order = em.find(Orders.class, orderID);
		return order;
	}
	
	public void PrintOrders(List<Orders> list) {
		//Print the given list of orders
		if(list.size()==0) {
			System.out.println("\nGiven customer does not have orders... ");
		}else {
			System.out.println("\norder\tproduct\tcolor\ttime \n-----------------------------------------------------------");
			for(Orders o : list) {
				System.out.println(o.getOrder_id() +"\t"+o.getProduct_id().getDescription()+"\t"+o.getProduct_id().getColour()+"\t"+o.getDate());
			}
			System.out.println("-----------------------------------------------------------");
		}
	}
	
	public List<Orders> FindOrderByCustomerId(int customer_id) {
		//find orders that are connected to given customer
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Orders> Query = criteriaBuilder.createQuery(Orders.class);
		Root<Orders> fromCustomer = Query.from(Orders.class);
		Query.select(fromCustomer); // select all from Customer

		Query.where(criteriaBuilder.equal(fromCustomer.get("customer"), customer_id));
	
		List<Orders> list = em.createQuery(Query).getResultList();
		
		System.out.println("\n\norders by user " + em.find(Customer.class, customer_id).getFirst_name());
		PrintOrders(list);
		
		return list;
	}
	
	public List<Orders> FindOrderByProductId(int product_id) {
		//find orders that are related to given product
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Orders> Query = criteriaBuilder.createQuery(Orders.class);
		Root<Orders> fromProduct = Query.from(Orders.class);
		Query.select(fromProduct); // select all from Product

		Query.where(criteriaBuilder.equal(fromProduct.get("product"), product_id));
	
		List<Orders> list = em.createQuery(Query).getResultList();
		
		PrintOrders(list);
		
		return list;
	}

	
	public void deleteOrder(int id) {
		//Delete order by order_id
		Orders order = em.find(Orders.class, id);
		removeOrder(order);
		System.out.println("Order with order_id "+order.getOrder_id()+" has been deleted...");
	}
	
	
	public void removeOrder(Orders order) {
		em.getTransaction().begin();
		em.remove(order);
		em.getTransaction().commit();
	}


	
	public List<Orders> GetAllOrders() {
		//get list of orders
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Orders> Query = criteriaBuilder.createQuery(Orders.class);
		Root<Orders> fromOrders = Query.from(Orders.class);
		Query.select(fromOrders); // select all from Orders
		
		List<Orders> list = em.createQuery(Query).getResultList();
		
		PrintOrders(list);
		
		return list;
		
	}
	
}
