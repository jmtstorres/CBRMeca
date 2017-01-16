/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.dashboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.unb.ppmec.cbrmeca.db.model.dao.CasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.PalavraDAOImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class CBRDashBoardCasesPanel.
 */
public class CBRDashBoardCasesPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7106078291303068980L;

	/**
	 * Create the panel.
	 */
	public CBRDashBoardCasesPanel() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Estatísticas");
		lblNewLabel.setIcon(new ImageIcon(CBRDashBoardCasesPanel.class.getResource("/imgassets/ds.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setPreferredSize(new Dimension(200, 60));
		lblNewLabel.setMinimumSize(new Dimension(200, 40));
		lblNewLabel.setMaximumSize(new Dimension(100, 40));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 50, 50, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Casos");
		lblNewLabel_1.setSize(new Dimension(100, 40));
		lblNewLabel_1.setPreferredSize(new Dimension(100, 40));
		lblNewLabel_1.setMinimumSize(new Dimension(100, 40));
		lblNewLabel_1.setMaximumSize(new Dimension(100, 40));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel label_cases_number = new JLabel("0");
		label_cases_number.setMinimumSize(new Dimension(100, 40));
		label_cases_number.setMaximumSize(new Dimension(100, 40));
		label_cases_number.setSize(new Dimension(100, 40));
		label_cases_number.setPreferredSize(new Dimension(100, 40));
		label_cases_number.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_cases_number.setHorizontalTextPosition(SwingConstants.CENTER);
		label_cases_number.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_cases_number = new GridBagConstraints();
		gbc_label_cases_number.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_cases_number.insets = new Insets(0, 0, 5, 5);
		gbc_label_cases_number.gridx = 2;
		gbc_label_cases_number.gridy = 0;
		panel_1.add(label_cases_number, gbc_label_cases_number);
		
		JLabel lblFunes = new JLabel("Funções");
		lblFunes.setMinimumSize(new Dimension(100, 40));
		lblFunes.setMaximumSize(new Dimension(100, 40));
		lblFunes.setSize(new Dimension(100, 40));
		lblFunes.setPreferredSize(new Dimension(100, 40));
		lblFunes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFunes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFunes.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFunes = new GridBagConstraints();
		gbc_lblFunes.fill = GridBagConstraints.BOTH;
		gbc_lblFunes.insets = new Insets(0, 0, 5, 5);
		gbc_lblFunes.gridx = 1;
		gbc_lblFunes.gridy = 1;
		panel_1.add(lblFunes, gbc_lblFunes);
		
		JLabel label_function_number = new JLabel("0");
		label_function_number.setMinimumSize(new Dimension(100, 40));
		label_function_number.setMaximumSize(new Dimension(100, 40));
		label_function_number.setSize(new Dimension(100, 40));
		label_function_number.setPreferredSize(new Dimension(100, 40));
		label_function_number.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_function_number.setHorizontalTextPosition(SwingConstants.CENTER);
		label_function_number.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_function_number = new GridBagConstraints();
		gbc_label_function_number.insets = new Insets(0, 0, 5, 5);
		gbc_label_function_number.gridx = 2;
		gbc_label_function_number.gridy = 1;
		panel_1.add(label_function_number, gbc_label_function_number);
		
		JLabel lblPalavras = new JLabel("Palavras");
		lblPalavras.setMinimumSize(new Dimension(100, 40));
		lblPalavras.setMaximumSize(new Dimension(100, 40));
		lblPalavras.setSize(new Dimension(100, 40));
		lblPalavras.setPreferredSize(new Dimension(100, 40));
		lblPalavras.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPalavras.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPalavras = new GridBagConstraints();
		gbc_lblPalavras.fill = GridBagConstraints.BOTH;
		gbc_lblPalavras.insets = new Insets(0, 0, 5, 5);
		gbc_lblPalavras.gridx = 1;
		gbc_lblPalavras.gridy = 2;
		panel_1.add(lblPalavras, gbc_lblPalavras);
		
		JLabel label_word_number = new JLabel("0");
		label_word_number.setMinimumSize(new Dimension(100, 40));
		label_word_number.setMaximumSize(new Dimension(100, 40));
		label_word_number.setSize(new Dimension(100, 40));
		label_word_number.setPreferredSize(new Dimension(100, 40));
		label_word_number.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_word_number.setHorizontalTextPosition(SwingConstants.CENTER);
		label_word_number.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_word_number = new GridBagConstraints();
		gbc_label_word_number.insets = new Insets(0, 0, 5, 5);
		gbc_label_word_number.gridx = 2;
		gbc_label_word_number.gridy = 2;
		panel_1.add(label_word_number, gbc_label_word_number);
		
		JLabel lblConceitos = new JLabel("Conceitos");
		lblConceitos.setMinimumSize(new Dimension(100, 40));
		lblConceitos.setMaximumSize(new Dimension(100, 40));
		lblConceitos.setSize(new Dimension(100, 40));
		lblConceitos.setPreferredSize(new Dimension(100, 40));
		lblConceitos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConceitos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConceitos.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblConceitos = new GridBagConstraints();
		gbc_lblConceitos.insets = new Insets(0, 0, 0, 5);
		gbc_lblConceitos.fill = GridBagConstraints.BOTH;
		gbc_lblConceitos.gridx = 1;
		gbc_lblConceitos.gridy = 3;
		panel_1.add(lblConceitos, gbc_lblConceitos);
		
		JLabel label_concept_number = new JLabel("0");
		label_concept_number.setMinimumSize(new Dimension(100, 40));
		label_concept_number.setMaximumSize(new Dimension(100, 40));
		label_concept_number.setSize(new Dimension(100, 40));
		label_concept_number.setPreferredSize(new Dimension(100, 40));
		label_concept_number.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_concept_number.setHorizontalTextPosition(SwingConstants.CENTER);
		label_concept_number.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_concept_number = new GridBagConstraints();
		gbc_label_concept_number.insets = new Insets(0, 0, 0, 5);
		gbc_label_concept_number.gridx = 2;
		gbc_label_concept_number.gridy = 3;
		panel_1.add(label_concept_number, gbc_label_concept_number);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		add(panel_2, BorderLayout.SOUTH);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				PalavraDAOImpl palavraDAO = new PalavraDAOImpl();
				label_word_number.setText(Integer.toString(palavraDAO.count()));
				
				CasoDAOImpl casoDAO = new CasoDAOImpl();
				label_cases_number.setText(Integer.toString(casoDAO.count()));
				
				FuncaoDAOImpl funcDAO = new FuncaoDAOImpl();
				label_function_number.setText(Integer.toString(funcDAO.count()));
				
				ConceitoDAOImpl conceitoDAO = new ConceitoDAOImpl();
				label_concept_number.setText(Integer.toString(conceitoDAO.count()));
			}
		}, 100);
	}

}
