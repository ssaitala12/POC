package net.viralpatel.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.hib.onetoone.Citizen;
import com.hib.onetoone.VoterCard;

public class Main {
	static SessionFactory sessionFactory;
	static Session session;
	
	

	public static void main(String[] args) {
		// Testing OneToOne unidirectional Mapping -- Start
		Citizen citizen1 = new Citizen();
		citizen1.setName("Srinivas");
		citizen1.setAddress("Miyapur");
		
		VoterCard voterCard1 = new VoterCard();
		voterCard1.setIssuedBy("Government Of Telangana");
		
		citizen1.setVoterCard(voterCard1);
		
		/*getSession();
		save(citizen1);*/
		
		// Testing OneToOne unidirectional Mapping -- End
		
		// Testing one-to-one bidirectional mapping -- start.
		voterCard1.setCitizen(citizen1);
		getSession();
		save(voterCard1);
		// Testing one-to-one bidirectional mapping -- start.
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
	private static void save(Citizen citizen) {
		
		session.beginTransaction();
		session.save(citizen);
		session.getTransaction().commit();
		session.close();
 	}
	
private static void save(VoterCard voterCard) {
		
		session.beginTransaction();
		session.save(voterCard);
		session.getTransaction().commit();
		session.close();
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
