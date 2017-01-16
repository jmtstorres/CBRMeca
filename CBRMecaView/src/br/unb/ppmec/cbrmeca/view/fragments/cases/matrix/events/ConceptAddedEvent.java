/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events;

import java.util.EventObject;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceptAddedEvent.
 */
public class ConceptAddedEvent extends EventObject{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 845390061989530096L;

	/** The conceito. */
	private Conceito conceito;

	/**
	 * Instantiates a new concept added event.
	 *
	 * @param conceito the conceito
	 */
	public ConceptAddedEvent(Conceito conceito) {
		super(conceito);
		this.conceito = conceito;
	}

	/**
	 * Gets the conceito.
	 *
	 * @return the conceito
	 */
	public Conceito getConceito() {
		return conceito;
	}
}
