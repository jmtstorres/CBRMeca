package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

public class ConceptMatrixObserver {
	/*
	 * Listeners para escolha do conceito
	 */
	private List<MatrixConceptAddedListener> _listeners = new ArrayList<MatrixConceptAddedListener>();
	
	private static final ConceptMatrixObserver INSTANCE = new ConceptMatrixObserver();
	
	public static ConceptMatrixObserver getInstance(){
		return INSTANCE;
	}
	
	public synchronized void addConceptAddedListener( MatrixConceptAddedListener l ) {
        _listeners.add( l );
    }
    
    public synchronized void removeConceptAddedListener( MatrixConceptAddedListener l ) {
        _listeners.remove( l );
    }
    
    public synchronized void sendConceptSelectedEvent(Conceito conceito) {
        ConceptAddedEvent event = new ConceptAddedEvent(conceito);
        Iterator<MatrixConceptAddedListener> listeners = _listeners.iterator();
        
        while( listeners.hasNext() ) {
            ((MatrixConceptAddedListener)listeners.next()).conceptAdded(event);
        }
    }
}
