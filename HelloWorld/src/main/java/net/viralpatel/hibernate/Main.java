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
