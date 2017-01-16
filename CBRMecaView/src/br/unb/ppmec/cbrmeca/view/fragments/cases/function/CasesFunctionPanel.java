/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.function;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.unb.ppmec.cbrmeca.db.constants.EFunctionEffect;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionNecessity;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionScope;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionType;
import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.view.components.autocomplete.AutoComboBox;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;

// TODO: Auto-generated Javadoc
/**
 * The Class CasesFunctionPanel.
 */
public class CasesFunctionPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5204293685056545405L;

	/** The Constant MAX_LEVELS. */
	private static final int MAX_LEVELS = 7;

	/** The level. */
	private int level = 0;

	/** The text field. */
	private AutoComboBox textField;

	/** The panel_new. */
	private JPanel panel_new;

	/** The children. */
	private List<CasesFunctionPanel> children;

	/** The btn_add. */
	private JButton btn_add;
	
	/** The btn_remove. */
	private JButton btn_remove;
	
	/** The lbl separator. */
	private PaintedLabel lblSeparator;
	
	/** The chckbx new check box. */
	private JCheckBox chckbxNewCheckBox;
	
	/** The panel_1. */
	private JPanel panel_1;

	/** The panel text. */
	private JPanel panelText;

	/** The parent. */
	private CasesFunctionPanel parent = null;
	
	/** The panel_2. */
	private JPanel panel_2;
	
	/** The layered pane. */
	private JLayeredPane layeredPane;
	
	/** The lbl alerta. */
	private JLabel lblAlerta;
	
	/** The panel_3. */
	private JPanel panel_3;
	
	/** The btn information. */
	private JButton btnInformation;
	
	/** The btn mechanics. */
	private JButton btnMechanics;
	
	/** The btn control. */
	private JButton btnControl;
	
	/** The btn power. */
	private JButton btnPower;
	
	/** The c box efeito. */
	private JComboBox<EFunctionEffect> cBoxEfeito;
	
	/** The c box necessidade. */
	private JComboBox<EFunctionNecessity> cBoxNecessidade;
	
	/** The c box tipo. */
	private JComboBox<EFunctionType> cBoxTipo;

	/** The escopo. */
	private int escopo = 0;
	
	/** The tipo funcao. */
	private int tipoFuncao = 0;
	
	/** The tipo efeito. */
	private int tipoEfeito = 0;
	
	/** The tipo necessidade. */
	private int tipoNecessidade = 0;
	
	/** The id tipo caso. */
	private int idTipoCaso = 0;

	/** The filled ok. */
	private boolean filledOK;

	/**
	 * Create the panelText.
	 *
	 * @param parent the parent
	 * @param level the level
	 */
	public CasesFunctionPanel(CasesFunctionPanel parent, int level) {
		setMinimumSize(new Dimension(150, 50));
		setMaximumSize(new Dimension(150, 250));
		setBorder(null);
		initialize();
		this.level = level;

		lblSeparator = new PaintedLabel(getConnection());
		lblSeparator.setIconTextGap(0);
		lblSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeparator.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSeparator.setPreferredSize(new Dimension(5, 40));

		panelText = new JPanel();
		panelText.setPreferredSize(new Dimension(180, 25));
		panelText.setMinimumSize(new Dimension(250, 25));
		panelText.setMaximumSize(new Dimension(250, 25));
		panelText.setBackground(Color.WHITE);
		panelText.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panelText, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 100 };
		gbl_panel.rowHeights = new int[] { 133, 0 };
		gbl_panel.columnWeights = new double[] { 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelText.setLayout(gbl_panel);

		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 0;
		panelText.add(layeredPane, gbc_layeredPane);

		lblAlerta = new JLabel("");
		layeredPane.setLayer(lblAlerta, 1);
		lblAlerta.setIcon(new ImageIcon(CasesFunctionPanel.class
				.getResource("/imgassets/alert_pq.png")));
		lblAlerta.setToolTipText("Preencha todos os campos.");
		lblAlerta.setBounds(150, 0, 25, 25);
		layeredPane.add(lblAlerta);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 0, 176, 133);
		layeredPane.add(panel_2);
		panel_2.setPreferredSize(new Dimension(100, 10));
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 145, 0 };
		gbl_panel_2.rowHeights = new int[] { 65, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		textField = new AutoComboBox();
		textField.setBackground(new Color(255, 255, 255));
		textField.setOpaque(false);
		textField.setMaximumRowCount(120);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				checkOK();
			}
		});
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseClicked(e);
				checkOK();
			}
		});
		textField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				checkOK();
			}
		});

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel_2.add(textField, gbc_textField);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setMinimumSize(new Dimension(180, 25));
		textField.setMaximumSize(new Dimension(180, 25));
		textField.setPreferredSize(new Dimension(80, 25));
		textField.setSize(new Dimension(180, 25));
		textField.setBorder(null);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		btnInformation = new JButton("");
		btnInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnInformation, EFunctionScope.INFORMATION);
			}
		});
		btnInformation.setOpaque(false);
		btnInformation.setContentAreaFilled(false);
		btnInformation.setIcon(new ImageIcon(CasesFunctionPanel.class
				.getResource("/imgassets/info_pq.png")));
		btnInformation.setMargin(new Insets(0, 0, 0, 0));
		btnInformation.setIconTextGap(0);
		btnInformation.setFocusable(false);
		btnInformation.setFocusPainted(false);
		btnInformation.setBorder(new LineBorder(Color.WHITE));
		btnInformation.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnInformation = new GridBagConstraints();
		gbc_btnInformation.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInformation.gridx = 0;
		gbc_btnInformation.gridy = 0;
		panel_3.add(btnInformation, gbc_btnInformation);

		btnMechanics = new JButton("");
		btnMechanics.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnMechanics, EFunctionScope.MECHANIC);
			}
		});
		btnMechanics.setOpaque(false);
		btnMechanics.setContentAreaFilled(false);
		btnMechanics.setIcon(new ImageIcon(CasesFunctionPanel.class
				.getResource("/imgassets/mecanics_pq.png")));
		btnMechanics.setMargin(new Insets(0, 0, 0, 0));
		btnMechanics.setIconTextGap(0);
		btnMechanics.setFocusable(false);
		btnMechanics.setFocusPainted(false);
		btnMechanics.setBorder(new LineBorder(Color.WHITE));
		btnMechanics.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnMechanics = new GridBagConstraints();
		gbc_btnMechanics.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMechanics.gridx = 1;
		gbc_btnMechanics.gridy = 0;
		panel_3.add(btnMechanics, gbc_btnMechanics);

		btnControl = new JButton("");
		btnControl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnControl, EFunctionScope.CONTROL);
			}
		});
		btnControl.setOpaque(false);
		btnControl.setContentAreaFilled(false);
		btnControl.setIcon(new ImageIcon(CasesFunctionPanel.class
				.getResource("/imgassets/control_pq.png")));
		btnControl.setMargin(new Insets(0, 0, 0, 0));
		btnControl.setIconTextGap(0);
		btnControl.setFocusable(false);
		btnControl.setFocusPainted(false);
		btnControl.setBorder(new LineBorder(Color.WHITE));
		btnControl.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnControl = new GridBagConstraints();
		gbc_btnControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnControl.gridx = 2;
		gbc_btnControl.gridy = 0;
		panel_3.add(btnControl, gbc_btnControl);

		btnPower = new JButton("");
		btnPower.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnPower, EFunctionScope.POWER);
			}
		});
		btnPower.setOpaque(false);
		btnPower.setContentAreaFilled(false);
		btnPower.setIcon(new ImageIcon(CasesFunctionPanel.class
				.getResource("/imgassets/power_pq.png")));
		btnPower.setMargin(new Insets(0, 0, 0, 0));
		btnPower.setIconTextGap(0);
		btnPower.setFocusable(false);
		btnPower.setFocusPainted(false);
		btnPower.setBorder(new LineBorder(Color.WHITE));
		btnPower.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnPower = new GridBagConstraints();
		gbc_btnPower.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPower.gridx = 3;
		gbc_btnPower.gridy = 0;
		panel_3.add(btnPower, gbc_btnPower);

		cBoxEfeito = new JComboBox<EFunctionEffect>();
		cBoxEfeito.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_cBoxEfeito = new GridBagConstraints();
		gbc_cBoxEfeito.gridwidth = 2;
		gbc_cBoxEfeito.fill = GridBagConstraints.BOTH;
		gbc_cBoxEfeito.gridx = 0;
		gbc_cBoxEfeito.gridy = 1;
		panel_3.add(cBoxEfeito, gbc_cBoxEfeito);

		cBoxNecessidade = new JComboBox<EFunctionNecessity>();
		cBoxNecessidade.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_cBoxNecessidade = new GridBagConstraints();
		gbc_cBoxNecessidade.gridwidth = 2;
		gbc_cBoxNecessidade.fill = GridBagConstraints.BOTH;
		gbc_cBoxNecessidade.gridx = 2;
		gbc_cBoxNecessidade.gridy = 1;
		panel_3.add(cBoxNecessidade, gbc_cBoxNecessidade);

		chckbxNewCheckBox = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 2;
		panel_3.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		chckbxNewCheckBox.setBackground(Color.BLACK);

		cBoxTipo = new JComboBox<EFunctionType>();
		cBoxTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_cBoxTipo = new GridBagConstraints();
		gbc_cBoxTipo.gridwidth = 3;
		gbc_cBoxTipo.fill = GridBagConstraints.BOTH;
		gbc_cBoxTipo.gridx = 1;
		gbc_cBoxTipo.gridy = 2;
		panel_3.add(cBoxTipo, gbc_cBoxTipo);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("changed");
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Selected");
					textField.setBorder(new LineBorder(Color.RED, 2));
					chckbxNewCheckBox.setBackground(Color.RED);
				} else if (arg0.getStateChange() == ItemEvent.DESELECTED) {
					System.out.println("Deselected");
					textField.setBorder(new LineBorder(Color.BLACK, 2));
					chckbxNewCheckBox.setBackground(Color.BLACK);
				}
				textField.revalidate();
				textField.repaint();
			}
		});

		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setMaximumSize(new Dimension(250, 40));
		panel_1.setMinimumSize(new Dimension(250, 40));
		panel_1.setSize(new Dimension(250, 40));
		panel_1.setPreferredSize(new Dimension(180, 40));
		panel_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 40, 40, 0 };
		gbl_panel_1.rowHeights = new int[] { 50, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		btn_add = new JButton("");
		btn_add.setToolTipText("Adicionar desdobramento");
		btn_add.setIcon(new ImageIcon(CasesFunctionPanel.class.getResource("/imgassets/add_below.png")));
		btn_add.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_add = new GridBagConstraints();
		gbc_btn_add.fill = GridBagConstraints.BOTH;
		gbc_btn_add.gridx = 0;
		gbc_btn_add.gridy = 0;
		panel_1.add(btn_add, gbc_btn_add);
		btn_add.setMinimumSize(new Dimension(80, 25));
		btn_add.setMaximumSize(new Dimension(80, 25));
		btn_add.setSize(new Dimension(80, 25));
		btn_add.setPreferredSize(new Dimension(80, 25));
		btn_add.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btn_add.setContentAreaFilled(false);
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (level < MAX_LEVELS) {
					CasesFunctionPanel panel_add = new CasesFunctionPanel(
							getMe(), level + 1);
					addChild(panel_add);
					lblSeparator.setVisible(true);
					revalidate();
					panel_add.revalidate();

					repaintImage();
				}
			}
		});

		btn_remove = new JButton("");
		btn_remove.setIcon(new ImageIcon(CasesFunctionPanel.class.getResource("/imgassets/remove_this.png")));
		btn_remove.setToolTipText("Remover esta função");
		btn_remove.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_remove = new GridBagConstraints();
		gbc_btn_remove.fill = GridBagConstraints.BOTH;
		gbc_btn_remove.gridx = 1;
		gbc_btn_remove.gridy = 0;
		panel_1.add(btn_remove, gbc_btn_remove);
		btn_remove.setMinimumSize(new Dimension(80, 25));
		btn_remove.setMaximumSize(new Dimension(80, 25));
		btn_remove.setSize(new Dimension(80, 25));
		btn_remove.setPreferredSize(new Dimension(80, 25));
		btn_remove.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btn_remove.setContentAreaFilled(false);
		btn_remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (parent != null) {
					parent.removeChild(getMe());
					System.out.println("Removido.");
					revalidate();
					repaintImage();
				}
			}
		});
		lblSeparator.setBorder(null);
		lblSeparator.setBackground(Color.WHITE);
		lblSeparator.setSize(new Dimension(5, 40));
		lblSeparator.setMinimumSize(new Dimension(5, 40));
		lblSeparator.setMaximumSize(new Dimension(5, 40));
		GridBagConstraints gbc_lblSeparator = new GridBagConstraints();
		gbc_lblSeparator.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSeparator.gridx = 0;
		gbc_lblSeparator.gridy = 2;
		add(lblSeparator, gbc_lblSeparator);

		panel_new = new JPanel();
		panel_new.setBackground(Color.WHITE);
		panel_new.setBorder(null);
		GridBagConstraints gbc_panel_new = new GridBagConstraints();
		gbc_panel_new.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_new.gridx = 0;
		gbc_panel_new.gridy = 3;
		add(panel_new, gbc_panel_new);
		GridBagLayout gbl_panel_new = new GridBagLayout();
		gbl_panel_new.columnWidths = new int[] { 0 };
		gbl_panel_new.rowHeights = new int[] { 0 };
		gbl_panel_new.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel_new.rowWeights = new double[] { Double.MIN_VALUE };
		panel_new.setLayout(gbl_panel_new);

		if (level >= MAX_LEVELS) {
			btn_add.setVisible(false);
			lblSeparator.setVisible(false);
		}

		if (parent == null) {
			btn_remove.setVisible(false);
			chckbxNewCheckBox.setVisible(false);
			panel_3.setVisible(false);
		}

		// Tem que ficar no final
		initCombo();
		checkOK();
	}

	/**
	 * Paint function scope.
	 *
	 * @param button the button
	 * @param scopeID the scope id
	 */
	private void paintFunctionScope(JButton button, int scopeID) {
		if ((this.escopo & scopeID) == scopeID) {
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		} else {
			button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		}
	}

	/**
	 * Update function scope.
	 *
	 * @param button the button
	 * @param scope the scope
	 */
	protected void updateFunctionScope(JButton button, EFunctionScope scope) {
		int escopoAtual = this.escopo;

		if ((this.escopo & scope.getId()) == scope.getId()) {
			this.escopo = (escopoAtual & ~scope.getId());
		} else {
			this.escopo = (escopoAtual | scope.getId());
		}

		escopoAtual = this.escopo;
		paintFunctionScope(button, scope.getId());
		checkOK();
	}

	/**
	 * Checks if is filled ok.
	 *
	 * @return true, if is filled ok
	 */
	public boolean isFilledOK() {
		return filledOK;
	}

	/**
	 * Check ok.
	 */
	private void checkOK() {

		if (this.tipoEfeito != 0 && 
			this.tipoNecessidade != 0 && 
			this.tipoFuncao != 0 && 
			this.escopo != 0	&&
			!this.textField.getText().isEmpty()) {

			lblAlerta.setIcon(new ImageIcon(
					JPanelFunctionBoxClassification.class
							.getResource("/imgassets/ok_pq.png")));
			lblAlerta.setToolTipText("Função preenchida.");
			filledOK = true;
		} else {
			lblAlerta.setIcon(new ImageIcon(
					JPanelFunctionBoxClassification.class
							.getResource("/imgassets/alert_pq.png")));
			lblAlerta.setToolTipText("Preencha todos os campos.");
			filledOK = false;
		}
		
		if(	parent == null &&
			!this.textField.getText().isEmpty()){
			
			lblAlerta.setIcon(new ImageIcon(
					JPanelFunctionBoxClassification.class
							.getResource("/imgassets/ok_pq.png")));
			lblAlerta.setToolTipText("Função preenchida.");
			filledOK = true;
		}
		
		revalidate();
		repaint();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		repaintImage();
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(CasesFunctionPanel parent) {
		this.parent = parent;
	}

	/**
	 * Repaint image.
	 */
	public void repaintImage() {
		if (parent != null) {
			parent.repaintImage();
		}

		lblSeparator.setImage(getConnection());
		lblSeparator.repaint();
	}

	/**
	 * Gets the text panel position.
	 *
	 * @return the text panel position
	 */
	public int getTextPanelPosition() {
		return (int) panelText.getBounds().getCenterX() + 130;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	private BufferedImage getConnection() {
		BufferedImage image = (BufferedImage) new BufferedImage(
				getWidth() + 10, getHeight() + 10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		Graphics2D g2d = image.createGraphics();

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));

		List<CasesFunctionPanel> panelList = getChildren();

		for (CasesFunctionPanel fPanel : panelList) {

			int xParent = (int) (panelText.getBounds().getCenterX());
			int xChildren = (int) fPanel.getBounds().getCenterX();

			g2d.drawLine(xParent, 0, xParent, 20);
			g2d.drawLine(xParent, 20, xChildren, 20);
			g2d.drawLine(xChildren, 20, xChildren, 40);
		}

		g2d.dispose();

		return image;
	}

	/**
	 * Sets the id tipo caso.
	 *
	 * @param idTipoCaso the new id tipo caso
	 */
	public void setIdTipoCaso(int idTipoCaso) {
		this.idTipoCaso = idTipoCaso;
		for(CasesFunctionPanel child : children){
			child.setIdTipoCaso(idTipoCaso);
		}
	}

	/**
	 * Gets the elementar level functions.
	 *
	 * @return the elementar level functions
	 */
	public List<FuncaoMatrizVO> getElementarLevelFunctions() {
		List<FuncaoMatrizVO> list = new ArrayList<FuncaoMatrizVO>();

		if (chckbxNewCheckBox.isSelected()) {
			FuncaoMatrizVO vo = new FuncaoMatrizVO(textField.getText(), isElementar(), escopo, tipoFuncao, tipoEfeito, tipoNecessidade, idTipoCaso, null);
			list.add(vo);
		}

		if (children.size() != 0) {
			for (CasesFunctionPanel child : children) {
				list.addAll(child.getElementarLevelFunctions());
			}
		}

		return list;
	}

	/**
	 * Gets the level functions.
	 *
	 * @param level the level
	 * @return the level functions
	 */
	public List<String> getLevelFunctions(int level) {
		List<String> list = new ArrayList<String>();

		if (children.size() == 0) {
			System.out.println("nope");
			if (level == this.level) {
				list.add(textField.getText());
				System.out.println("adicionou");
			}
		} else {
			for (CasesFunctionPanel child : children) {
				System.out.println("descendo...");
				list.addAll(child.getLevelFunctions(level));
			}
		}

		return list;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		setBackground(Color.WHITE);

		children = new ArrayList<CasesFunctionPanel>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 0 };
		gridBagLayout.rowHeights = new int[] { 137, 30, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
	}

	/**
	 * Adds the child.
	 *
	 * @param panel the panel
	 */
	public void addChild(CasesFunctionPanel panel) {
		children.add(panel);
		
		GridBagConstraints gbc_new = new GridBagConstraints();
		gbc_new.insets = new Insets(0, 5, 0, 5);
		gbc_new.fill = GridBagConstraints.BOTH;
		System.out.println(children.size());
		gbc_new.gridy = 0;

		panel.setParent(this);
		panel.setIdTipoCaso(idTipoCaso);

		panel_new.add(panel, gbc_new);
		lblSeparator.setVisible(true);

		revalidate();
		repaint();
	}

	/**
	 * Removes the child.
	 *
	 * @param panel the panel
	 */
	public void removeChild(CasesFunctionPanel panel) {
		children.remove(panel);
		panel_new.remove(panel);
		if (children.size() == 0) {
			lblSeparator.setVisible(false);
		}
		revalidate();
		repaint();
	}

	/**
	 * Clear all.
	 */
	public void clearAll() {
		panel_new.removeAll();
		textField.setText("");
		children.clear();
		lblSeparator.setVisible(false);
		revalidate();
		repaint();
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<CasesFunctionPanel> getChildren() {
		return this.children;
	}

	/**
	 * Gets the function string.
	 *
	 * @return the function string
	 */
	public String getFunctionString() {
		return this.textField.getText();
	}

	/**
	 * Gets the me.
	 *
	 * @return the me
	 */
	public CasesFunctionPanel getMe() {
		return this;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		ScrollPane scroll_function = new ScrollPane();
		scroll_function.setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] {};
		gbl_panel.rowWeights = new double[] {};
		panel.setLayout(gbl_panel);
		CasesFunctionPanel functionPanel = new CasesFunctionPanel(null, 0);
		functionPanel.setOpaque(false);
		GridBagConstraints gbc_functionPanel = new GridBagConstraints();
		gbc_functionPanel.gridx = 0;
		gbc_functionPanel.gridy = 0;
		panel.add(functionPanel, gbc_functionPanel);
		scroll_function.add(panel);

		frame.getContentPane().add(scroll_function);
		frame.setVisible(true);
	}
	
	/**
	 * Load from case.
	 *
	 * @param caso the caso
	 */
	public void loadFromCase(Caso caso){
		FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
		FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
		FuncaoCaso fc = fcDAO.get(caso.getIdFuncaoGeral());
		Funcao f = fDAO.get(fc.getIdFuncao());
		this.textField.setText(f.toString());
				
		List<FuncaoCaso> list = fcDAO.getByIdFuncaoPai(caso.getIdFuncaoGeral());
		addChildren(list);
	}
	
	/**
	 * Adds the children.
	 *
	 * @param list the list
	 */
	public void addChildren(List<FuncaoCaso> list){
		for(FuncaoCaso fc : list){
			FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
			FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
			
			Funcao f = fDAO.get(fc.getIdFuncao());
			
			CasesFunctionPanel panel_add = new CasesFunctionPanel(
					getMe(), level + 1);

			panel_add.setText(f.toString());
			panel_add.setEfeito(fc.getTipoEfeito());
			panel_add.setTipoFuncao(fc.getTipoFuncao());
			panel_add.setTipoNecessidade(fc.getTipoNecessidade());
			panel_add.setTipoEscopo(fc.getTipoEscopo());
			panel_add.setElementar(fc.getBolElementar());
			
			
			addChild(panel_add);
			lblSeparator.setVisible(true);
			revalidate();
			panel_add.revalidate();
		
			List<FuncaoCaso> list_children = fcDAO.getByIdFuncaoPai(fc.getIdFuncaoCaso());
			panel_add.addChildren(list_children);
	
			repaintImage();
		}
	}

	/**
	 * Sets the tipo escopo.
	 *
	 * @param tipoEscopo the new tipo escopo
	 */
	private void setTipoEscopo(Integer tipoEscopo) {
		for(EFunctionScope scope : EFunctionScope.values()){
			if(EFunctionScope.CONTROL == scope){
				paintFunctionScope(btnControl, scope.getId());
			}else if(EFunctionScope.MECHANIC == scope){
				paintFunctionScope(btnMechanics, scope.getId());
			}else if(EFunctionScope.POWER == scope){
				paintFunctionScope(btnPower, scope.getId());
			}else if(EFunctionScope.INFORMATION == scope){
				paintFunctionScope(btnInformation, scope.getId());
			}
		}
	}

	/**
	 * Sets the tipo necessidade.
	 *
	 * @param tipoNecessidade2 the new tipo necessidade
	 */
	private void setTipoNecessidade(Integer tipoNecessidade2) {
		this.cBoxNecessidade.setSelectedIndex(tipoNecessidade2);
	}

	/**
	 * Sets the tipo funcao.
	 *
	 * @param tipoFuncao2 the new tipo funcao
	 */
	private void setTipoFuncao(Integer tipoFuncao2) {
		this.cBoxTipo.setSelectedIndex(tipoFuncao2);
	}

	/**
	 * Sets the efeito.
	 *
	 * @param tipoEfeito2 the new efeito
	 */
	private void setEfeito(Integer tipoEfeito2) {
		this.cBoxEfeito.setSelectedIndex(tipoEfeito);
	}

	/**
	 * Sets the elementar.
	 *
	 * @param bolElementar the new elementar
	 */
	private void setElementar(boolean bolElementar) {
		this.chckbxNewCheckBox.setSelected(bolElementar);
	}

	/**
	 * Sets the text.
	 *
	 * @param string the new text
	 */
	private void setText(String string) {
		this.textField.setText(string);
	}

	/**
	 * Checks if is elementar.
	 *
	 * @return true, if is elementar
	 */
	public boolean isElementar() {
		return chckbxNewCheckBox.isSelected();
	}

	/**
	 * Inits the combo.
	 */
	private void initCombo() {
		for (EFunctionType type : EFunctionType.values()) {
			cBoxTipo.addItem(type);
		}

		if(this.tipoFuncao != 0){
			cBoxTipo.setSelectedIndex(this.tipoFuncao);
		}
		
		cBoxTipo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panel_2.setBackground(((EFunctionType) e.getItem()).getColor());
					tipoFuncao = ((EFunctionType) e.getItem()).getId();
					checkOK();
				}
			}
		});

		for (EFunctionEffect type : EFunctionEffect.values()) {
			cBoxEfeito.addItem(type);
		}

		if(this.tipoEfeito != 0){
			cBoxEfeito.setSelectedIndex(this.tipoEfeito);
		}
		
		cBoxEfeito.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tipoEfeito = ((EFunctionEffect) e.getItem()).getId();
					checkOK();
				}
			}
		});

		for (EFunctionNecessity type : EFunctionNecessity.values()) {
			cBoxNecessidade.addItem(type);
		}

		if(this.tipoNecessidade != 0){
			cBoxNecessidade.setSelectedIndex(this.tipoNecessidade);
		}
		
		cBoxNecessidade.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tipoNecessidade = ((EFunctionNecessity) e.getItem()).getId();
					checkOK();
				}
			}
		});
	}

	/**
	 * Gets the escopo.
	 *
	 * @return the escopo
	 */
	public int getEscopo() {
		return escopo;
	}

	/**
	 * Gets the tipo funcao.
	 *
	 * @return the tipo funcao
	 */
	public int getTipoFuncao() {
		return tipoFuncao;
	}

	/**
	 * Gets the tipo efeito.
	 *
	 * @return the tipo efeito
	 */
	public int getTipoEfeito() {
		return tipoEfeito;
	}

	/**
	 * Gets the tipo necessidade.
	 *
	 * @return the tipo necessidade
	 */
	public int getTipoNecessidade() {
		return tipoNecessidade;
	}
}
