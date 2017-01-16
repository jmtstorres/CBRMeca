/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

// TODO: Auto-generated Javadoc
/**
 * Base interface for CRUD operations and common queries.
 *
 * @param <T> the generic type
 */
public interface IDaoBase<T> {
     
    /**
     * Load all.
     *
     * @return the list
     */
    public List<T> loadAll();
    
    /**
     * Save.
     *
     * @param domain the domain
     */
    public void save(T domain);
    
    /**
     * Update.
     *
     * @param domain the domain
     */
    public void update(T domain);
    
    /**
     * Delete.
     *
     * @param domain the domain
     */
    public void delete(T domain);
    
    /**
     * Gets the.
     *
     * @param id the id
     * @return the t
     */
    public T get(Serializable id);
     
    /**
     * Get list by criteria.
     *
     * @param detachedCriteria the domain query criteria, include condition and the orders.
     * @return the list by criteria
     */
    public List<T> getListByCriteria(DetachedCriteria detachedCriteria);
    
    /**
     * Gets the list by criteria.
     *
     * @param detachedCriteria the detached criteria
     * @param offset the offset
     * @param size the size
     * @return the list by criteria
     */
    public List<T> getListByCriteria(DetachedCriteria detachedCriteria, int offset, int size);   
}