package uwasa.Project_work_shop_DB;

import java.util.Scanner;

import uwasa.Project_work_shop_DB.services.Customer_services;
import uwasa.Project_work_shop_DB.services.Order_services;
import uwasa.Project_work_shop_DB.services.Product_review_services;
import uwasa.Project_work_shop_DB.services.Product_services;
import uwasa.Project_work_shop_DB.services.Warehouse_services;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Customer_services cs = new Customer_services();
        Order_services os = new Order_services();
        Product_services ps = new Product_services();
        Warehouse_services ws = new Warehouse_services();
        Product_review_services prs = new Product_review_services();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Hello, welcome to the shop\nplease login or register to proceed \nuse numbers on your keyboard to select\n----------------------------------------------");
        System.out.println("1 --> login\n2 --> register\n0 --> stop application");
        
        int select = sc.nextInt();
        boolean first = false;
        while(first == false) {
        	if(select==1) {
            	System.out.println("Give username + enter: ");
            	String un = sc.next();
            	System.out.println("Give password + enter: ");
            	String pw = sc.next();
            	if(cs.Login(pw, un)==true) {
            		first =true;
            	}
            	else {
            		System.out.println("1 --> login\n2 --> register\n0 --> stop application");
            		select = sc.nextInt();
            	}
            }if(select==2) {
            	System.out.println("Give firstname, lastname, city, address, phone, email, password, username: ");
            	String fn = sc.nextLine();
            	fn = sc.nextLine();
            	String ln = sc.nextLine();
            	String c = sc.nextLine();
            	String a = sc.nextLine();
            	String p = sc.nextLine();
            	String e = sc.nextLine();
            	String pw = sc.nextLine();
            	String un = sc.nextLine();
            	cs.AddCustomer(fn, ln, c, a, p, e, pw, un);
            	first=true;
            }if(select==0) {
            	break;
            }
        }
        
        while(select != 0) {
        	System.out.println("\n\n\n\nHello, welcome to the shop\nuse numbers on your keyboard to select\n----------------------------------------------");
            System.out.println("1 --> print all customers\n2 --> delete customer\n3 --> show all products\n4 --> create new order\n5 --> show orders of given customer\n6 --> delete order\n7 --> show all warehouses\n8 --> add new product\n9 --> update warehouse for product"
            		+ "\n10 --> delete product\n11 --> add new warehouse\n12 --> delete warehouse\n13 --> add review for product\n14 --> print all reviews for one product\n0 --> stop application\n\n\n\nr");
            
            select = sc.nextInt();
            
        	if(select == 1) {
        		cs.PrintAllCustomers();
        	}
			if(select == 2) {
				System.out.println("Give the id of the customer you want to delete... ");
				int id = sc.nextInt();
				cs.deleteCustomer(id);
			}
			if(select == 3) {
				ps.PrintAllProducts();
			}
			if(select == 4) {
				System.out.println("Give user for who the order is created... ");
				int c_id = sc.nextInt();
				System.out.println("Give product id... ");
				int p_id = sc.nextInt();
				os.CreateNewOrder(c_id, p_id);
			}
			if(select == 5) {
				System.out.println("Give user's id whose orders you want to show... ");
				int c_id = sc.nextInt();
				os.FindOrderByCustomerId(c_id);
			}
			if(select == 6) {
				System.out.println("Give order id... ");
				int o_id = sc.nextInt();
				os.deleteOrder(o_id);
			}
			if(select == 7) {
				ws.PrintAllWarehouses();
			}
			if(select == 8) {
				System.out.println("Give warehouse id... ");
				int w_id = sc.nextInt();
				System.out.println("Give description... ");
				String description = sc.nextLine();
				description = sc.nextLine();
				System.out.println("Give color... ");
				String color = sc.nextLine();
				System.out.println("Give price (as decimal)... ");
				Double price = sc.nextDouble();
				ps.AddNewProduct(w_id, description, color, price);
			}
			if(select == 9) {
				System.out.println("Give product id... ");
				int p_id = sc.nextInt();
				System.out.println("Give warehouse id... ");
				int w_id = sc.nextInt();
				ps.updateWarehouseForProduct(p_id, w_id);
			}
			if(select == 10) {
				System.out.println("Give product id... ");
				int p_id = sc.nextInt();
				ps.DeleteProduct(p_id);
			}
			if(select == 11) {
				System.out.println("Give warehouse city... ");
				String c = sc.nextLine();
				c = sc.nextLine();
				System.out.println("Give warehouse address... ");
				String a = sc.nextLine();
				ws.AddWarehouse(c, a);
			}
			if(select == 12) {
				System.out.println("Give warehouse id... ");
				int w_id = sc.nextInt();
				ws.DeleteWarehouse(w_id);
			}
			if(select == 13) {
				System.out.println("Give product id... ");
				int p_id = sc.nextInt();
				System.out.println("Give review title... ");
				String title = sc.nextLine();
				title = sc.nextLine();
				System.out.println("Give review text... ");
				String content = sc.nextLine();
				System.out.println("Give product rating (1-5)... ");
				int rating = sc.nextInt();

				prs.AddNewReview(p_id, title, rating, content);
			}
			if(select == 14) {
				System.out.println("Give product id... ");
				int p_id = sc.nextInt();
				prs.printReviewsByProductId(p_id);
			}
        }
        sc.close();
        
        
    }
    
}
