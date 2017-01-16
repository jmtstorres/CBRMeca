/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Funcao;

// TODO: Auto-generated Javadoc
/**
 * The Interface IFuncaoDAO.
 */
public interface IFuncaoDAO extends IDaoBase<Funcao>{
	
	/**
	 * Gets the by str descriptor.
	 *
	 * @param pattern the pattern
	 * @return the by str descriptor
	 */
	Funcao getByStrDescriptor(String pattern);
	
	/**
	 * Gets the list by str descriptor.
	 *
	 * @param pattern the pattern
	 * @return the list by str descriptor
	 */
	List<Funcao> getListByStrDescriptor(String pattern);
}
