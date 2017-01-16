/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;

// TODO: Auto-generated Javadoc
/**
 * The Class CBRSearchCasesDialog.
 */
public class CBRSearchCasesDialog extends JDialog {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CBRSearchCasesDialog diag = new CBRSearchCasesDialog();
					diag.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7106078291303068980L;
	
	/** The text field. */
	private JTextField textField;
	
	/** The list. */
	private JList<Caso> list;
	
	/** The caso. */
	protected Caso caso;

	/**
	 * Create the panel.
	 */
	public CBRSearchCasesDialog() {
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setSize(new Dimension(800, 600));
		setUndecorated(true);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Selecione o caso");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setSize(new Dimension(200, 50));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(CBRSearchCasesDialog.class.getResource("/imgassets/ds.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setPreferredSize(new Dimension(200, 50));
		lblNewLabel.setMinimumSize(new Dimension(200, 50));
		lblNewLabel.setMaximumSize(new Dimension(100, 50));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{144, 400, 0};
		gbl_panel_1.rowHeights = new int[]{40, 500, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Termos de busca");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
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
		});
		textField.setBorder(new LineBorder(Color.BLACK, 2));
		textField.setMinimumSize(new Dimension(100, 30));
		textField.setMaximumSize(new Dimension(100, 30));
		textField.setSize(new Dimension(100, 30));
		textField.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		list = new JList<Caso>();
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//TODO - adicionar isto a tela de cadastro
				Caso caso = list.getSelectedValue();
				System.out.println(caso.getStrTitulo());
				System.out.println(caso.getStrDescricao());
			}
		});
		list.setBorder(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 200, 0};
		gbl_panel.rowHeights = new int[]{50, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSelecionar = new JLabel("Selecionar");
		lblSelecionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				caso = list.getSelectedValue();
				dispose();
			}
		});
		lblSelecionar.setIcon(new ImageIcon(CBRSearchCasesDialog.class.getResource("/imgassets/ds.png")));
		lblSelecionar.setSize(new Dimension(200, 50));
		lblSelecionar.setPreferredSize(new Dimension(200, 50));
		lblSelecionar.setMinimumSize(new Dimension(200, 50));
		lblSelecionar.setMaximumSize(new Dimension(100, 50));
		lblSelecionar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSelecionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecionar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSelecionar.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSelecionar.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblSelecionar = new GridBagConstraints();
		gbc_lblSelecionar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelecionar.gridx = 0;
		gbc_lblSelecionar.gridy = 0;
		panel.add(lblSelecionar, gbc_lblSelecionar);
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				caso = null;
				dispose();
			}
		});
		lblCancelar.setIcon(new ImageIcon(CBRSearchCasesDialog.class.getResource("/imgassets/ds.png")));
		lblCancelar.setSize(new Dimension(200, 50));
		lblCancelar.setPreferredSize(new Dimension(200, 50));
		lblCancelar.setMinimumSize(new Dimension(200, 50));
		lblCancelar.setMaximumSize(new Dimension(100, 50));
		lblCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCancelar.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCancelar.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblCancelar = new GridBagConstraints();
		gbc_lblCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCancelar.anchor = GridBagConstraints.NORTH;
		gbc_lblCancelar.gridx = 1;
		gbc_lblCancelar.gridy = 0;
		panel.add(lblCancelar, gbc_lblCancelar);
		
		fillList();
	}
	
	/**
	 * Gets the elementar level functions.
	 *
	 * @param fc the fc
	 * @return the elementar level functions
	 */
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

	/**
	 * Fill list.
	 */
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

	/**
	 * Gets the caso.
	 *
	 * @return the caso
	 */
	public Caso getCaso() {
		return caso;
	}

}
