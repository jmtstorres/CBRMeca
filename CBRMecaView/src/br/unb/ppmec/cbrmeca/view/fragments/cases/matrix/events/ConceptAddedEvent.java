package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events;

import java.util.EventObject;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

public class ConceptAddedEvent extends EventObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 845390061989530096L;

	private Conceito conceito;

	public ConceptAddedEvent(Conceito conceito) {
		super(conceito);
		this.conceito = conceito;
	}

	public Conceito getConceito() {
		return conceito;
	}
}
