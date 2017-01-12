package br.unb.ppmec.cbrmeca.view.fragments.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CBRMecaDashboardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8562037625706065684L;
	private JPanel panelContent;
	
	private static final int ID_PANEL_STATS = 0;
	private static final int ID_PANEL_SEARCH = 1;

	/**
	 * Create the panel.
	 */
	public CBRMecaDashboardPanel() {
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {150};
		gbl_panel.rowHeights = new int[] {50, 50, 50, 50, 50, 50};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("Estat\u00EDsticas");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				changeContent(ID_PANEL_STATS);
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setBorder(null);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(CBRMecaDashboardPanel.class.getResource("/imgassets/stats.png")));
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setIconTextGap(0);
		btnNewButton.setSize(new Dimension(100, 100));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setPreferredSize(new Dimension(100, 100));
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setMinimumSize(new Dimension(100, 100));
		btnNewButton.setMaximumSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnSolues = new JButton("Buscar Casos");
		btnSolues.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeContent(ID_PANEL_SEARCH);
			}
		});
		btnSolues.setFocusPainted(false);
		btnSolues.setContentAreaFilled(false);
		btnSolues.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSolues.setBorder(null);
		btnSolues.setBorderPainted(false);
		btnSolues.setIcon(new ImageIcon(CBRMecaDashboardPanel.class.getResource("/imgassets/search.png")));
		btnSolues.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSolues.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSolues.setPreferredSize(new Dimension(100, 100));
		btnSolues.setIconTextGap(0);
		btnSolues.setMargin(new Insets(0, 0, 0, 0));
		btnSolues.setSize(new Dimension(100, 100));
		btnSolues.setMinimumSize(new Dimension(100, 100));
		btnSolues.setMaximumSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnSolues = new GridBagConstraints();
		gbc_btnSolues.fill = GridBagConstraints.BOTH;
		gbc_btnSolues.gridx = 0;
		gbc_btnSolues.gridy = 1;
		panel.add(btnSolues, gbc_btnSolues);
		
		panelContent = new JPanel();
		panelContent.setBorder(null);
		panelContent.setBackground(Color.WHITE);
		add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BorderLayout(0, 0));
		changeContent(ID_PANEL_STATS);
	}
	
	private void changeContent(int panelID){
		JPanel newPanel = null;
		
		switch(panelID){
		case ID_PANEL_STATS:
			newPanel = new CBRDashBoardCasesPanel();
			break;
		case ID_PANEL_SEARCH:
			newPanel = new CBRDashSearchCasesPanel();
			break;
		}
		
		if(newPanel != null){
			panelContent.removeAll();
			panelContent.revalidate();
			panelContent.repaint();
			panelContent.add(newPanel, BorderLayout.CENTER);
		}
	}
}
