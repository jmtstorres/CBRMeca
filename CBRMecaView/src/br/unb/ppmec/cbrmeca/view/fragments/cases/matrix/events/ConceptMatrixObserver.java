/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about ConceptMatrix information as the ConceptMatrix is constructed.
 */
public class ConceptMatrixObserver {
	
	/** The _listeners. */
	/*
	 * Listeners para escolha do conceito
	 */
	private List<MatrixConceptAddedListener> _listeners = new ArrayList<MatrixConceptAddedListener>();
	
	/** The Constant INSTANCE. */
	private static final ConceptMatrixObserver INSTANCE = new ConceptMatrixObserver();
	
	/**
	 * This method is called when information about an ConceptMatrix
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @return the instance
	 */
	public static ConceptMatrixObserver getInstance(){
		return INSTANCE;
	}
	
	/**
	 * This method is called when information about an ConceptMatrix
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param l the l
	 */
	public synchronized void addConceptAddedListener( MatrixConceptAddedListener l ) {
        _listeners.add( l );
    }
    
    /**
     * This method is called when information about an ConceptMatrix
     * which was previously requested using an asynchronous
     * interface becomes available.
     *
     * @param l the l
     */
    public synchronized void removeConceptAddedListener( MatrixConceptAddedListener l ) {
        _listeners.remove( l );
    }
    
    /**
     * This method is called when information about an ConceptMatrix
     * which was previously requested using an asynchronous
     * interface becomes available.
     *
     * @param conceito the conceito
     */
    public synchronized void sendConceptSelectedEvent(Conceito conceito) {
        ConceptAddedEvent event = new ConceptAddedEvent(conceito);
        Iterator<MatrixConceptAddedListener> listeners = _listeners.iterator();
        
        while( listeners.hasNext() ) {
            ((MatrixConceptAddedListener)listeners.next()).conceptAdded(event);
        }
    }
}
