package org;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BaseDao <T> {

	private static Logger logger = LogManager.getLogger(BaseDao.class);
	
	Class<T> entity;
	
	public BaseDao(Class<T> entity) {
		this.entity = entity;
	}
	
	public T save(T entity) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx2 = session.beginTransaction();
			T savedEntity = (T) session.merge((String) null, entity);
			tx2.commit();
			return savedEntity;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
		
	public List<T> findAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			String simpleName = entity.getSimpleName();
			String lowerCaseSimpleName = simpleName.toLowerCase();
			Query createQuery = session.createQuery("Select " + lowerCaseSimpleName + " From " + simpleName + " " + lowerCaseSimpleName);
			return (List<T>) createQuery.list();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
