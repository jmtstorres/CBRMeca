/*
 * 
 */
package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;

// TODO: Auto-generated Javadoc
/**
 * The Interface IFuncaoCasoDAO.
 */
public interface IFuncaoCasoDAO extends IDaoBase<FuncaoCaso>{
	
	/**
	 * Gets the by id funcao caso.
	 *
	 * @param idFuncaoCaso the id funcao caso
	 * @return the by id funcao caso
	 */
	FuncaoCaso getByIdFuncaoCaso(Integer idFuncaoCaso);
	
	/**
	 * Gets the by id funcao pai.
	 *
	 * @param idFuncaoPai the id funcao pai
	 * @return the by id funcao pai
	 */
	List<FuncaoCaso> getByIdFuncaoPai(Integer idFuncaoPai);
	
	/**
	 * Gets the by id type.
	 *
	 * @param tipo_funcao the tipo_funcao
	 * @return the by id type
	 */
	List<FuncaoCaso> getByIdType(Integer tipo_funcao);
	
	/**
	 * Gets the by id funcao.
	 *
	 * @param id_funcao the id_funcao
	 * @return the by id funcao
	 */
	List<FuncaoCaso> getByIdFuncao(Integer id_funcao);
}
