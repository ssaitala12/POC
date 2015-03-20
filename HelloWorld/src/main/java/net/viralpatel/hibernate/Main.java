package net.viralpatel.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Main {

	public static void main(String[] args) {
		SessionFactory sf = buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Employee"); // Here * will not be there in the query.
		
		System.out.println("The size of the complete query : "+query.list());
		/*
		 * Another flavour of the query is given below. The only difference which you can see in the 
		 * the normal jdbc calls and the HQL calls is that, in the HQL we get a resultset. Whereas,
		 * in the HQL we get a list of objects. 
		 */
		
		Query query1 = session.createQuery("from Employee where id = 2");
		System.out.println("The size of the query object is: "+query1.list());
		
		// pagination in hibernate.
		
//		Query paginationQuery = session.createQuery("select e.firstname, e.id from Employee e");
		
		// Interesting one, in order to get a map, you can give it as below.
		
		Query paginationQuery = session.createQuery("select new map(e.firstname, e.id) from Employee e");
		
		// As shown above, if you give two properties/columns, then the data will be stored as array[0] will contain the [0], [1] 
		// and array[1] contain [0], [1] elements etc.
		
		// you can use all the aggregate functions, such as max(e.id) etc.. in the above query.
		
		// As you see the above two lines, pagination in hibernate is very easy. You specify, from where the records should start.
		// For example if i have a total of 20 records, and I gave setFirstResult is 2, then hibernate will start from the second
		// record and skip the records before 2. Also, we give the max results, to show in total how many records to be displayed.
		// In this way, you can set the pagination in hiberate, by setting the firstresult and maxresults dynamically at runtime for
		// each page.
		paginationQuery.setFirstResult(2);
		paginationQuery.setMaxResults(3);
		System.out.println("size after pagination: "+paginationQuery.list().size());
				
	}

	private static Employee save(Employee employee) {
		SessionFactory sf = buildSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		Long id = (Long) session.save(employee);

		session.getTransaction().commit();

		session.close();

		return employee;
	}
	
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration()
            		.configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
