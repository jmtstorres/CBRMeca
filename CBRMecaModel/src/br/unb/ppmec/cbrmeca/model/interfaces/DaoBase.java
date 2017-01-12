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

public abstract class DaoBase<T> implements IDaoBase<T> {
	private Class<T> entityClass;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setSession(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public DaoBase() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		List<T> list = getSessionFactory().getCurrentSession().createQuery("from " + entityClass.getName()).list();
		transaction.commit();
		return list;
	}

	public void delete(T domain) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().delete(domain);
		transaction.commit();
	}

	public void save(T domain) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().save(domain);
		transaction.commit();
	}

	public void update(T domain) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().update(domain);
		transaction.commit();
	}

	public T get(Serializable id) {
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		T o = (T) getSessionFactory().getCurrentSession().get(entityClass, id);
		transaction.commit();
		return o;
	}

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

	@SuppressWarnings("unchecked")
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(entityClass.getClass(), "a");
		Conjunction e = Restrictions.conjunction();
		e.add(Subqueries.exists(detachedCriteria));
		criteria.add(e);
		return criteria.list();
	}
	
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
	
	@SuppressWarnings("unchecked")
	public int count(){
		Transaction transaction = getSessionFactory().getCurrentSession().beginTransaction();
		List<T> list = getSessionFactory().getCurrentSession().createQuery("from " + entityClass.getName()).list();
		transaction.commit();
		return list.size();
	}
}