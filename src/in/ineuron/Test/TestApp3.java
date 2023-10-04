package in.ineuron.Test;

import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.hibernate.query.Query;

import in.ineuron.Model.Product;
import in.ineuron.Util.HibernateUtil;

public class TestApp3 {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		Session session = null;
		
		
		try
		{
			session = HibernateUtil.getSession();
			//Prepare query object to hold HQL
			Query<Object[]> query=session.createQuery("select pname,price,qty From Product where pname IN(:prod1,:prod2)");
			
			//set the values for Named Parameter
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "pesticide");
			
			
			//Execute the query
			List<Object[]> products= query.list();
			System.out.println("PNAME \t PRICE \tQTY");
			
			//Process the list object
			products.forEach(row->{
			for(Object object : row )
			{
				System.out.print(object+"\t");
			}
			System.out.println();
			}
			
			);
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
