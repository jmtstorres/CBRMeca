package br.com.ppmec.cbrmeca.control.models;

import java.util.List;
import java.util.Map;

import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;

/**
 * Interface ICBREngine.
 * @author Joao Marcelo
 */
public interface ICBREngine {
	
	/**
	 * Recupera casos similares da base.
	 *
	 * @param fCaso - funcao caso
	 * @param idTipoCaso - tipo do caso
	 * @param fDescriptor  - descritor da funcao
	 * @return lista de casos similares
	 */
	List<FuncaoCaso> retrieveSimilar(FuncaoCaso fCaso, int idTipoCaso, String fDescriptor);
	
	/**
	 * Retorna as solucoes correspondentes ao caso passado.
	 *
	 * @param similarCases - casos similares
	 * @return lista de solucoes para o caso
	 */
	Map<Integer, Integer> getSolutionsForCase(List<FuncaoCaso> similarCases);
}
