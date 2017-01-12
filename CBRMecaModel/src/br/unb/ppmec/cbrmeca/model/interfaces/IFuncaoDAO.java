package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Funcao;

public interface IFuncaoDAO extends IDaoBase<Funcao>{
	Funcao getByStrDescriptor(String pattern);
	List<Funcao> getListByStrDescriptor(String pattern);
}
