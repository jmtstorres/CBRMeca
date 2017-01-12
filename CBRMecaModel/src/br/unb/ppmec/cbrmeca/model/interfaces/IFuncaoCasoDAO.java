package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;

public interface IFuncaoCasoDAO extends IDaoBase<FuncaoCaso>{
	FuncaoCaso getByIdFuncaoCaso(Integer idFuncaoCaso);
	List<FuncaoCaso> getByIdFuncaoPai(Integer idFuncaoPai);
	List<FuncaoCaso> getByIdType(Integer tipo_funcao);
	List<FuncaoCaso> getByIdFuncao(Integer id_funcao);
}
