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

/*
 * From:
 * https://gist.github.com/resarahadian/5076440
 */

public class ComboListener implements KeyListener {

	@SuppressWarnings("rawtypes")
	JComboBox cbListener;

	@SuppressWarnings("rawtypes")
	Vector vector;

	@SuppressWarnings("rawtypes")
	public ComboListener(JComboBox cbListenerParam, Vector vectorParam) {
		cbListener = cbListenerParam;
		vector = vectorParam;
	}
	
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

	public void keyPressed(KeyEvent key) {
		update(key);
	}

	@Override
	public void keyTyped(KeyEvent key) {
		update(key);
	}

	@Override
	public void keyReleased(KeyEvent key) {
		update(key);
	}
}