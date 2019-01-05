package org;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

	// Annotation based configuration
	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionAnnotationFactory() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			logger.info("Session Oluşturuldu");
			return sessionFactory;
		} catch (Throwable ex) {
			logger.error("Session Oluşturulamadı." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionAnnotationFactory();
		return sessionFactory;
	}
}