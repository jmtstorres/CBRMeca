package br.unb.ppmec.cbrmeca.view.fragments.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.xml.bind.JAXBException;

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
import br.unb.ppmec.cbrmeca.view.fragments.cases.function.JPanelFunctionClassification;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.CasesMatrixPanel;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;
import br.unb.ppmec.cbrmeca.view.notification.Notification;
import br.unb.ppmec.cbrmeca.view.notification.NotificationManager;
import br.unb.ppmec.cbrmeca.view.splash.SplashScreen;
import br.unb.ppmec.cbrmeca.view.util.io.CaseIO;
import br.unb.ppmec.cbrmeca.view.util.io.FileIO;

public class CBRDashSearchCasesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7106078291303068980L;
	private JTextField textField;
	private JList<Caso> list;
	private JPanelFunctionClassification tree;
	private JPanel panel_2;
	private CasesMatrixPanel matrixPanel;
	private JDialog dialog_wait;

	/**
	 * Create the panel.
	 */
	public CBRDashSearchCasesPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		NotificationManager.getInstance().setupNotification();
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 25));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{961, 0};
		gbl_panel.rowHeights = new int[]{20, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(CBRDashSearchCasesPanel.class.getResource("/imgassets/ds_low.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setPreferredSize(new Dimension(200, 25));
		lblNewLabel.setMinimumSize(new Dimension(200, 25));
		lblNewLabel.setMaximumSize(new Dimension(100, 25));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 144, 596, 10, 0};
		gbl_panel_1.rowHeights = new int[]{40, 60, 500, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ajuda");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MessageDialog dialog = new MessageDialog();
				dialog.setText("<html>Para utilizar a busca, digite o nome do projeto ou selecione na lista.</html>");
				dialog.setVisible(true);
			}
		});
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(CBRDashSearchCasesPanel.class.getResource("/imgassets/help.png")));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridheight = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Termos de busca");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textField.getText().length() > 1){
					System.out.println("typed");
					CasoDAOImpl casoDao = new CasoDAOImpl();
					List<Caso> casos = casoDao.getStartedWith(textField.getText());
					
					DefaultListModel<Caso> dModel = new DefaultListModel<Caso>();
					
					for(Caso caso : casos){
						System.out.println("CasoVO: " + caso.getStrTitulo());
						dModel.addElement(caso);
					}
					list.setModel(dModel);
					list.revalidate();
					list.repaint();
				}else{
					fillList();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {}

			@Override
			public void keyReleased(KeyEvent arg0) {}
		});
		textField.setBorder(new LineBorder(Color.BLACK, 2));
		textField.setMinimumSize(new Dimension(100, 30));
		textField.setMaximumSize(new Dimension(100, 30));
		textField.setSize(new Dimension(100, 30));
		textField.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridheight = 2;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 3;
		gbc_panel_4.gridy = 0;
		panel_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{1, 1, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Exportar");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				try {
					File f = CaseIO.chooseSaveFile();
					if(f.getName().isEmpty()){
						NotificationManager.getInstance().notifyUser("Nome do arquivo", "Informe o nome do arquivo.", Notification.FAIL);
					}
					String file = f.getAbsolutePath();
					if(!file.contains(".cbm")){
						file = file + ".cbm";
					}
					
					BufferedImage imTree = new BufferedImage(tree.getWidth(), tree.getHeight(), BufferedImage.TYPE_INT_ARGB);
					tree.paint(imTree.getGraphics());
					
					BufferedImage imMatrix = new BufferedImage(matrixPanel.getWidth(), matrixPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
					matrixPanel.paint(imMatrix.getGraphics());
					
					FileIO.saveCaseFile(file, list.getSelectedValue(), imTree, imMatrix);
				} catch (JAXBException e) {
					NotificationManager.getInstance().notifyUser("Erro", "Falha exportando caso: Erro coletando informações.", Notification.FAIL);
				} catch (IOException e) {
					NotificationManager.getInstance().notifyUser("Erro", "Falha exportando caso: Erro escrevendo arquivo.", Notification.FAIL);
				}
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(CBRDashSearchCasesPanel.class.getResource("/imgassets/export.png")));
		lblNewLabel_3.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 15, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_4.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Importar");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				File f = CaseIO.chooseSaveFile();
				if(f.getName().isEmpty()){
					NotificationManager.getInstance().notifyUser("Nome do arquivo", "Informe o nome do arquivo.", Notification.FAIL);
				}
				
				final String file;
				
				if(!f.getAbsolutePath().contains(".cbm")){
					file = f.getAbsolutePath() + ".cbm";
				}else{
					file = f.getAbsolutePath();
				}
				
				createDialog();
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							FileIO.loadCaseFile(file);
						} catch (Exception e) {
							NotificationManager.getInstance().notifyUser("Erro", "Falha importando caso: Arquivo corrompido ou caso já existe.", Notification.FAIL);
						}
						fillList();
						revalidate();
						repaint();
						
						dialog_wait.dispose();
					}
				});
				
				t.start();
				
			}
		});
		lblNewLabel_4.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_4.setIcon(new ImageIcon(CBRDashSearchCasesPanel.class.getResource("/imgassets/import.png")));
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 1;
		panel_4.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JTextPane lblResultadoscliquePara = new JTextPane();
		lblResultadoscliquePara.setFocusable(false);
		lblResultadoscliquePara.setEditable(false);
		lblResultadoscliquePara.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblResultadoscliquePara.setText("Resultados (clique para abrir)");
		lblResultadoscliquePara.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblResultadoscliquePara = new GridBagConstraints();
		gbc_lblResultadoscliquePara.fill = GridBagConstraints.BOTH;
		gbc_lblResultadoscliquePara.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultadoscliquePara.gridx = 1;
		gbc_lblResultadoscliquePara.gridy = 1;
		panel_1.add(lblResultadoscliquePara, gbc_lblResultadoscliquePara);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		list = new JList<Caso>();
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//TODO - adicionar isto a tela de cadastro
				Caso caso = list.getSelectedValue();
				tree.setCaseID(caso.getIdFuncaoGeral());
				FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
				List<FuncaoMatrizVO> funcoes = getElementarLevelFunctions(fcDAO.get(caso.getIdFuncaoGeral()));
				System.out.println("FUncoes: " + funcoes.size());
				matrixPanel.clearAll();
				for(FuncaoMatrizVO fVO : funcoes){
					matrixPanel.addFunction(fVO);
				}
				matrixPanel.showButtons(false);
				System.out.println(caso.getStrTitulo());
				System.out.println(caso.getStrDescricao());
			}
		});
		list.setBorder(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 4;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		panel_1.add(tabbedPane, gbc_tabbedPane);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Árvore de Funções", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		tree = new JPanelFunctionClassification();
        tree.setEditable(false);
		tree.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tree);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Matriz Morfológica", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		matrixPanel = new CasesMatrixPanel();
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(Color.WHITE);
		panel_3.add(scrollPane_2);
		scrollPane_2.setViewportView(matrixPanel);
		
		fillList();
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
	
	public List<FuncaoMatrizVO> getElementarLevelFunctions(FuncaoCaso fc) {
		List<FuncaoMatrizVO> list = new ArrayList<FuncaoMatrizVO>();

		if (fc.getBolElementar()) {
			FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
			Funcao f = fDAO.get(fc.getIdFuncao());
			
			SolucaoDAOImpl solDAO = new SolucaoDAOImpl();
			List<Solucao> sol = solDAO.getByIdFuncaoECaso(f.getIdFuncao(), this.list.getSelectedValue().getIdCaso());

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
				list.addAll(getElementarLevelFunctions(child));
			}
		}

		return list;
	}

	private void fillList(){
		CasoDAOImpl casoDao = new CasoDAOImpl();
		List<Caso> casos = casoDao.loadAll();
		
		DefaultListModel<Caso> dModel = new DefaultListModel<Caso>();
		
		for(Caso caso : casos){
			System.out.println("Caso: " + caso.getStrTitulo());
			dModel.addElement(caso);
		}
		list.setModel(dModel);
		list.revalidate();
		list.repaint();
	}

}
