/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

// TODO: Auto-generated Javadoc
/**
 * The Class DaoBase.
 *
 * @param <T> the generic type
 */
public abstract class DaoBase<T> implements IDaoBase<T> {
	
	/** The entity class. */
	private Class<T> entityClass;

	/** The session factory. */
	private SessionFactory sessionFactory;

	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Sets the session.
	 *
	 * @param sessionFactory the new session
	 */
	public void setSession(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	/**
	 * Instantiates a new dao base.
	 */
	@SuppressWarnings("unchecked")
	public DaoBase() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#loadAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		List<T> list = getSessionFactory().getCurrentSession().createQuery("from " + entityClass.getName()).list();
		transaction.commit();
		return list;
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#delete(java.lang.Object)
	 */
	public void delete(T domain) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().delete(domain);
		transaction.commit();
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#save(java.lang.Object)
	 */
	public void save(T domain) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().save(domain);
		transaction.commit();
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#update(java.lang.Object)
	 */
	public void update(T domain) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().update(domain);
		transaction.commit();
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#get(java.io.Serializable)
	 */
	public T get(Serializable id) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		T o = (T) getSessionFactory().getCurrentSession().get(entityClass, id);
		transaction.commit();
		return o;
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#getListByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria,
			int offset, int size) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(entityClass.getClass(), "a");
		Conjunction e = Restrictions.conjunction();
		e.add(Subqueries.exists(detachedCriteria));
		criteria.add(e);
		return criteria.list();
	}
	
	/**
	 * Gets the list by criterions.
	 *
	 * @param criterions the criterions
	 * @return the list by criterions
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriterions(List<Criterion> criterions) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        for (Criterion criterion : criterions) {
            detachedCriteria.add(criterion);
        }
        
        Session s = getSessionFactory().getCurrentSession();
        Transaction t = s.beginTransaction();
        List<T> list = detachedCriteria.getExecutableCriteria(s).list();
        t.commit();
        
        return list;
    }
	
	/**
	 * Gets the list by criterion.
	 *
	 * @param criterion the criterion
	 * @return the list by criterion
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriterion(Criterion criterion) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.add(criterion);
        
        Session s = getSessionFactory().getCurrentSession();
        Transaction t = s.beginTransaction();
        List<T> list = detachedCriteria.getExecutableCriteria(s).list();
        t.commit();
        
        return list;
    }

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IDaoBase#getListByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(entityClass.getClass(), "a");
		Conjunction e = Restrictions.conjunction();
		e.add(Subqueries.exists(detachedCriteria));
		criteria.add(e);
		return criteria.list();
	}
	
	/**
	 * Gets the list by criterion.
	 *
	 * @param criterion the criterion
	 * @param limit the limit
	 * @return the list by criterion
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriterion(Criterion criterion, int limit) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.add(criterion);
        
        Session s = getSessionFactory().getCurrentSession();
        Transaction t = s.beginTransaction();
        List<T> list = detachedCriteria.getExecutableCriteria(s).setMaxResults(limit).list();
        t.commit();
        
        return list;
    }
	
	/**
	 * Count.
	 *
	 * @return the int
	 */
	@SuppressWarnings("unchecked")
	public int count(){
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		List<T> list = getSessionFactory().getCurrentSession().createQuery("from " + entityClass.getName()).list();
		transaction.commit();
		return list.size();
	}
}