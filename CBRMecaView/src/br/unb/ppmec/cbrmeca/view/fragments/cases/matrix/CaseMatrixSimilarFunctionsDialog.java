package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.com.ppmec.cbrmeca.control.CBREngine;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.view.fragments.cases.function.JPanelFunctionBoxClassification;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;

public class CaseMatrixSimilarFunctionsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2793870212125658926L;

	private static final String AUTO_NOT_FOUND = "<html>Não foram encontradas funções semelhantes no modo automático. Utilize a busca, caso deseje fazer o relacionamento manualmente.</html>";
	private static final String SEARCH_NOT_FOUND = "<html>Não foram encontradas funções com estes termos de busca.</html>";

	private FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
	private FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	private JPanel panel_casos;

	private JLabel lblNotFound;

	private List<FuncaoCaso> similarCases;

	private List<JPanelFunctionBoxClassification> list;

	protected boolean cancelled = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FuncaoMatrizVO fVO = new FuncaoMatrizVO("Alimentar dispositivos",
					true, 4, 2, 1, 1, 4, null);
			CaseMatrixSimilarFunctionsDialog dialog = new CaseMatrixSimilarFunctionsDialog(
					fVO);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModal(true);
			dialog.setVisible(true);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CaseMatrixSimilarFunctionsDialog(FuncaoMatrizVO vo) {

		getContentPane().setBackground(Color.WHITE);
		setModal(true);
		setUndecorated(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setOpaque(false);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel = new JLabel("Funções semelhantes");
					lblNewLabel.setBorder(null);
					lblNewLabel.setSize(new Dimension(500, 50));
					lblNewLabel.setMinimumSize(new Dimension(500, 50));
					lblNewLabel.setMaximumSize(new Dimension(500, 50));
					lblNewLabel.setIconTextGap(10);
					panel_1.add(lblNewLabel);
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
					lblNewLabel.setIcon(new ImageIcon(
							CaseMatrixSimilarFunctionsDialog.class
									.getResource("/imgassets/suggest_pq.png")));
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel.setPreferredSize(new Dimension(500, 70));
				}
				{
					JLabel lblNewLabel_1 = new JLabel("");
					lblNewLabel_1.setPreferredSize(new Dimension(3000, 15));
					lblNewLabel_1.setIcon(new ImageIcon(
							CaseMatrixSimilarFunctionsDialog.class
									.getResource("/imgassets/ds_low.png")));
					panel_1.add(lblNewLabel_1, BorderLayout.SOUTH);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				panel.add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setPreferredSize(new Dimension(500, 50));
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[] { 47, 65, 0 };
				gbl_buttonPane.rowHeights = new int[] { 23, 0 };
				gbl_buttonPane.columnWeights = new double[] { 1.0, 1.0,
						Double.MIN_VALUE };
				gbl_buttonPane.rowWeights = new double[] { 1.0,
						Double.MIN_VALUE };
				buttonPane.setLayout(gbl_buttonPane);
				{
					JButton okButton = new JButton("Selecionar");
					okButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							if (similarCases == null) {
								similarCases = new ArrayList<FuncaoCaso>();
							} else {
								similarCases.clear();
							}

							for (JPanelFunctionBoxClassification box : list) {
								System.out.println(box.getFuncaoCaso());
								if (box.isSelected()) {
									System.out.println("Selected");
									similarCases.add(box.getFuncaoCaso());
								}
							}
							dispose();
						}
					});
					okButton.setBorder(new LineBorder(new Color(0, 0, 0)));
					okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
					okButton.setHorizontalTextPosition(SwingConstants.CENTER);
					okButton.setIcon(new ImageIcon(
							CaseMatrixSimilarFunctionsDialog.class
									.getResource("/imgassets/ds.png")));
					okButton.setActionCommand("OK");
					GridBagConstraints gbc_okButton = new GridBagConstraints();
					gbc_okButton.fill = GridBagConstraints.BOTH;
					gbc_okButton.gridx = 0;
					gbc_okButton.gridy = 0;
					buttonPane.add(okButton, gbc_okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancelar");
					cancelButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							similarCases.clear();
							cancelled = true;
							dispose();
						}
					});
					cancelButton.setBorder(new LineBorder(new Color(0, 0, 0)));
					cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
					cancelButton
							.setHorizontalTextPosition(SwingConstants.CENTER);
					cancelButton.setIcon(new ImageIcon(
							CaseMatrixSimilarFunctionsDialog.class
									.getResource("/imgassets/ds.png")));
					cancelButton.setActionCommand("Cancel");
					GridBagConstraints gbc_cancelButton = new GridBagConstraints();
					gbc_cancelButton.fill = GridBagConstraints.BOTH;
					gbc_cancelButton.gridx = 1;
					gbc_cancelButton.gridy = 0;
					buttonPane.add(cancelButton, gbc_cancelButton);
				}
			}
			panel.add(contentPanel, BorderLayout.CENTER);
			contentPanel.setPreferredSize(new Dimension(500, 50));
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setOpaque(false);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(null);
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 0, 800, 52);
			panel_1.setOpaque(false);
			panel_1.setBackground(Color.WHITE);
			panel_1.setBorder(null);
			contentPanel.add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[] { 0, 83, 250, 0, 0, 0 };
			gbl_panel_1.rowHeights = new int[] { 50, 35, 0 };
			gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			panel_1.setLayout(gbl_panel_1);
			{
				JLabel lblNewLabel_2 = new JLabel("Busca por nome: ");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 10);
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 0;
				panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				textField = new JTextField();
				textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (textField.getText().length() > 1) {
							fillPanel();
						} else {
							clearPanel();
							lblNotFound.setText(SEARCH_NOT_FOUND);
							panel_casos.add(lblNotFound);
						}
					}
				});
				textField.setSize(new Dimension(150, 20));
				textField.setMinimumSize(new Dimension(150, 20));
				textField.setPreferredSize(new Dimension(250, 20));
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 0;
				panel_1.add(textField, gbc_textField);
				textField.setColumns(10);
			}
			JLabel lblNewLabel_4 = new JLabel("Buscar");
			lblNewLabel_4.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					fillPanel();
				}
			});
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_4.setIcon(new ImageIcon(
					CaseMatrixSimilarFunctionsDialog.class
							.getResource("/imgassets/icon_search.png")));
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 10, 0, 0);
			gbc_lblNewLabel_4.gridx = 3;
			gbc_lblNewLabel_4.gridy = 0;
			panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
			{
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3.setOpaque(true);
				lblNewLabel_3.setBackground(Color.BLACK);
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_3.gridwidth = 5;
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 1;
				panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(5, 57, 790, 403);
				scrollPane.setBorder(null);
				scrollPane.setBackground(Color.WHITE);
				contentPanel.add(scrollPane);
				{
					panel_casos = new JPanel();
					panel_casos.setPreferredSize(new Dimension(750, 100));
					panel_casos.setMinimumSize(new Dimension(750, 100));
					panel_casos.setMaximumSize(new Dimension(750, 100));
					FlowLayout flowLayout = (FlowLayout) panel_casos
							.getLayout();
					flowLayout.setVgap(0);
					flowLayout.setHgap(0);
					panel_casos.setBorder(null);
					panel_casos.setBackground(Color.WHITE);
					scrollPane.add(panel_casos);
					{
						lblNotFound = new JLabel(AUTO_NOT_FOUND);
						lblNotFound.setPreferredSize(new Dimension(640, 80));
						lblNotFound
								.setHorizontalTextPosition(SwingConstants.CENTER);
						lblNotFound
								.setHorizontalAlignment(SwingConstants.CENTER);
						panel_casos.add(lblNotFound);
					}
				}
				scrollPane.setViewportView(panel_casos);
			}
		}

		loadlist(vo);
	}

	private void loadlist(FuncaoMatrizVO vo) {
		clearPanel();
		
		FuncaoCaso fCasoDummy = new FuncaoCaso();
		fCasoDummy.setBolElementar(vo.isElementar());
		fCasoDummy.setTipoEfeito(vo.getTipoEfeito());
		fCasoDummy.setTipoEscopo(vo.getEscopo());
		fCasoDummy.setTipoNecessidade(vo.getTipoNecessidade());
		fCasoDummy.setTipoFuncao(vo.getTipoFuncao());

		similarCases = CBREngine.getInstance().retrieveSimilar(fCasoDummy,
				vo.getIdTipoCaso(), vo.getFuncao());

		list = new ArrayList<JPanelFunctionBoxClassification>();

		fillPanel(similarCases);
		
		if (similarCases.size() == 0) {
			clearPanel();
			panel_casos.add(lblNotFound);
		}
	}

	protected void clearPanel() {
		panel_casos.removeAll();
		panel_casos.revalidate();
		panel_casos.repaint();

		if(similarCases != null){
			similarCases.clear();
		}
	}

	protected void fillPanel() {
		panel_casos.removeAll();
		panel_casos.revalidate();
		panel_casos.repaint();
		
		similarCases.clear();

		List<Funcao> funcoes = fDAO.getListByStrDescriptor(textField.getText());
		List<FuncaoCaso> similarCases = new ArrayList<FuncaoCaso>();

		for (Funcao f : funcoes) {
			List<FuncaoCaso> fCasos = fcDAO
					.getByIdFuncao(f.getIdFuncao(), true);
			similarCases.addAll(fCasos);
		}

		fillPanel(similarCases);

		if (similarCases.size() == 0) {
			clearPanel();
			lblNotFound.setText(SEARCH_NOT_FOUND);
			panel_casos.add(lblNotFound);
		}
	}

	private void fillPanel(List<FuncaoCaso> similarCases) {

		for (FuncaoCaso funcaoCaso : similarCases) {
			JPanelFunctionBoxClassification fBox = new JPanelFunctionBoxClassification(
					funcaoCaso);
			fBox.setBoxEnabled(false);
			fBox.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (fBox.isSelected()) {
						fBox.setSelected(false);
						if (similarCases.contains(fBox.getFuncaoCaso())) {
							System.out.println("Removed.");
							similarCases.remove(fBox.getFuncaoCaso());
						}
					} else {
						fBox.setSelected(true);
						if (!similarCases.contains(fBox.getFuncaoCaso())) {
							System.out.println("Added.");
							similarCases.add(fBox.getFuncaoCaso());
						}
					}
				}
			});
			fBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			panel_casos.add(fBox);
			panel_casos.revalidate();
			panel_casos.repaint();
			panel_casos.setPreferredSize(new Dimension(750, ((similarCases
					.size() / 5) + 1)
					* JPanelFunctionBoxClassification.BLOCK_HEIGHT));
			list.add(fBox);
		}
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public List<FuncaoCaso> getSimilarCases() {
		return similarCases;
	}
}
