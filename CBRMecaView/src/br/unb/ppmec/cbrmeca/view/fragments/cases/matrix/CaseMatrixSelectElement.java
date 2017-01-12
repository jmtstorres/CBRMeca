package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.ImagemConceito;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ImagemConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.ConceptAddedEvent;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.ConceptMatrixObserver;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.MatrixConceptAddedListener;

public class CaseMatrixSelectElement extends JFrame implements
		MatrixConceptAddedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5155983696059444619L;

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel_concepts;

	private ScrollPane panel_scroll;
	private CaseMatrixRegisterElement cmre = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaseMatrixSelectElement frame = new CaseMatrixSelectElement(
							null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaseMatrixSelectElement(JFrame parent) {
		ConceptMatrixObserver.getInstance().addConceptAddedListener(this);

		setBackground(Color.WHITE);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(parent);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 10, 150, 500, 0, 10, 0 };
		gbl_panel.rowHeights = new int[] { 17, 40, 50, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_3 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		lblNewLabel.setIcon(new ImageIcon(CaseMatrixSelectElement.class
				.getResource("/imgassets/icon_search.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(171, 173, 179), 2));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5, 10, 5, 10);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!textField.getText().isEmpty()) {

					ConceitoDAOImpl conceitoDao = new ConceitoDAOImpl();
					List<Conceito> lista = conceitoDao.getStartedWith(
							textField.getText(), 20);

					panel_concepts.removeAll();
					for (Conceito conceito : lista) {
						ImagemConceitoDAOImpl imgDao = new ImagemConceitoDAOImpl();
						ImagemConceito img = imgDao.get(conceito
								.getImgConceito());

						CaseMatrixElement element = new CaseMatrixElement();
						element.setSubtitle(conceito.getStrConceito());
						element.setConceptID(conceito.getIdConceito());
						element.setEditable(false);

						if (img != null && img.getDirImagemConceito() != null) {
							String userdir = img.getDirImagemConceito();
							System.out.println(userdir);
							element.setImage(userdir);
						}

						element.setImageClickListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								System.out.println("Conceito Selecionado: "
										+ conceito.getIdConceito());
								ConceptMatrixObserver.getInstance()
										.sendConceptSelectedEvent(conceito);
								dispose();
							}
						});

						panel_concepts.add(element);
					}

					panel_concepts.setPreferredSize(new Dimension(750, ((lista
							.size() / 5) + 1) * 150));
					panel_concepts.revalidate();
					panel_concepts.repaint();

					panel_scroll.revalidate();
					panel_scroll.repaint();
				} else {
					panel_concepts.removeAll();
					panel_concepts
							.setPreferredSize(new Dimension(750, 2 * 150));
					panel_concepts.revalidate();
					panel_concepts.repaint();
					panel_scroll.revalidate();
					panel_scroll.repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		JButton btnNewButton = new JButton("Fechar");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Conceito dummy = new Conceito(0);
				ConceptMatrixObserver.getInstance().sendConceptSelectedEvent(dummy);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		btnNewButton.setOpaque(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setIconTextGap(0);
		btnNewButton.setIcon(new ImageIcon(CaseMatrixSelectElement.class
				.getResource("/imgassets/icon_close.png")));
		btnNewButton.setFocusTraversalKeysEnabled(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(
				"Clique para adicionar o conceito buscado");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNovoConceito = new JButton("Novo conceito");
		btnNovoConceito.setFocusPainted(false);
		btnNovoConceito.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				cmre = new CaseMatrixRegisterElement(
						CaseMatrixSelectElement.this);
				cmre.setVisible(true);
			}
		});
		btnNovoConceito.setHorizontalAlignment(SwingConstants.LEFT);
		btnNovoConceito.setIcon(new ImageIcon(CaseMatrixSelectElement.class
				.getResource("/imgassets/icon_add.png")));
		btnNovoConceito.setOpaque(false);
		btnNovoConceito.setMargin(new Insets(0, 0, 0, 0));
		btnNovoConceito.setIconTextGap(0);
		btnNovoConceito.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNovoConceito.setFocusTraversalKeysEnabled(false);
		btnNovoConceito.setContentAreaFilled(false);
		btnNovoConceito.setBorderPainted(false);
		btnNovoConceito.setBorder(null);
		GridBagConstraints gbc_btnNovoConceito = new GridBagConstraints();
		gbc_btnNovoConceito.fill = GridBagConstraints.VERTICAL;
		gbc_btnNovoConceito.anchor = GridBagConstraints.WEST;
		gbc_btnNovoConceito.insets = new Insets(0, 0, 5, 5);
		gbc_btnNovoConceito.gridx = 3;
		gbc_btnNovoConceito.gridy = 2;
		panel.add(btnNovoConceito, gbc_btnNovoConceito);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(CaseMatrixSelectElement.class
				.getResource("/imgassets/ds_low.png")));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		panel_scroll = new ScrollPane();
		panel_scroll.setBackground(Color.WHITE);
		contentPane.add(panel_scroll, BorderLayout.CENTER);

		panel_concepts = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_concepts.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_concepts.setSize(new Dimension(790, 0));
		panel_concepts.setPreferredSize(new Dimension(785, 10));
		panel_concepts.setMinimumSize(new Dimension(800, 10));
		panel_concepts.setMaximumSize(new Dimension(800, 32767));
		panel_concepts.setBorder(null);
		panel_concepts.setBackground(Color.WHITE);
		panel_scroll.add(panel_concepts, BorderLayout.CENTER);
	}

	@Override
	public void conceptAdded(ConceptAddedEvent event) {
		System.out.println("Recebeu o evento");
		dispose();
	}
}
