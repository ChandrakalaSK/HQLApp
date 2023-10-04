package in.ineuron.Test;

import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.hibernate.query.Query;

import in.ineuron.Model.Product;
import in.ineuron.Util.HibernateUtil;

public class TestApp {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		Session session = null;
		
		
		try
		{
			session = HibernateUtil.getSession();
			//Prepare query object to hold HQL
			Query<Product> query=session.createQuery("From Product");
			
			
			//Execute the query
			List<Product> products= query.list();
			
			//Process the list object
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
