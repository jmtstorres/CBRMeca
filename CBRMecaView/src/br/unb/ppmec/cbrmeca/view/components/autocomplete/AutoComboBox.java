/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.components.autocomplete;

import java.awt.Color;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/*
 * From:
 * https://gist.github.com/resarahadian/5076440
 */

/**
 * The Class AutoComboBox.
 */
public class AutoComboBox extends JComboBox<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6082869206291896354L;
	
	/** The key word. */
	String keyWord[] = { "Digite a função (Verbo + objeto)" };
	
	/** The my vector. */
	Vector<String> myVector = new Vector<String>();
	
	/** The text. */
	private JTextField text;

	/**
	 * Instantiates a new auto combo box.
	 */
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
	
	/**
	 * Sets the background color.
	 *
	 * @param color the new background color
	 */
	public void setBackgroundColor(Color color){
		text.setBackground(color);
	}

	/**
	 * set the item list of the AutoComboBox.
	 *
	 * @param keyWord            an String array
	 */
	public void setKeyWord(String[] keyWord) {
		this.keyWord = keyWord;
		setMyVectorInitial();
	}

	/**
	 * Sets the my vector.
	 */
	private void setMyVector() {
		int a;
		for (a = 0; a < keyWord.length; a++) {
			myVector.add(keyWord[a]);
		}
	}

	/**
	 * Sets the my vector initial.
	 */
	private void setMyVectorInitial() {
		myVector.clear();
		int a;
		for (a = 0; a < keyWord.length; a++) {

			myVector.add(keyWord[a]);
		}
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return this.text.getText();
	}
	
	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text){
		this.text.setText(text);
	}
}