package br.unb.ppmec.cbrmeca.view.components.autocomplete;

import java.awt.Color;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/*
 * From:
 * https://gist.github.com/resarahadian/5076440
 */

public class AutoComboBox extends JComboBox<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6082869206291896354L;
	String keyWord[] = { "Digite a função (Verbo + objeto)" };
	Vector<String> myVector = new Vector<String>();
	
	private JTextField text;

	public AutoComboBox() {
		setModel(new DefaultComboBoxModel<String>(myVector));
		setSelectedIndex(-1);
		setEditable(true);
		text = (JTextField) this.getEditor().getEditorComponent();
		text.setFocusable(true);
		text.setText("");
		text.addKeyListener(new ComboListener(this, myVector));
		setMyVector();
	}
	
	public void setBackgroundColor(Color color){
		text.setBackground(color);
	}

	/**
	 * set the item list of the AutoComboBox
	 * 
	 * @param keyWord
	 *            an String array
	 */
	public void setKeyWord(String[] keyWord) {
		this.keyWord = keyWord;
		setMyVectorInitial();
	}

	private void setMyVector() {
		int a;
		for (a = 0; a < keyWord.length; a++) {
			myVector.add(keyWord[a]);
		}
	}

	private void setMyVectorInitial() {
		myVector.clear();
		int a;
		for (a = 0; a < keyWord.length; a++) {

			myVector.add(keyWord[a]);
		}
	}

	public String getText() {
		return this.text.getText();
	}
	
	public void setText(String text){
		this.text.setText(text);
	}
}