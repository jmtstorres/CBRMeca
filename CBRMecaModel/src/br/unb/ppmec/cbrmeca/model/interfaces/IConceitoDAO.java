/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConceitoDAO.
 */
public interface IConceitoDAO extends IDaoBase<Conceito>{
	
	/**
	 * Gets the started with.
	 *
	 * @param pattern the pattern
	 * @return the started with
	 */
	List<Conceito> getStartedWith(String pattern);
	
	/**
	 * Gets the started with.
	 *
	 * @param pattern the pattern
	 * @param limit the limit
	 * @return the started with
	 */
	List<Conceito> getStartedWith(String pattern, int limit);
	
	/**
	 * Gets the exact.
	 *
	 * @param pattern the pattern
	 * @return the exact
	 */
	Conceito getExact(String pattern);
}
