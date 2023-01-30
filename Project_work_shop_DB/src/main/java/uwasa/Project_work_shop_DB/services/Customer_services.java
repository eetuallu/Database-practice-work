package uwasa.Project_work_shop_DB.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uwasa.Project_work_shop_DB.JPAUtil;
import uwasa.Project_work_shop_DB.entities.Customer;
import uwasa.Project_work_shop_DB.entities.Orders;

/** Services regarding the customer table*/
public class Customer_services {
	
	private Order_services order_services = new Order_services();
	
	private EntityManager em;

	public Customer_services() {
		em = JPAUtil.getEntityManager();
	}

	
	public boolean Login(String password, String username) {
		//check if the password and username exists in the database 
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> Query = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> fromCustomer = Query.from(Customer.class);
		Query.select(fromCustomer); // select all from Customer
	
		List<Customer> list = em.createQuery(Query).getResultList();
		
		boolean login = false;
		//iterate through the customer table and compare passwords and usernames with the given values
		for(Customer c :list) {
			if(c.getPassword().equals(password) && c.getUsername().equals(username)) {
				System.out.println("Logged in successfully ");
				login = true;
			}
		}
		if(login==false) {
			System.out.println("Login failed...");
		}
		
		return login;
	}

	public void PrintAllCustomers() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> Query = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> fromCustomer = Query.from(Customer.class);
		Query.select(fromCustomer); // select all from Customer
		
		List<Customer> list = em.createQuery(Query).getResultList();
		System.out.println("\n\n------------------------------------------");
		for(Customer c : list) {
			System.out.println(c.getCustomer_id()+"\t"+c.getFirst_name()+"\t"+c.getLast_name());
		}
		System.out.println("------------------------------------------");
	}
	
	public void AddCustomer(String first_name, String last_name, String city, String address, String phonenumber, String email,
			String password, String username) {
		//add new customer
		em.getTransaction().begin();
		Customer cust = new Customer();
		cust.setFirst_name(first_name);
		cust.setLast_name(last_name);
		cust.setCity(city);
		cust.setAddress(address);
		cust.setPhonenumber(phonenumber);
		cust.setEmail(email);
		cust.setPassword(password);
		cust.setUsername(username);
		em.persist(cust);
		em.getTransaction().commit();
		System.out.println("New customer added: "+ first_name+" "+last_name);
		
	}
	
	public Customer findCustomerById(int id)
	{
		Customer customer = em.find(Customer.class,id);
		return customer;
	}
	
	public void deleteCustomer(int customer_id) {
		
		if(em.find(Customer.class, customer_id) == null) {
			System.out.println("Customer with customer id "+customer_id+" does not exist");
		}
		else {
			List<Orders> orders = order_services.FindOrderByCustomerId(customer_id);
			for(Orders order : orders) {
				System.out.println(order);
			}
			if(orders.size() == 0) {
				System.out.print("Related orders were not found.");
				Customer cus = findCustomerById(customer_id);
				removeCustomer(cus);
				System.out.print("Customer" + cus.getFirst_name()+ " "+cus.getLast_name() + "has been deleted");
			}else {
				for (Orders order : orders) {
					order_services.removeOrder(order);
				}
				
				System.out.print("related order have been deleted.");
				Customer cus = findCustomerById(customer_id);
				removeCustomer(cus);
				System.out.print("Customer" + cus.getFirst_name()+ " "+cus.getLast_name() + "has been deleted");
			}	
		}
	}
	
	public void removeCustomer(Customer customer) 
	{
		//remove customer from the database
		em.getTransaction().begin();
		em.remove(customer);
		em.getTransaction().commit();
	}
	
	 
}
