package br.unb.ppmec.cbrmeca.model.interfaces;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Palavra;

public interface IPalavraDAO extends IDaoBase<Palavra>{
	List<Palavra> getStartedWith(String pattern);

	Palavra getMatching(String pattern);
}
