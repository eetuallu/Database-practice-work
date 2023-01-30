package uwasa.Project_work_shop_DB.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uwasa.Project_work_shop_DB.JPAUtil;
import uwasa.Project_work_shop_DB.entities.Customer;
import uwasa.Project_work_shop_DB.entities.Product;
import uwasa.Project_work_shop_DB.entities.Warehouse;

/**methods to handle warehouse table*/
public class Warehouse_services {
	
	private Product_services product_services = new Product_services();
	
	private EntityManager em;

	public Warehouse_services() {
		em = JPAUtil.getEntityManager();
	}
	
	public void AddWarehouse(String c, String a) {
		//add new warehouse
		//create a new order
		em.getTransaction().begin();
		Warehouse w = new Warehouse();
		w.setCity(c);
		w.setAddress(a);
		em.persist(w);
		em.getTransaction().commit();
		System.out.println("\n\nNew warehouse added to the database: address: "+ w.getAddress()+", "+w.getCity());

	}
	
	public void DeleteWarehouse(int id) {
		//deletes warehouse, and delete all related products
		if(em.find(Warehouse.class, id) == null) {
			System.out.println("Warehouse with id "+id+" does not exist");
		}
		else {
			List<Product> products = product_services.FindProductByWarehouseId(id);
			for(Product p : products) {
				System.out.println(p);
			}
			if(products.size() == 0) {
				System.out.print("\n\nRelated products were not found\n");
				Warehouse w = findWarehouseById(id);
				RemoveWarehouse(w);
				System.out.print("\n\nWarehouse " + w + " has been deleted");
			}else {
				for (Product p : products) {
					product_services.DeleteProduct(p.getProduct_id());
				}
				System.out.print("related products have been deleted.");
				Warehouse wh = findWarehouseById(id);
				RemoveWarehouse(wh);
				System.out.print("Product " + wh + " has been deleted");
			}	
		}
	}
	
	public void PrintAllWarehouses() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Warehouse> Query = criteriaBuilder.createQuery(Warehouse.class);
		Root<Warehouse> fromWarehouse = Query.from(Warehouse.class);
		Query.select(fromWarehouse); // select all from Warehouse
		
		List<Warehouse> list = em.createQuery(Query).getResultList();
		System.out.println("\n\nid\tcity\taddress\n------------------------------------------------------");
		for(Warehouse w : list) {
			System.out.println(w.getWarehouse_id()+"\t"+w.getCity()+"\t"+w.getAddress());
		}
		System.out.println("------------------------------------------------------");
	}
	
	public Warehouse findWarehouseById(int id)
	{
		Warehouse w = em.find(Warehouse.class,id);
		return w;
	}

	
	
	public void RemoveWarehouse(Warehouse w) {
		em.getTransaction().begin();
		em.remove(w);
		em.getTransaction().commit();
	}
	
}
