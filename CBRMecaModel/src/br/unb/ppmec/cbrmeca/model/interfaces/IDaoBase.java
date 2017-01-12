package br.unb.ppmec.cbrmeca.model.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Base interface for CRUD operations and common queries
 */
public interface IDaoBase<T> {
     
    public List<T> loadAll();
    public void save(T domain);
    public void update(T domain);
    public void delete(T domain);
    public T get(Serializable id);
     
    /**
     * Get list by criteria
     * @param detachedCriteria the domain query criteria, include condition and the orders.
     * @return
     *
     */
    public List<T> getListByCriteria(DetachedCriteria detachedCriteria);
    public List<T> getListByCriteria(DetachedCriteria detachedCriteria, int offset, int size);   
}