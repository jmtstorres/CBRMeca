/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Solucao;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISolucaoDAO.
 */
public interface ISolucaoDAO extends IDaoBase<Solucao>{
	
	/**
	 * Gets the by id funcao e caso.
	 *
	 * @param idFuncaoCaso the id funcao caso
	 * @param idCaso the id caso
	 * @return the by id funcao e caso
	 */
	List<Solucao> getByIdFuncaoECaso(Integer idFuncaoCaso, Integer idCaso);
}
