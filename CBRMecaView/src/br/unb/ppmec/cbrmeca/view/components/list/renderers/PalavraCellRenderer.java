/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.components.list.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import br.unb.ppmec.cbrmeca.db.model.Palavra;

// TODO: Auto-generated Javadoc
/**
 * The Class PalavraCellRenderer.
 */
public class PalavraCellRenderer extends JLabel implements ListCellRenderer<Palavra> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4438086479932955268L;

	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Palavra> list, Palavra value, int index,
			boolean isSelected, boolean cellHasFocus) {

		JLabel c = new JLabel(value.getStrPalavra());
		c.setOpaque(true);
		
		if (isSelected) {
	        c.setBackground(Color.LIGHT_GRAY);
	        c.setForeground(Color.WHITE);
	      } else {
	        c.setBackground(Color.WHITE);
	        c.setForeground(Color.BLACK);
	      }
		
		return c;
	}
}