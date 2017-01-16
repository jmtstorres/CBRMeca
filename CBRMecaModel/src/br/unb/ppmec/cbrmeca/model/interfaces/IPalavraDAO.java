/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Palavra;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPalavraDAO.
 */
public interface IPalavraDAO extends IDaoBase<Palavra>{
	
	/**
	 * Gets the started with.
	 *
	 * @param pattern the pattern
	 * @return the started with
	 */
	List<Palavra> getStartedWith(String pattern);

	/**
	 * Gets the matching.
	 *
	 * @param pattern the pattern
	 * @return the matching
	 */
	Palavra getMatching(String pattern);
}
