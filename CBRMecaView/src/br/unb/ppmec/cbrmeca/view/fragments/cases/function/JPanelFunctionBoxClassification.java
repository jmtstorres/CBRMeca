/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.function;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.unb.ppmec.cbrmeca.db.constants.EFunctionEffect;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionNecessity;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionScope;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionType;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;

// TODO: Auto-generated Javadoc
/**
 * The Class JPanelFunctionBoxClassification.
 */
public class JPanelFunctionBoxClassification extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1338656805293536362L;

	/** The Constant BLOCK_WIDTH. */
	public static final int BLOCK_WIDTH = 150;
	
	/** The Constant BLOCK_HEIGHT. */
	public static final int BLOCK_HEIGHT = 150;

	/** The funcao caso. */
	private FuncaoCaso funcaoCaso;
	
	/** The c box tipo. */
	private JComboBox<EFunctionType> cBoxTipo;

	/** The text area. */
	private JLabel textArea;

	/** The c box efeito. */
	private JComboBox<EFunctionEffect> cBoxEfeito;
	
	/** The c box necessidade. */
	private JComboBox<EFunctionNecessity> cBoxNecessidade;

	/** The lbl alerta. */
	private JLabel lblAlerta;

	/** The btn information. */
	private JButton btnInformation;

	/** The btn mechanics. */
	private JButton btnMechanics;

	/** The btn control. */
	private JButton btnControl;

	/** The btn power. */
	private JButton btnPower;
	
	/** The enabled. */
	private boolean enabled;
	
	/** The selected. */
	private boolean selected;

	/**
	 * Create the panel.
	 *
	 * @param funcaoCaso the funcao caso
	 */
	public JPanelFunctionBoxClassification(FuncaoCaso funcaoCaso) {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setSize(new Dimension(150, 100));
		setPreferredSize(new Dimension(150, 150));
		setMinimumSize(new Dimension(150, 100));
		setMaximumSize(new Dimension(150, 100));
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane panelContent = new JLayeredPane();
		add(panelContent);

		this.funcaoCaso = funcaoCaso;
		panelContent.setLayout(null);
		
		lblAlerta = new JLabel("");
		lblAlerta.setToolTipText("Preencha todos os campos.");
		lblAlerta.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/alert_pq.png")));
		lblAlerta.setBounds(119, 0, 25, 25);
		panelContent.add(lblAlerta);

		textArea = new JLabel();
		textArea.setBounds(0, 0, 144, 78);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 10));
		textArea.setHorizontalAlignment(SwingConstants.CENTER);
		textArea.setHorizontalTextPosition(SwingConstants.CENTER);
		panelContent.add(textArea);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 78, 144, 26);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		panelContent.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{25, 25, 25, 25, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnInformation = new JButton("");
		btnInformation.setContentAreaFilled(false);
		btnInformation.setFocusable(false);
		btnInformation.setFocusPainted(false);
		btnInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnInformation, EFunctionScope.INFORMATION);
			}
		});
		btnInformation.setBorder(new LineBorder(Color.WHITE));
		btnInformation.setBackground(Color.WHITE);
		btnInformation.setMargin(new Insets(0, 0, 0, 0));
		btnInformation.setIconTextGap(0);
		btnInformation.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/info_pq.png")));
		GridBagConstraints gbc_btnInformation = new GridBagConstraints();
		gbc_btnInformation.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInformation.gridx = 0;
		gbc_btnInformation.gridy = 0;
		panel.add(btnInformation, gbc_btnInformation);
		
		btnMechanics = new JButton("");
		btnMechanics.setContentAreaFilled(false);
		btnMechanics.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnMechanics, EFunctionScope.MECHANIC);
			}
		});
		btnMechanics.setFocusable(false);
		btnMechanics.setFocusPainted(false);
		btnMechanics.setBorder(new LineBorder(Color.WHITE));
		btnMechanics.setBackground(Color.WHITE);
		btnMechanics.setMargin(new Insets(0, 0, 0, 0));
		btnMechanics.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/mecanics_pq.png")));
		btnMechanics.setIconTextGap(0);
		GridBagConstraints gbc_btnMechanics = new GridBagConstraints();
		gbc_btnMechanics.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMechanics.gridx = 1;
		gbc_btnMechanics.gridy = 0;
		panel.add(btnMechanics, gbc_btnMechanics);
		
		btnControl = new JButton("");
		btnControl.setContentAreaFilled(false);
		btnControl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnControl, EFunctionScope.CONTROL);
			}
		});
		btnControl.setFocusable(false);
		btnControl.setFocusPainted(false);
		btnControl.setBorder(new LineBorder(Color.WHITE));
		btnControl.setBackground(Color.WHITE);
		btnControl.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/control_pq.png")));
		btnControl.setMargin(new Insets(0, 0, 0, 0));
		btnControl.setIconTextGap(0);
		GridBagConstraints gbc_btnControl = new GridBagConstraints();
		gbc_btnControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnControl.gridx = 2;
		gbc_btnControl.gridy = 0;
		panel.add(btnControl, gbc_btnControl);
		
		btnPower = new JButton("");
		btnPower.setContentAreaFilled(false);
		btnPower.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateFunctionScope(btnPower, EFunctionScope.POWER);
			}
		});
		btnPower.setFocusable(false);
		btnPower.setFocusPainted(false);
		btnPower.setBorder(new LineBorder(Color.WHITE));
		btnPower.setBackground(Color.WHITE);
		btnPower.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/power_pq.png")));
		btnPower.setMargin(new Insets(0, 0, 0, 0));
		btnPower.setIconTextGap(0);
		GridBagConstraints gbc_btnPower = new GridBagConstraints();
		gbc_btnPower.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPower.gridx = 3;
		gbc_btnPower.gridy = 0;
		panel.add(btnPower, gbc_btnPower);
		
		cBoxEfeito = new JComboBox<EFunctionEffect>();
		cBoxEfeito.setBounds(0, 104, 72, 20);
		cBoxEfeito.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelContent.add(cBoxEfeito);
		
		cBoxNecessidade = new JComboBox<EFunctionNecessity>();
		cBoxNecessidade.setBounds(72, 104, 72, 20);
		cBoxNecessidade.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelContent.add(cBoxNecessidade);

		cBoxTipo = new JComboBox<EFunctionType>();
		cBoxTipo.setBounds(0, 124, 144, 20);
		cBoxTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelContent.add(cBoxTipo);

		if (funcaoCaso != null) {
			FuncaoDAOImpl fdao = new FuncaoDAOImpl();
			Funcao funcao = fdao.get(funcaoCaso.getIdFuncao());

			textArea.setText("<html><center>" + funcao.getStrFuncaoVerbo()
					+ "<p>" + funcao.getStrFuncaoObjeto() + "</center></html>");
			textArea.setOpaque(false);

			if (funcaoCaso.getTipoFuncao() != null
					&& funcaoCaso.getTipoFuncao() != 0) {
				setBackground(EFunctionType.values()[funcaoCaso.getTipoFuncao()]
						.getColor());
			}
			
			paintFunctionScope(btnControl, EFunctionScope.CONTROL.getId());
			paintFunctionScope(btnInformation, EFunctionScope.INFORMATION.getId());
			paintFunctionScope(btnMechanics, EFunctionScope.MECHANIC.getId());
			paintFunctionScope(btnPower, EFunctionScope.POWER.getId());
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
	private void paintFunctionScope(JButton button, int scopeID){
		if((funcaoCaso.getTipoEscopo() & scopeID) == scopeID){
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}else{
			button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		}
	}
	
	/**
	 * Update function scope.
	 *
	 * @param button the button
	 * @param scope the scope
	 */
	private void updateFunctionScope(JButton button, EFunctionScope scope){
		if(!enabled){
			setSelected(!selected);
			return;
		}
		
		FuncaoCasoDAOImpl funcaoCasoDao = new FuncaoCasoDAOImpl();
		int escopoAtual = this.funcaoCaso.getTipoEscopo();
		
		if((funcaoCaso.getTipoEscopo() & scope.getId()) == scope.getId()){
			funcaoCaso.setTipoEscopo(escopoAtual & ~scope.getId());
		}else{
			funcaoCaso.setTipoEscopo(escopoAtual | scope.getId());
		}
		
		funcaoCasoDao.update(funcaoCaso);
		
		escopoAtual = this.funcaoCaso.getTipoEscopo();
		
		paintFunctionScope(button, scope.getId());
		
		checkOK();
	}

	/**
	 * Inits the combo.
	 */
	private void initCombo() {
		for (EFunctionType type : EFunctionType.values()) {
			cBoxTipo.addItem(type);
		}
		
		if (funcaoCaso.getTipoFuncao() != null) {
			cBoxTipo.setSelectedIndex(funcaoCaso.getTipoFuncao());
		}
		
		cBoxTipo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED &&
						enabled) {
					if(!enabled){
						setSelected(!selected);
						return;
					}
					setBackground(((EFunctionType) e.getItem()).getColor());
					FuncaoCasoDAOImpl funcaoCasoDao = new FuncaoCasoDAOImpl();
					funcaoCaso.setTipoFuncao(((EFunctionType) e.getItem()).getId());
					funcaoCasoDao.update(funcaoCaso);
					checkOK();
				}
			}
		});
		
		for (EFunctionEffect type : EFunctionEffect.values()) {
			cBoxEfeito.addItem(type);
		}
		
		if (funcaoCaso.getTipoEfeito() != null) {
			cBoxEfeito.setSelectedIndex(funcaoCaso.getTipoEfeito());
		}
		
		cBoxEfeito.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED &&
						enabled) {
					if(!enabled){
						setSelected(!selected);
						return;
					}
					FuncaoCasoDAOImpl funcaoCasoDao = new FuncaoCasoDAOImpl();
					funcaoCaso.setTipoEfeito(((EFunctionEffect) e.getItem()).getId());
					funcaoCasoDao.update(funcaoCaso);
					checkOK();
				}
			}
		});
		
		for (EFunctionNecessity type : EFunctionNecessity.values()) {
			cBoxNecessidade.addItem(type);
		}
		
		if (funcaoCaso.getTipoNecessidade() != null) {
			cBoxNecessidade.setSelectedIndex(funcaoCaso.getTipoNecessidade());
		}
		
		cBoxNecessidade.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED &&
					enabled) {
					if(!enabled){
						setSelected(!selected);
						return;
					}
					FuncaoCasoDAOImpl funcaoCasoDao = new FuncaoCasoDAOImpl();
					funcaoCaso.setTipoNecessidade(((EFunctionNecessity) e.getItem()).getId());
					funcaoCasoDao.update(funcaoCaso);
					checkOK();
				}
			}
		});
	}
	
	/**
	 * Check ok.
	 */
	private void checkOK(){
		int efeito = cBoxEfeito.getSelectedIndex();
		int necessidade = cBoxNecessidade.getSelectedIndex();
		int tipo = cBoxTipo.getSelectedIndex();
		
		if(	(efeito != 0 &&
			necessidade != 0 &&
			tipo != 0 &&
			funcaoCaso.getTipoEscopo() != 0) ||
			funcaoCaso.getIdFuncaoPai() == 0){
			
			lblAlerta.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/ok_pq.png")));
			lblAlerta.setToolTipText("Função preenchida.");
		}else{
			lblAlerta.setIcon(new ImageIcon(JPanelFunctionBoxClassification.class.getResource("/imgassets/alert_pq.png")));
			lblAlerta.setToolTipText("Preencha todos os campos.");
		}
	}

	/**
	 * Sets the box enabled.
	 *
	 * @param enabled the new box enabled
	 */
	public void setBoxEnabled(boolean enabled) {
		this.enabled = enabled;
		this.cBoxTipo.setEnabled(enabled);
		this.cBoxEfeito.setEnabled(enabled);
		this.cBoxNecessidade.setEnabled(enabled);
		this.btnControl.setEnabled(enabled);
		this.btnInformation.setEnabled(enabled);
		this.btnMechanics.setEnabled(enabled);
		this.btnPower.setEnabled(enabled);
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public EFunctionType getType() {
		return (EFunctionType) cBoxTipo.getSelectedItem();
	}

	/**
	 * Gets the funcao caso.
	 *
	 * @return the funcao caso
	 */
	public FuncaoCaso getFuncaoCaso() {
		return funcaoCaso;
	}

	/**
	 * Checks if is selected.
	 *
	 * @return true, if is selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected){
			setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}else{
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		}
	}
}
