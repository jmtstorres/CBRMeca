/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.navigation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class CBRMecaMenu.
 */
public class CBRMecaMenu extends JPanel {

	/** The Constant LEFT. */
	public static final int LEFT = 0;
	
	/** The Constant MIDDLE. */
	public static final int MIDDLE = 1;
	
	/** The Constant RIGHT. */
	public static final int RIGHT = 2;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6398195420711409591L;

	/** The btn left. */
	private JButton btnLeft;
	
	/** The btn middle. */
	private JButton btnMiddle;
	
	/** The btn right. */
	private JButton btnRight;
	
	/** The lbl left. */
	private JLabel lblLeft;
	
	/** The lbl middle. */
	private JLabel lblMiddle;
	
	/** The lbl right. */
	private JLabel lblRight;

	/**
	 * Create the panel.
	 */
	public CBRMecaMenu() {
		setBackground(Color.WHITE);
		initialize();
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 80, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0 };
		setLayout(gridBagLayout);

		btnLeft = new JButton("Informa\u00E7\u00F5es");
		btnLeft.setContentAreaFilled(false);
		btnLeft.setFocusPainted(false);
		btnLeft.setVerticalTextPosition(SwingConstants.TOP);
		btnLeft.setIcon(new ImageIcon(CBRMecaMenu.class
				.getResource("/imgassets/dashboard.png")));
		btnLeft.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLeft.setVerticalAlignment(SwingConstants.TOP);
		btnLeft.setOpaque(false);
		btnLeft.setForeground(Color.BLACK);
		btnLeft.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLeft.setMargin(new Insets(0, 0, 0, 0));
		btnLeft.setBackground(Color.WHITE);
		btnLeft.setBorder(null);
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.fill = GridBagConstraints.BOTH;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 0;
		gbc_btnLeft.gridy = 0;
		add(btnLeft, gbc_btnLeft);

		btnMiddle = new JButton("Cadastro de casos");
		btnMiddle.setContentAreaFilled(false);
		btnMiddle.setFocusPainted(false);
		btnMiddle.setVerticalTextPosition(SwingConstants.TOP);
		btnMiddle.setIcon(new ImageIcon(CBRMecaMenu.class
				.getResource("/imgassets/cadastro.png")));
		btnMiddle.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMiddle.setVerticalAlignment(SwingConstants.TOP);
		btnMiddle.setOpaque(false);
		btnMiddle.setForeground(Color.BLACK);
		btnMiddle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMiddle.setMargin(new Insets(0, 0, 0, 0));
		btnMiddle.setBackground(Color.WHITE);
		btnMiddle.setBorder(null);
		GridBagConstraints gbc_btnMiddle = new GridBagConstraints();
		gbc_btnMiddle.insets = new Insets(0, 0, 5, 5);
		gbc_btnMiddle.fill = GridBagConstraints.BOTH;
		gbc_btnMiddle.gridx = 1;
		gbc_btnMiddle.gridy = 0;
		add(btnMiddle, gbc_btnMiddle);

		btnRight = new JButton("Configurações");
		btnRight.setContentAreaFilled(false);
		btnRight.setFocusPainted(false);
		btnRight.setVerticalTextPosition(SwingConstants.TOP);
		btnRight.setIcon(new ImageIcon(CBRMecaMenu.class
				.getResource("/imgassets/engine.png")));
		btnRight.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRight.setVerticalAlignment(SwingConstants.TOP);
		btnRight.setOpaque(false);
		btnRight.setForeground(Color.BLACK);
		btnRight.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRight.setMargin(new Insets(0, 0, 0, 0));
		btnRight.setBackground(Color.WHITE);
		btnRight.setBorder(null);
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 0);
		gbc_btnRight.fill = GridBagConstraints.BOTH;
		gbc_btnRight.gridx = 2;
		gbc_btnRight.gridy = 0;
		add(btnRight, gbc_btnRight);

		lblLeft = new JLabel("");
		lblLeft.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		GridBagConstraints gbc_lblLeft = new GridBagConstraints();
		gbc_lblLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLeft.insets = new Insets(0, 0, 0, 5);
		gbc_lblLeft.gridx = 0;
		gbc_lblLeft.gridy = 1;
		add(lblLeft, gbc_lblLeft);

		lblMiddle = new JLabel("");
		lblMiddle.setVisible(false);
		lblMiddle.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		GridBagConstraints gbc_lblMiddle = new GridBagConstraints();
		gbc_lblMiddle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMiddle.insets = new Insets(0, 0, 0, 5);
		gbc_lblMiddle.gridx = 1;
		gbc_lblMiddle.gridy = 1;
		add(lblMiddle, gbc_lblMiddle);

		lblRight = new JLabel("");
		lblRight.setVisible(false);
		lblRight.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		GridBagConstraints gbc_lblRight = new GridBagConstraints();
		gbc_lblRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRight.gridx = 2;
		gbc_lblRight.gridy = 1;
		add(lblRight, gbc_lblRight);
	}

	/**
	 * Sets the selected.
	 *
	 * @param position the new selected
	 */
	public void setSelected(int position) {
		switch (position) {
		case LEFT:
			lblLeft.setVisible(true);
			lblMiddle.setVisible(false);
			lblRight.setVisible(false);
			break;
		case MIDDLE:
			lblLeft.setVisible(false);
			lblMiddle.setVisible(true);
			lblRight.setVisible(false);
			break;
		case RIGHT:
			lblLeft.setVisible(false);
			lblMiddle.setVisible(false);
			lblRight.setVisible(true);
		}
	}

	/**
	 * Sets the listener.
	 *
	 * @param btnPosition the btn position
	 * @param listener the listener
	 */
	public void setListener(int btnPosition, ActionListener listener) {
		switch (btnPosition) {
		case LEFT:
			btnLeft.addActionListener(listener);
			btnLeft.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					lblLeft.setVisible(true);
					lblMiddle.setVisible(false);
					lblRight.setVisible(false);
				}
			});
			break;
		case MIDDLE:
			btnMiddle.addActionListener(listener);
			btnMiddle.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					lblLeft.setVisible(false);
					lblMiddle.setVisible(true);
					lblRight.setVisible(false);
				}
			});
			break;
		case RIGHT:
			btnRight.addActionListener(listener);
			btnRight.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					lblLeft.setVisible(false);
					lblMiddle.setVisible(false);
					lblRight.setVisible(true);
				}
			});
			break;
		}
	}
}
