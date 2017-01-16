/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving matrixConceptAdded events.
 * The class that is interested in processing a matrixConceptAdded
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addMatrixConceptAddedListener<code> method. When
 * the matrixConceptAdded event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MatrixConceptAddedEvent
 */
public interface MatrixConceptAddedListener {
	
	/**
	 * Concept added.
	 *
	 * @param event the event
	 */
	public void conceptAdded(ConceptAddedEvent event);

}