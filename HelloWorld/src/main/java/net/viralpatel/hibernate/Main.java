package net.viralpatel.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Main {
	static SessionFactory sessionFactory;
	static Session session;
	
	

	public static void main(String[] args) {
		// Write
		System.out.println("******* WRITE *******");
		Employee empl = new Employee("Jack", "Bauer", new Date(
				System.currentTimeMillis()), "911");
		
		empl.setEmployeeType(EmployeeType.FULL_TIME_EMPLOYEE);
		
		Employee emp2 = new Employee("second", "person", new Date(
				System.currentTimeMillis()), "222");
		
		empl.setEmployeeType(EmployeeType.PART_TIME_EMPLOYEE);
		
		
		Department d = new Department();
		d.setDepartment_id(22);
		d.setDepartmentName("Test Department");
		d.setDepartmentDescription("Thisis a sample description of the sample department");
		
		empl.setDepartment(d);
		emp2.setDepartment(d);
		List employeLst = new ArrayList();
		employeLst.add(empl);
		employeLst.add(emp2);
		d.setEmployeeLst(employeLst);
		
		getSession();
		save(d);
		
		/*getSession();
		empl = save(empl);*/
//		read();

	}

	public static Session getSession() {
		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
			session = sessionFactory.openSession();
			return session;
		} else if(session.isDirty()){
			session = sessionFactory.openSession();
			return session;
		}
		else {
			return session;
		}
		
	}
	private static Employee save(Employee employee) {
		
		session.beginTransaction();

		Long id = (Long) session.save(employee);

		session.getTransaction().commit();

		session.close();
 
		return employee;
	}
	
private static Department save(Department department) {
		
		session.beginTransaction();

		session.save(department);

		session.getTransaction().commit();

//		session.close();
 
		return department;
	}
	
	/*private static Employee read(){
		SessionFactory sf = buildSessionFactory();
		Session session = sf.openSession();

		Employee e = (Employee)session.get(Employee.class, 20L);
		System.out.println("The First Name:"+e.getMy());
		
		session.close();
		return e;
		
	}
	*/
		
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
