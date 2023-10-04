package in.ineuron.Test;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.hibernate.query.Query;

import in.ineuron.Model.Product;
import in.ineuron.Util.HibernateUtil;

public class TestApp5 {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		Session session = null;
		
		
		try
		{
			session = HibernateUtil.getSession();
			//Prepare query object to hold HQL
			//Query<String> query=session.createQuery("select pname From Product where pname IN(:prod1,:prod2)");
			Query<Product> query=session.createQuery("From Product where pid =:pid");
			
			//set the values for Named Parameter
//			int pid = 1;
			System.out.println("Enter the id of the product to be fetched");
			@SuppressWarnings("resource")
			int pid  = new Scanner(System.in).nextInt();
			query.setParameter("pid",pid);
		
			
			//Execute the query
			List<Product> products= query.list();
			System.out.println(products.size());
			
			//Execute the query (select * from  product where pid = ?)
			Product product=query.uniqueResult();
			if(product != null)
			{
				System.out.println(product);
			}
			else
			{
				System.out.println("Record not available fopr given id "+pid );
			}
			System.out.println(product);
			
//			//Process the list object
//			if(!products.isEmpty())
//			{
//				Product product = products.get(0);
//				System.out.println( product);
//			}
//			else
//			{
//				System.out.println("Record not avaialble for given id "+pid);
//			}
			
			products.forEach(System.out::println);
			
			
			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
