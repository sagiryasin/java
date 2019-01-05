package org.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.HibernateUtil;
import org.User;
import org.junit.Test;

public class ActorServiceTest {
	
	private static Logger logger = LogManager.getLogger(ActorServiceTest.class);
	
	@Test
	public void findAllFilm() {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			String simpleName = "User";
			String lowerCaseSimpleName = simpleName.toLowerCase();
			Query createQuery = session.createQuery("Select " + lowerCaseSimpleName + " From " + simpleName + " " + lowerCaseSimpleName);
			ArrayList<User> list = (ArrayList<User>) createQuery.list();
			for (User list2 : list) {
				System.out.println(list2.getEmail());
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@Test
	public void save() {
		int result = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx2 = session.beginTransaction();
			User save = new User();
			save.setname("gstagdta");
			save.setEmail("gstagdta");
			save.setPassword("gstagdta");
			save.setGender("gstagdta");
			save.setAddress("gstagdta");
			User savedEntity = (User) session.merge((String) null, save);
			tx2.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}