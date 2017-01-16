/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.components.autocomplete;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;

// TODO: Auto-generated Javadoc
/*
 * From:
 * https://gist.github.com/resarahadian/5076440
 */

/**
 * The listener interface for receiving combo events.
 * The class that is interested in processing a combo
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addComboListener<code> method. When
 * the combo event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ComboEvent
 */
public class ComboListener implements KeyListener {

	/** The cb listener. */
	@SuppressWarnings("rawtypes")
	JComboBox cbListener;

	/** The vector. */
	@SuppressWarnings("rawtypes")
	Vector vector;

	/**
	 * Instantiates a new combo listener.
	 *
	 * @param cbListenerParam the cb listener param
	 * @param vectorParam the vector param
	 */
	@SuppressWarnings("rawtypes")
	public ComboListener(JComboBox cbListenerParam, Vector vectorParam) {
		cbListener = cbListenerParam;
		vector = vectorParam;
	}
	
	/**
	 * Update.
	 *
	 * @param key the key
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void update(KeyEvent key){
		JTextField field = ((JTextField) key.getSource());
		int position = field.getCaretPosition();
		String text = field.getText();
		int selStart = field.getSelectionStart();
		int selEnd = field.getSelectionEnd();

		cbListener.setModel(new DefaultComboBoxModel(getFilteredList(text)));
		cbListener.setSelectedIndex(-1);

		JTextField component = ((JTextField) cbListener.getEditor().getEditorComponent());
		component.setText(text);
		component.setCaretPosition(position);
		component.setSelectionStart(selStart);
		component.setSelectionEnd(selEnd);

		cbListener.getParent().getParent().getParent().revalidate();
		cbListener.getParent().getParent().getParent().repaint();
		cbListener.showPopup();
	}
	
	/**
	 * Gets the filtered list.
	 *
	 * @param text the text
	 * @return the filtered list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getFilteredList(String text) {
		Vector v = new Vector();

		if (text.length() > 2) {
			FuncaoDAOImpl funcaoDao = new FuncaoDAOImpl();
			List<Funcao> lista = funcaoDao.getListByStrDescriptor(text);
			for (Funcao funcao : lista) {
				v.add(funcao.getStrFuncaoVerbo() + " "
						+ funcao.getStrFuncaoObjeto());
			}
		}

		return v;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent key) {
		update(key);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent key) {
		update(key);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent key) {
		update(key);
	}
}