package in.ineuron.Test;

import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.hibernate.query.Query;

import in.ineuron.Model.Product;
import in.ineuron.Util.HibernateUtil;

public class TestApp4 {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		Session session = null;
		
		
		try
		{
			session = HibernateUtil.getSession();
			//Prepare query object to hold HQL
			//Query<String> query=session.createQuery("select pname From Product where pname IN(:prod1,:prod2)");
			Query<Integer> query=session.createQuery("select price From Product where pname IN(:prod1,:prod2)");
			
			//set the values for Named Parameter
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "pesticide");
			
			
			//Execute the query
			List<Integer> products= query.list();
			//System.out.println("PNAME");
			System.out.println("PRICE");
			
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
