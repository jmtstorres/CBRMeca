package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

public interface IConceitoDAO extends IDaoBase<Conceito>{
	List<Conceito> getStartedWith(String pattern);
	List<Conceito> getStartedWith(String pattern, int limit);
	Conceito getExact(String pattern);
}
