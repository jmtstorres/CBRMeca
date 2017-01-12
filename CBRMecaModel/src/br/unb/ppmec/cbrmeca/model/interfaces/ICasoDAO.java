package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Caso;

public interface ICasoDAO extends IDaoBase<Caso>{

	List<Caso> getStartedWith(String pattern);
	Caso getByIDFuncaoCasoRaiz(int id);
	Caso getExactTitle(String pattern);
}
