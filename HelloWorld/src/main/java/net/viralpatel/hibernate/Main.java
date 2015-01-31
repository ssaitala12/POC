package net.viralpatel.hibernate;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Main {

	public static void main(String[] args) {
		// Write
		System.out.println("******* WRITE *******");
		Employee empl = new Employee("Jack", "Bauer", new Date(
				System.currentTimeMillis()), "911");
		empl.setId(20L);
		empl = save(empl);
		read();

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
	
	private static Employee read(){
		SessionFactory sf = buildSessionFactory();
		Session session = sf.openSession();

		Employee e = (Employee)session.get(Employee.class, 20L);
		System.out.println("The First Name:"+e.getFirstname());
		
		session.close();
		return e;
		
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
