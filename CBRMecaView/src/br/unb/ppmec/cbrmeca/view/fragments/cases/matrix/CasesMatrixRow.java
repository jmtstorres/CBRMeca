package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.com.ppmec.cbrmeca.control.CBREngine;
import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.ImagemConceito;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ImagemConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.ConceptAddedEvent;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.ConceptMatrixObserver;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.MatrixConceptAddedListener;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;
import br.unb.ppmec.cbrmeca.view.notification.Notification;
import br.unb.ppmec.cbrmeca.view.notification.NotificationManager;

public class CasesMatrixRow extends JPanel implements
		MatrixConceptAddedListener {

	private int columns = 1;

	private FuncaoMatrizVO funcao;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4534025550144186364L;

	private static final int MAX_COLUMNS = 12;

	private JLabel label;
	private JPanel panel_matrix;
	private List<CaseMatrixElement> elementos = null;
	private JButton btnAdicionarConceito;
	private JButton btnSugestesDeConceitos;
	private CaseMatrixSelectElement cmse = null;
	private boolean editable = true;

	/**
	 * Create the panel.
	 */
	public CasesMatrixRow(boolean editable) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		this.editable = editable;

		panel_matrix = new JPanel();
		panel_matrix.setBackground(Color.WHITE);
		add(panel_matrix, BorderLayout.WEST);
		GridBagLayout gbl_panel_matrix = new GridBagLayout();
		gbl_panel_matrix.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_matrix.rowHeights = new int[] { 38, 38, 0 };
		gbl_panel_matrix.columnWeights = new double[] { 1.0, 1.0, 0.0 };
		gbl_panel_matrix.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		panel_matrix.setLayout(gbl_panel_matrix);

		label = new JLabel("New label");
		label.setSize(new Dimension(150, 150));
		label.setPreferredSize(new Dimension(150, 150));
		label.setMinimumSize(new Dimension(150, 150));
		label.setMaximumSize(new Dimension(150, 150));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridheight = 2;
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_matrix.add(label, gbc_label);

		btnAdicionarConceito = new JButton("<html>Adicionar<p>Conceito</html>");
		btnAdicionarConceito.setIconTextGap(0);
		btnAdicionarConceito.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdicionarConceito.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdicionarConceito.setIcon(new ImageIcon(CasesMatrixRow.class
				.getResource("/imgassets/add_pq.png")));
		btnAdicionarConceito.setSize(new Dimension(50, 150));
		btnAdicionarConceito.setFocusPainted(false);
		btnAdicionarConceito.setContentAreaFilled(false);
		btnAdicionarConceito.setPreferredSize(new Dimension(80, 75));
		btnAdicionarConceito.setMinimumSize(new Dimension(50, 150));
		btnAdicionarConceito.setMaximumSize(new Dimension(50, 150));
		btnAdicionarConceito.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_btnAdicionarConceito;
		gbc_btnAdicionarConceito = new GridBagConstraints();
		gbc_btnAdicionarConceito.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdicionarConceito.gridx = 1;
		gbc_btnAdicionarConceito.gridy = 1;
		panel_matrix.add(btnAdicionarConceito, gbc_btnAdicionarConceito);
		btnAdicionarConceito.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				addThisAsListener();
				cmse = new CaseMatrixSelectElement(null);
				ConceptMatrixObserver.getInstance().addConceptAddedListener(
						cmse);
				cmse.setVisible(true);
			}
		});

		btnSugestesDeConceitos = new JButton("Sugestões");
		btnSugestesDeConceitos.setIconTextGap(0);
		btnSugestesDeConceitos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSugestesDeConceitos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSugestesDeConceitos.setIcon(new ImageIcon(CasesMatrixRow.class
				.getResource("/imgassets/suggest_pq.png")));
		btnSugestesDeConceitos.setSize(new Dimension(50, 150));
		btnSugestesDeConceitos.setPreferredSize(new Dimension(80, 75));
		btnSugestesDeConceitos.setMinimumSize(new Dimension(50, 150));
		btnSugestesDeConceitos.setMaximumSize(new Dimension(50, 150));
		btnSugestesDeConceitos.setFocusPainted(false);
		btnSugestesDeConceitos.setContentAreaFilled(false);
		btnSugestesDeConceitos.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_btnAdicionarConceito2 = new GridBagConstraints();
		gbc_btnAdicionarConceito2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdicionarConceito2.gridx = 1;
		gbc_btnAdicionarConceito2.gridy = 0;
		panel_matrix.add(btnSugestesDeConceitos, gbc_btnAdicionarConceito2);
		btnSugestesDeConceitos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				FuncaoMatrizVO vo = getFuncao();

				CaseMatrixSimilarFunctionsDialog dialog = new CaseMatrixSimilarFunctionsDialog(
						vo);
				dialog.setModal(true);
				dialog.setVisible(true);

				List<FuncaoCaso> similarCases = dialog.getSimilarCases();

				if (similarCases.size() > 0) {
					Map<Integer, Integer> conceitos = CBREngine.getInstance()
							.getSolutionsForCase(similarCases);
					if (conceitos.size() > 0) {
						ConceitoDAOImpl cDAO = new ConceitoDAOImpl();
						for (Integer conceitoId : conceitos.keySet()) {
							addElement(cDAO.get(conceitoId));
						}
					} else {
						NotificationManager
								.getInstance()
								.notifyUser(
										"Sem Conceitos",
										"Não foram encontradas sugestões de solução...",
										Notification.FAIL);
					}
				} else if (!dialog.cancelled) {
					NotificationManager
							.getInstance()
							.notifyUser(
									"Sem Funções",
									"Não foram encontradas funções suficientemente semelhantes na base...",
									Notification.FAIL);
				}
			}
		});
	}

	private void addThisAsListener() {
		ConceptMatrixObserver.getInstance().addConceptAddedListener(this);
	}

	private void removeThisFromListeners() {
		ConceptMatrixObserver.getInstance().removeConceptAddedListener(this);
	}

	public void setFunction(FuncaoMatrizVO funcao) {
		this.funcao = funcao;
		label.setText(funcao.getFuncao());
		revalidate();
		repaint();
	}

	public FuncaoMatrizVO getFuncao() {
		return funcao;
	}

	public List<CaseMatrixElement> getList() {
		return this.elementos;
	}

	@Override
	public void conceptAdded(ConceptAddedEvent event) {
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				removeThisFromListeners();
			}
		}, 100);
		
		Conceito c = event.getConceito();
		if (c.getIdConceito() != 0) {
			if(elementos != null){
				for(CaseMatrixElement element : elementos){
					System.out.println("elemento id: " + element.getConceptID() + "|" + c.getIdConceito());
					if(element.getConceptID().equals(c.getIdConceito())){
						System.out.println("iguais");
						NotificationManager.getInstance().notifyUser("Já existe", "Conceito já existe nessa coluna.", Notification.FAIL);
						return;
					}
				}
			}
			addElement(event.getConceito());
		}
	}

	public void addElement(Conceito conceito) {

		System.out.println("Adicionou " + columns);
		if (columns > MAX_COLUMNS) {
			return;
		}

		panel_matrix.remove(btnAdicionarConceito);
		panel_matrix.remove(btnSugestesDeConceitos);

		CaseMatrixElement element = new CaseMatrixElement();
		element.setConceptID(conceito.getIdConceito());
		element.setSubtitle(conceito.getStrConceito());
		element.setEditable(editable);

		element.setImageClickListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				element.hoverAction(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				element.hoverAction(true);
			}
		});

		element.setLblCloseClickListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				panel_matrix.remove(element);
				elementos.remove(element);
				panel_matrix.revalidate();
				panel_matrix.repaint();
				columns--;
			}
		});

		ImagemConceitoDAOImpl imgDao = new ImagemConceitoDAOImpl();
		ImagemConceito imagem = imgDao.get(conceito.getImgConceito());
		element.setImage(imagem.getDirImagemConceito());

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.NONE;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		//gbc_lblNewLabel_1.gridx = columns++;
		gbc_lblNewLabel_1.gridy = 0;
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.gridheight = 2;
		panel_matrix.add(element, gbc_lblNewLabel_1);

		gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		//gbc_lblNewLabel_1.gridx = columns + 1;
		gbc_lblNewLabel_1.gridy = 1;
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.gridheight = 1;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 0);
		panel_matrix.add(btnAdicionarConceito, gbc_lblNewLabel_1);

		gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		//gbc_lblNewLabel_1.gridx = columns + 1;
		gbc_lblNewLabel_1.gridy = 0;
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.gridheight = 1;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 0);
		panel_matrix.add(btnSugestesDeConceitos, gbc_lblNewLabel_1);

		if (elementos == null) {
			elementos = new ArrayList<CaseMatrixElement>();
		}

		for (CaseMatrixElement elemento : elementos) {
			if (elemento.getConceptID() == element.getConceptID()) {
				NotificationManager.getInstance().notifyUser("Já existe",
						"Conceito já adicionado nesta linha...",
						Notification.FAIL);
				return;
			}
		}

		elementos.add(element);

		panel_matrix.revalidate();
		panel_matrix.repaint();
	}

	public void showButtons(boolean show) {
		btnAdicionarConceito.setVisible(show);
		btnSugestesDeConceitos.setVisible(show);
		if(elementos != null){
			for(CaseMatrixElement element : elementos){
				element.setCloseEnabled(show);
			}
		}
	}
}
