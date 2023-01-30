package uwasa.Project_work_shop_DB.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uwasa.Project_work_shop_DB.JPAUtil;
import uwasa.Project_work_shop_DB.entities.Product;
import uwasa.Project_work_shop_DB.entities.Product_review;

public class Product_review_services {

	private EntityManager em;

	public Product_review_services() {
		em = JPAUtil.getEntityManager();
	}

//	private Product_review_services product_review_services = new Product_review_services();
	
	public void AddNewReview(int p_id, String title, int rating, String content) {
		//adds new review
		em.getTransaction().begin();
		Product p = em.find(Product.class, p_id);
		Product_review pr = new Product_review();
		pr.setProduct(p);
		pr.setTitle(title);
		pr.setRating(rating);
		pr.setContent(content);
		em.persist(pr);
		em.getTransaction().commit();
		System.out.println("New product review added for product "+ p.getDescription());
	}
	
	public List<Product_review> printReviewsByProductId(int p_id) {
			//find reviews that are related to given product
			try {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<Product_review> Query = criteriaBuilder.createQuery(Product_review.class);
				Root<Product_review> fromProduct = Query.from(Product_review.class);
				Query.select(fromProduct); // select all from Product

				Query.where(criteriaBuilder.equal(fromProduct.get("product"), p_id));

				List<Product_review> list = em.createQuery(Query).getResultList();
				
				System.out.println("\n\nreviews for product "+ em.find(Product.class, p_id).getDescription()+": \n---------------------------------------------------------------------------\n"
						+ "title\t\trating\t\tpublished\n---------------------------------------------------------------------------");
				
				for(Product_review pr : list) {
					System.out.println(pr.getTitle()+"\t"+pr.getRating()+"\t"+pr.getPublished());
				}
				
				return list;
			} catch (Exception e) {
				System.out.println("reviews not found for given product id");
			}
			return null;
	}
	
	public void GetAverageOfReviews() {
		//prints the average value of given reviews by product_id
	}
	
	public List<Product_review> FindReviewByProductId(int product_id) {
		//find reviews for given product
		List<Product_review> list;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Product_review> Query = criteriaBuilder.createQuery(Product_review.class);
			Root<Product_review> fromProduct = Query.from(Product_review.class);
			Query.select(fromProduct); // select all from Product_review

			Query.where(criteriaBuilder.equal(fromProduct.get("product"), product_id));

			list = em.createQuery(Query).getResultList();
			return list;
		} catch (Exception e) {
			System.out.println("reviews not found for given product id");
		}
				return null;
	}
	
	public void removeReview(Product_review pr) {
		em.getTransaction().begin();
		em.remove(pr);
		em.getTransaction().commit();
	}

	
	
	
}
