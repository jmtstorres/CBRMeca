package br.unb.ppmec.cbrmeca.view.fragments.cases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import br.unb.ppmec.cbrmeca.db.constants.EProjectType;
import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.Solucao;
import br.unb.ppmec.cbrmeca.db.model.dao.CasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.SolucaoDAOImpl;
import br.unb.ppmec.cbrmeca.view.components.dialog.MessageDialog;
import br.unb.ppmec.cbrmeca.view.fragments.cases.dialog.ConfirmationDialog;
import br.unb.ppmec.cbrmeca.view.fragments.cases.function.CasesFunctionPanel;
import br.unb.ppmec.cbrmeca.view.fragments.cases.function.JPanelFunctionClassification;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.CaseMatrixElement;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.CasesMatrixPanel;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.CasesMatrixRow;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;
import br.unb.ppmec.cbrmeca.view.notification.Notification;
import br.unb.ppmec.cbrmeca.view.notification.NotificationManager;
import br.unb.ppmec.cbrmeca.view.splash.SplashScreen;
import br.unb.ppmec.cbrmeca.view.util.io.CaseIO;
import br.unb.ppmec.cbrmeca.view.util.io.FileIO;
import br.unb.ppmec.cbrmeca.view.util.io.PanelImageExport;

public class CBRMecaCasesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8562037625706065684L;
	private JTextField txtf_title;
	private JTextPane txtp_desc;
	private CasesFunctionPanel functionPanel;

	private CasesMatrixPanel panel_matrix;
	private JFormattedTextField textFieldAno;
	private JComboBox<EProjectType> comboBox;
	private JDialog dialog_wait;

	/**
	 * Create the panel.
	 */
	public CBRMecaCasesPanel() {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		NotificationManager.getInstance().setupNotification();

		JPanel panel_case_description = new JPanel();
		panel_case_description.setOpaque(false);
		add(panel_case_description, BorderLayout.NORTH);
		GridBagLayout gbl_panel_case_description = new GridBagLayout();
		gbl_panel_case_description.columnWidths = new int[] { 50, 50, 250, 50, 80,
				50, 50, 50, 0 };
		gbl_panel_case_description.rowHeights = new int[] { 50, 30, 10, 10, 0 };
		gbl_panel_case_description.columnWeights = new double[] { 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_case_description.rowWeights = new double[] { 1.0, 0.0, 1.0,
				1.0, Double.MIN_VALUE };
		panel_case_description.setLayout(gbl_panel_case_description);

		JLabel lblNewLabel = new JLabel("Novo Caso");
		lblNewLabel.setMinimumSize(new Dimension(150, 50));
		lblNewLabel.setMaximumSize(new Dimension(150, 50));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(CBRMecaCasesPanel.class
				.getResource("/imgassets/ds.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setPreferredSize(new Dimension(150, 50));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridwidth = 8;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_case_description.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblTtulo = new JLabel("Título: ");
		lblTtulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTtulo.setMinimumSize(new Dimension(150, 25));
		lblTtulo.setMaximumSize(new Dimension(150, 25));
		lblTtulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTtulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTtulo.setPreferredSize(new Dimension(150, 25));
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 10);
		gbc_lblTtulo.fill = GridBagConstraints.VERTICAL;
		gbc_lblTtulo.gridx = 1;
		gbc_lblTtulo.gridy = 1;
		panel_case_description.add(lblTtulo, gbc_lblTtulo);

		txtf_title = new JTextField();
		txtf_title.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtf_title.setHorizontalAlignment(SwingConstants.CENTER);
		txtf_title.setPreferredSize(new Dimension(200, 30));
		txtf_title.setSize(new Dimension(200, 30));
		txtf_title.setBorder(new LineBorder(Color.BLACK));
		txtf_title.setMargin(new Insets(10, 10, 10, 10));
		GridBagConstraints gbc_txtf_title = new GridBagConstraints();
		gbc_txtf_title.insets = new Insets(0, 0, 5, 5);
		gbc_txtf_title.fill = GridBagConstraints.BOTH;
		gbc_txtf_title.gridx = 2;
		gbc_txtf_title.gridy = 1;
		panel_case_description.add(txtf_title, gbc_txtf_title);
		txtf_title.setColumns(10);

		comboBox = new JComboBox<EProjectType>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setOpaque(false);
		comboBox.setBorder(new LineBorder(Color.BLACK, 2));
		comboBox.setBackground(Color.WHITE);
		comboBox.getEditor().getEditorComponent().setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int idTipoCaso = ((EProjectType) e.getItem()).getId();
					functionPanel.setIdTipoCaso(idTipoCaso);
				}
			}
		});

		JLabel lblAno = new JLabel("Ano: ");
		lblAno.setPreferredSize(new Dimension(50, 25));
		lblAno.setMinimumSize(new Dimension(50, 25));
		lblAno.setMaximumSize(new Dimension(50, 25));
		lblAno.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblAno = new GridBagConstraints();
		gbc_lblAno.insets = new Insets(0, 0, 5, 5);
		gbc_lblAno.anchor = GridBagConstraints.EAST;
		gbc_lblAno.gridx = 3;
		gbc_lblAno.gridy = 1;
		panel_case_description.add(lblAno, gbc_lblAno);

		textFieldAno = new JFormattedTextField(createFormatter("####"));
		textFieldAno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldAno.setSize(new Dimension(200, 30));
		textFieldAno.setPreferredSize(new Dimension(200, 30));
		textFieldAno.setMargin(new Insets(10, 10, 10, 10));
		textFieldAno.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAno.setColumns(10);
		textFieldAno.setBorder(new LineBorder(Color.BLACK));
		GridBagConstraints gbc_textFieldAno = new GridBagConstraints();
		gbc_textFieldAno.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAno.fill = GridBagConstraints.BOTH;
		gbc_textFieldAno.gridx = 4;
		gbc_textFieldAno.gridy = 1;
		panel_case_description.add(textFieldAno, gbc_textFieldAno);

		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setPreferredSize(new Dimension(50, 25));
		lblTipo.setMinimumSize(new Dimension(50, 25));
		lblTipo.setMaximumSize(new Dimension(50, 25));
		lblTipo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.gridx = 5;
		gbc_lblTipo.gridy = 1;
		panel_case_description.add(lblTipo, gbc_lblTipo);
		comboBox.setModel(new DefaultComboBoxModel<EProjectType>(EProjectType
				.values()));

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 1;
		panel_case_description.add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Ajuda");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MessageDialog dialog = new MessageDialog();
				dialog.setText("<html>Os campos aqui adicionados serão utilizados pelo "
									+ "algoritmo de sugestão para tentar aproximar algumas "
									+ "possíveis soluções para o projeto elaborado."
									+ "As funções possuem 5 parâmetros principais:<p>"
									+ "1 - o nome da função<p>"
									+ "2 - o escopo (É aplicável aos domínios: Mecânico, Controle, Informação ou Potência)<p>"
									+ "3 - a necessidade (consegue suprir uma necessidade do usuário?)<p>"
									+ "4 - o efeito (ela corrige ou previne algum efeito indesejado?)<p>"
									+ "5 - o tipo da função, relacionado ao seu papel (técnico ou interativo)<p>"
									+ "Por fim, a caixa de seleção possui a finalidade de identificar as funções elementares "
									+ "que serão analisadas na etapa da matriz morfológica.<p>"
									+ "Na etapa da matriz, o usuário poderá selecionar entre os conceitos já cadastrados ou "
									+ "deixar que o algoritmo forneça algumas sugestões. Os itens então apresentados poderão ainda ser"
									+ " ajustados para as necessidade do projeto.<p>"
									+ "Ao salvar o projeto, serão gerados dois arquivos \"png\" com as imagens da árvore de funções "
									+ "e da matriz para uso posterior. Os casos cadastrados também poderão ser visualizados na seção de informações. </html>");
				
				dialog.setVisible(true);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(CBRMecaCasesPanel.class.getResource("/imgassets/help.png")));
		lblNewLabel_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.gridheight = 2;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_case_description.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblDescrio = new JLabel("Descrição: ");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescrio.setMinimumSize(new Dimension(150, 50));
		lblDescrio.setMaximumSize(new Dimension(150, 50));
		lblDescrio.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 10);
		gbc_lblDescrio.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescrio.gridx = 1;
		gbc_lblDescrio.gridy = 2;
		panel_case_description.add(lblDescrio, gbc_lblDescrio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(200, 40));
		scrollPane.setPreferredSize(new Dimension(200, 40));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		panel_case_description.add(scrollPane, gbc_scrollPane);

		txtp_desc = new JTextPane();
		txtp_desc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtp_desc.setSize(new Dimension(200, 40));
		txtp_desc.setPreferredSize(new Dimension(200, 40));
		txtp_desc.setMinimumSize(new Dimension(200, 40));
		txtp_desc.setMaximumSize(new Dimension(200, 40));
		txtp_desc.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(txtp_desc);
		
		JLabel lblEditarCasoDa = new JLabel("<html>Editar caso existente</html>");
		lblEditarCasoDa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CBRSearchCasesDialog dialog = new CBRSearchCasesDialog();
				dialog.setModal(true);
				dialog.setVisible(true);
				
				Caso caso = dialog.getCaso();
				if(caso != null){
					loadCaseTree(caso);
					FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
					List<FuncaoMatrizVO> funcoes = getElementarLevelFunctions(caso, fcDAO.get(caso.getIdFuncaoGeral()));
					System.out.println("FUncoes: " + funcoes.size());
					panel_matrix.clearAll();
					for(FuncaoMatrizVO fVO : funcoes){
						panel_matrix.addFunction(fVO);
					}
					panel_matrix.showButtons(true);
					System.out.println(caso.getStrTitulo());
					System.out.println(caso.getStrDescricao());
				}
			}
		});
		lblEditarCasoDa.setIcon(new ImageIcon(CBRMecaCasesPanel.class.getResource("/imgassets/reedit.png")));
		lblEditarCasoDa.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblEditarCasoDa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditarCasoDa.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEditarCasoDa = new GridBagConstraints();
		gbc_lblEditarCasoDa.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEditarCasoDa.insets = new Insets(0, 0, 5, 0);
		gbc_lblEditarCasoDa.gridheight = 2;
		gbc_lblEditarCasoDa.gridx = 7;
		gbc_lblEditarCasoDa.gridy = 1;
		panel_case_description.add(lblEditarCasoDa, gbc_lblEditarCasoDa);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(Color.WHITE);
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				switch (index) {
				case 0:

					break;
				case 1:
					if(!checkFunctionTreeOk(functionPanel)){
						NotificationManager.getInstance().notifyUser("Verificar itens", "Preencher todos os dados de funções para continuar.", Notification.FAIL);
						sourceTabbedPane.setSelectedIndex(0);
						return;
					}
					
					List<FuncaoMatrizVO> funcoesLowLevel = functionPanel.getElementarLevelFunctions();
					if(funcoesLowLevel == null || funcoesLowLevel.size() == 0){
						NotificationManager.getInstance().notifyUser("Verificar itens", "Marcar as funções elementares para a matriz.", Notification.FAIL);
						sourceTabbedPane.setSelectedIndex(0);
						return;
					}
					panel_matrix.setFunctions(funcoesLowLevel);
					break;
				}

			}
		};
		tabbedPane.addChangeListener(changeListener);
		add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scroll_function = new JScrollPane();
		scroll_function.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		tabbedPane.addTab("Árvore de funções", null, scroll_function, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] {};
		gbl_panel.rowWeights = new double[] {};
		panel.setLayout(gbl_panel);
		functionPanel = new CasesFunctionPanel(null, 0);
		functionPanel.setOpaque(false);
		GridBagConstraints gbc_functionPanel = new GridBagConstraints();
		gbc_functionPanel.gridx = 0;
		gbc_functionPanel.gridy = 0;
		panel.add(functionPanel, gbc_functionPanel);
		scroll_function.add(panel);
		scroll_function.setViewportView(panel);
		scroll_function.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		JScrollPane panel_1 = new JScrollPane();
		tabbedPane.addTab("Matriz Morfológica", null, panel_1, null);
		panel_matrix = new CasesMatrixPanel();
		panel_matrix.setBackground(Color.WHITE);
		panel_1.add(panel_matrix);
		panel_1.setViewportView(panel_matrix);

		JPanel panel_bottom = new JPanel();
		panel_bottom.setBackground(Color.LIGHT_GRAY);
		add(panel_bottom, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_bottom = new GridBagLayout();
		gbl_panel_bottom.columnWidths = new int[] { 200, 200, 0 };
		gbl_panel_bottom.rowHeights = new int[] { 60, 0 };
		gbl_panel_bottom.columnWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_bottom.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_bottom.setLayout(gbl_panel_bottom);

		JLabel lblLimparDados = new JLabel("Limpar Dados");
		lblLimparDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				clearData();
			}
		});
		lblLimparDados.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLimparDados.setBorder(null);
		lblLimparDados.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLimparDados.setIcon(new ImageIcon(CBRMecaCasesPanel.class
				.getResource("/imgassets/ds.png")));
		lblLimparDados.setIconTextGap(0);
		lblLimparDados.setPreferredSize(new Dimension(200, 60));
		lblLimparDados.setMinimumSize(new Dimension(200, 60));
		lblLimparDados.setMaximumSize(new Dimension(200, 0));
		lblLimparDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimparDados.setForeground(Color.BLACK);
		lblLimparDados.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLimparDados.setBackground(Color.RED);
		GridBagConstraints gbc_lblLimparDados = new GridBagConstraints();
		gbc_lblLimparDados.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLimparDados.anchor = GridBagConstraints.NORTH;
		gbc_lblLimparDados.gridx = 0;
		gbc_lblLimparDados.gridy = 0;
		panel_bottom.add(lblLimparDados, gbc_lblLimparDados);

		JLabel lblNewLabel_1 = new JLabel("Salvar");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				List<String> list = paramsOk();
				if (list.size() > 0) {
					String missingData = "";
					for (String data : list) {
						missingData = missingData.concat("\n" + data);
					}
					NotificationManager.getInstance().notifyUser("Parâmetros em falta:", "Reveja os dados:"
							+ missingData, Notification.FAIL);
					dialog_wait.dispose();
					return;
				}
				
				ConfirmationDialog dialog = new ConfirmationDialog();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				
				if(dialog.isConfirmed()){
					createDialog();
					System.out.println("SIM");
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try{
								CasoDAOImpl casoDao = new CasoDAOImpl();
								Caso exists = casoDao.getExactTitle(txtf_title.getText());
								System.out.println("Teste");

								if(exists != null){
									NotificationManager.getInstance().notifyUser("Já existe", "Já existe caso com este título.", Notification.FAIL);
								}else{
									int idFgeral = saveFunctionTree(functionPanel, null);
									System.out.println("Id caso: " + idFgeral);
					
									Caso caso = new Caso();
									caso.setStrTitulo(txtf_title.getText());
									caso.setStrDescricao(txtp_desc.getText());
									caso.setIdFuncaoGeral(idFgeral);
									caso.setIdTipo(((EProjectType) comboBox.getSelectedItem())
											.getId());
									caso.setIntAno(Integer.valueOf(textFieldAno.getText()));
									
									casoDao.save(caso);
					
									List<FuncaoMatrizVO> funcoesLowLevel = functionPanel
											.getElementarLevelFunctions();
									for (FuncaoMatrizVO funcao : funcoesLowLevel) {
										saveSolution(funcao, caso);
									}
									
									dialog_wait.dispose();
									
									ConfirmationDialog dialog = new ConfirmationDialog();
									dialog.setMessage("Salvar o caso em arquivo?");
									dialog.setVisible(true);
									dialog.setLocationRelativeTo(null);
									
									if(dialog.isConfirmed()){
										BufferedImage imTree = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
										panel.paint(imTree.getGraphics());
										
										panel_matrix.showButtons(false);
										BufferedImage imMatrix = new BufferedImage(panel_matrix.getWidth(), panel_matrix.getHeight(), BufferedImage.TYPE_INT_ARGB);
										panel_matrix.paint(imMatrix.getGraphics());
										panel_matrix.showButtons(true);
										
										File f = CaseIO.chooseSaveFile();
										if(f.getName().isEmpty()){
											NotificationManager.getInstance().notifyUser("Nome do arquivo", "Informe o nome do arquivo.", Notification.FAIL);
										}
										
										String file = f.getAbsolutePath();
										if(!file.contains(".cbm")){
											file = file + ".cbm";
										}
										
										FileIO.saveCaseFile(file, caso, imTree, imMatrix);
									}
					
									NotificationManager.getInstance().notifyUser("Salvo com sucesso:",
											"Caso número: " + caso.getIdCaso(),
											Notification.SUCCESS);
									
									clearData();
								}
							}catch (Exception ex){
								ex.printStackTrace();
								NotificationManager.getInstance().notifyUser("Erro", "Erro salvando caso na base.", Notification.FAIL);
							}
							
							dialog_wait.dispose();
						}
					});
					
					t.start();
				}
			}
		});
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(CBRMecaCasesPanel.class
				.getResource("/imgassets/ds.png")));
		lblNewLabel_1.setBackground(Color.GREEN);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setPreferredSize(new Dimension(200, 60));
		lblNewLabel_1.setMinimumSize(new Dimension(200, 60));
		lblNewLabel_1.setMaximumSize(new Dimension(200, 0));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_bottom.add(lblNewLabel_1, gbc_lblNewLabel_1);
	}
	
	private void loadCaseTree(Caso caso){
		clearData();
		functionPanel.loadFromCase(caso);
		this.txtf_title.setText(renameCase(caso.getStrTitulo()));
		this.txtp_desc.setText(caso.getStrDescricao());
		this.textFieldAno.setText(caso.getIntAno().toString());
		this.comboBox.setSelectedIndex(caso.getIdTipo());
	}
	
	private String renameCase(String title){
		String numberedTitle = null; 
		int number = getVariationNumber(title);
		
		if(number > 0){
			numberedTitle = title.replaceAll("\\(" + number + "\\)"
											 ,"(" + (number+1) + ")");
		}else if(!(title.contains("(") && title.contains(")"))){
			numberedTitle = title + "(" + (number+1) + ")";
		}
		
		return numberedTitle;
	}
	
	private int getVariationNumber(String title){
		int number = 1;
		
		if(!(title.contains("(") && title.contains(")"))){
			return 0;
		}
		
		while(!title.contains("(" + number + "") &&
			  (title.contains("(") && title.contains(")"))){
			System.out.println(number);
			number++;
		}
		
		return number;
	}

	protected void clearData() {
		txtf_title.setText("");
		txtp_desc.setText("");
		textFieldAno.setText("");
		comboBox.setSelectedIndex(0);
		functionPanel.clearAll();
		panel_matrix.clearAll();
	}

	public void createDialog() {
		dialog_wait = new JDialog();
		ImageIcon icon = new ImageIcon(SplashScreen.class.getResource("/imgassets/wait.gif"));
		JLabel l = new JLabel(icon);
		dialog_wait.setSize(getParent().getParent().getSize());
		dialog_wait.getContentPane().add(l);
		dialog_wait.setLocationRelativeTo(getParent().getParent());
		dialog_wait.setAlwaysOnTop(true);
		dialog_wait.setUndecorated(true);
		dialog_wait.setBackground(new Color(125, 125, 125, 125));
		
		dialog_wait.setVisible(true);
	}

	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}

	private int saveFunctionTree(CasesFunctionPanel panelStart,
			FuncaoCaso funcaoCaso) {
		List<CasesFunctionPanel> list = panelStart.getChildren();
		int id = 0;

		System.out.println("elementar: " + panelStart.isElementar());

		if (funcaoCaso == null) {
			funcaoCaso = saveFunction(
					panelStart.getFunctionString(), 
					null,
					panelStart.isElementar(),
					panelStart.getEscopo(),
					panelStart.getTipoFuncao(),
					panelStart.getTipoEfeito(),
					panelStart.getTipoNecessidade());
			
			id = funcaoCaso.getIdFuncaoCaso();
		}

		for (CasesFunctionPanel functionPanel : list) {
			String funcaoStr = functionPanel.getFunctionString();
			FuncaoCaso f = saveFunction(
					funcaoStr, 
					funcaoCaso,
					functionPanel.isElementar(),
					functionPanel.getEscopo(),
					functionPanel.getTipoFuncao(),
					functionPanel.getTipoEfeito(),
					functionPanel.getTipoNecessidade());
			
			saveFunctionTree(functionPanel, f);
		}

		return id;
	}

	private FuncaoCaso saveFunction(	String strFe, 
										FuncaoCaso funcao,
										boolean elementar,
										int tipoEscopo,
										int tipoFuncao,
										int tipoEfeito,
										int tipoNecessidade) {
		
		String[] parsedFE = strFe.split(" ", 2);

		if (parsedFE.length < 2) {
			parsedFE = new String[2];
			parsedFE[0] = strFe;
			parsedFE[1] = "";
		}

		FuncaoDAOImpl feDao = new FuncaoDAOImpl();
		Funcao fEx = feDao.getByStrDescriptor(strFe);

		Funcao fe;

		if (fEx != null
				&& fEx.getStrFuncaoVerbo().equalsIgnoreCase(parsedFE[0])
				&& fEx.getStrFuncaoObjeto().equalsIgnoreCase(parsedFE[1])) {
			fe = fEx;
		} else {
			fe = new Funcao();
			fe.setStrFuncaoVerbo(parsedFE[0]);
			fe.setStrFuncaoObjeto(parsedFE[1]);
			feDao.save(fe);
		}

		FuncaoCaso fc = new FuncaoCaso();

		if (funcao == null) {
			fc.setIdFuncaoPai(0);
		} else {
			fc.setIdFuncaoPai(funcao.getIdFuncaoCaso());
		}

		fc.setIdFuncao(fe.getIdFuncao());
		fc.setBolElementar(elementar);
		fc.setTipoEfeito(tipoEfeito);
		fc.setTipoEscopo(tipoEscopo);
		fc.setTipoNecessidade(tipoNecessidade);
		fc.setTipoFuncao(tipoFuncao);

		FuncaoCasoDAOImpl funcaoCasoDAO = new FuncaoCasoDAOImpl();
		funcaoCasoDAO.save(fc);

		return fc;
	}

	private void saveSolution(FuncaoMatrizVO funcao, Caso caso) {
		List<CasesMatrixRow> listRows = panel_matrix.getList();
		if (listRows == null) {
			NotificationManager.getInstance().notifyUser("Erro", "Falha ao salvar soluções", Notification.FAIL);
			return;
		}
		
		SolucaoDAOImpl solucaoDAO = new SolucaoDAOImpl();
		FuncaoDAOImpl funcaoDAO = new FuncaoDAOImpl();

		for (CasesMatrixRow row : listRows) {
			if (funcao.getFuncao().matches(row.getFuncao().getFuncao())) {
				Funcao f = funcaoDAO.getByStrDescriptor(funcao.getFuncao());

				if (f == null) {
					System.out.println("Não achou função...");
					continue;
				}

				System.out.println("Funcao: " + f.getIdFuncao());

				List<CaseMatrixElement> listElement = row.getList();

				if (listElement != null) {
					for (CaseMatrixElement element : listElement) {
						System.out.println("Elemento: "
								+ element.getConceptID());

						Solucao sol = new Solucao();
						sol.setIdCaso(caso.getIdCaso());
						sol.setIdConceito(element.getConceptID());
						sol.setIdFuncao(f.getIdFuncao());

						solucaoDAO.save(sol);
					}
				}
			}
		}
	}
	
	private void saveWords(){
		
	}

	private List<String> paramsOk() {
		List<String> paramsToWatch = new ArrayList<String>();

		if (txtf_title.getText().isEmpty()) {
			paramsToWatch.add("Tìtulo está vazio");
		}

		if (textFieldAno.getText().isEmpty()) {
			paramsToWatch.add("Ano de implementação do projeto faltando");
		}

		if (((EProjectType) comboBox.getSelectedItem()).getId() == EProjectType.None
				.getId()) {
			paramsToWatch.add("Selecione o tipo de projeto");
		}

		if (txtp_desc.getText().isEmpty()) {
			paramsToWatch.add("Descrição está vazia");
		}

		if (functionPanel.getElementarLevelFunctions().size() <= 1) {
			paramsToWatch.add("Adicione mais funções");
			if (!checkFunctionTreeOk(functionPanel)) {
				paramsToWatch.add("Funções não estão corretas");
			}
		}

		if (!checkSolutionsOk()) {
			paramsToWatch
					.add("Não foram atribuídas soluções a todas as funcões");
		}

		return paramsToWatch;
	}

	private boolean checkFunctionTreeOk(CasesFunctionPanel panelStart) {
		List<CasesFunctionPanel> list = panelStart.getChildren();

		String funcaoStr = panelStart.getFunctionString();
		if (funcaoStr.isEmpty() || funcaoStr.split(" ", 2).length < 2) {
			System.out.println("false");
			return false;
		}
		
		if (!panelStart.isFilledOK()) {
			System.out.println("false");
			return false;
		}

		for (CasesFunctionPanel functionPanel : list) {
			if (!checkFunctionTreeOk(functionPanel)) {
				System.out.println("false");
				return false;
			}
		}

		System.out.println("true");
		return true;
	}

	private boolean checkSolutionsOk() {
		List<CasesMatrixRow> listRows = panel_matrix.getList();
		if (listRows == null || listRows.size() == 0) {
			System.out.println("false - sol");
			return false;
		}

		for (CasesMatrixRow row : listRows) {
			List<CaseMatrixElement> listElement = row.getList();
			if (listElement == null || listElement.size() < 1) {
				System.out.println("false - sol1");
				return false;
			}
		}

		System.out.println("true - sol");
		return true;
	}

	public boolean isEditing() {
		if(functionPanel.getChildren().size() > 0){
			System.out.println("1");
			return true;
		}
		
		if(!txtf_title.getText().isEmpty()){
			System.out.println("2");
			return true;
		}
		
		if(!txtp_desc.getText().isEmpty()){
			System.out.println("3");
			return true;
		}
		
		if(comboBox.getSelectedIndex() > 0){
			System.out.println("5");
			return true;
		}
		
		return false;
	}
	
	public List<FuncaoMatrizVO> getElementarLevelFunctions(Caso caso, FuncaoCaso fc) {
		List<FuncaoMatrizVO> list = new ArrayList<FuncaoMatrizVO>();

		if (fc.getBolElementar()) {
			FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
			Funcao f = fDAO.get(fc.getIdFuncao());
			
			SolucaoDAOImpl solDAO = new SolucaoDAOImpl();
			List<Solucao> sol = solDAO.getByIdFuncaoECaso(f.getIdFuncao(), caso.getIdCaso());

			ConceitoDAOImpl cDAO = new ConceitoDAOImpl();
			List<Conceito> conceitos = new ArrayList<Conceito>();
			for(Solucao solution : sol){
				conceitos.add(cDAO.get(solution.getIdConceito()));
			}
			
			FuncaoMatrizVO vo = new FuncaoMatrizVO(f.toString(), fc.getBolElementar(), fc.getTipoEscopo(), fc.getTipoFuncao(), fc.getTipoEfeito(), fc.getTipoNecessidade(), 0, conceitos);
			list.add(vo);
		}
		
		FuncaoCasoDAOImpl fDAO = new FuncaoCasoDAOImpl();
		List<FuncaoCaso> children = fDAO.getByIdFuncaoPai(fc.getIdFuncaoCaso());

		if (children.size() != 0) {
			for (FuncaoCaso child : children) {
				list.addAll(getElementarLevelFunctions(caso, child));
			}
		}

		return list;
	}
}
