/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sun.media.jfxmedia.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class HibernateUtil.
 */
public class HibernateUtil {
	
	/** The session factory. */
	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			Logger.logMsg(Logger.ERROR, "Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}