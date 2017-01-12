package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Solucao;

public interface ISolucaoDAO extends IDaoBase<Solucao>{
	List<Solucao> getByIdFuncaoECaso(Integer idFuncaoCaso, Integer idCaso);
}
