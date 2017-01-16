/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Caso;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICasoDAO.
 */
public interface ICasoDAO extends IDaoBase<Caso>{

	/**
	 * Gets the started with.
	 *
	 * @param pattern the pattern
	 * @return the started with
	 */
	List<Caso> getStartedWith(String pattern);
	
	/**
	 * Gets the by id funcao caso raiz.
	 *
	 * @param id the id
	 * @return the by id funcao caso raiz
	 */
	Caso getByIDFuncaoCasoRaiz(int id);
	
	/**
	 * Gets the exact title.
	 *
	 * @param pattern the pattern
	 * @return the exact title
	 */
	Caso getExactTitle(String pattern);
}
