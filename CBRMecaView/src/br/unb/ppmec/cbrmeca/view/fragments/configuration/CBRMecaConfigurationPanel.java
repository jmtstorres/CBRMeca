package br.unb.ppmec.cbrmeca.view.fragments.configuration;

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
import java.io.File;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.unb.ppmec.cbrmeca.view.util.io.PropertiesIO;

public class CBRMecaConfigurationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8562037625706065684L;
	private JTextField textFieldPathModel;

	private JCheckBox chckbxNewCheckBox;
	private JLabel lblModeloGramatical;
	private JButton btnNewButton;
	/**
	 * @wbp.nonvisual location=-7,-31
	 */
	private final JTextField textField = new JTextField();

	/**
	 * Create the panel.
	 */
	public CBRMecaConfigurationPanel() {
		textField.setText((String) null);
		textField.setPreferredSize(new Dimension(20, 20));
		textField.setEnabled(false);
		textField.setColumns(10);
		setBorder(null);
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 80, 0, 0, 80, 0 };
		gridBagLayout.rowHeights = new int[] { 50, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				textFieldPathModel.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
				btnNewButton.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
				updateConfig();
			}
		});
		
		lblModeloGramatical = new JLabel("Modelo gramatical");
		lblModeloGramatical.setIcon(new ImageIcon(CBRMecaConfigurationPanel.class.getResource("/imgassets/ds.png")));
		lblModeloGramatical.setPreferredSize(new Dimension(150, 50));
		lblModeloGramatical.setMinimumSize(new Dimension(150, 50));
		lblModeloGramatical.setMaximumSize(new Dimension(150, 50));
		lblModeloGramatical.setHorizontalTextPosition(SwingConstants.CENTER);
		lblModeloGramatical.setHorizontalAlignment(SwingConstants.CENTER);
		lblModeloGramatical.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblModeloGramatical = new GridBagConstraints();
		gbc_lblModeloGramatical.insets = new Insets(0, 0, 5, 0);
		gbc_lblModeloGramatical.fill = GridBagConstraints.BOTH;
		gbc_lblModeloGramatical.gridwidth = 4;
		gbc_lblModeloGramatical.gridx = 0;
		gbc_lblModeloGramatical.gridy = 0;
		add(lblModeloGramatical, gbc_lblModeloGramatical);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		JLabel lblNewLabel = new JLabel("Usar Modelo Gramatical");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"Caminho do modelo (formato word2vec)");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		btnNewButton = new JButton("Selecionar Arquivo:");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					textFieldPathModel.setText(selectedFile.getAbsolutePath());
					updateConfig();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		add(btnNewButton, gbc_btnNewButton);

		textFieldPathModel = new JTextField();
		textFieldPathModel.setPreferredSize(new Dimension(20, 20));
		textFieldPathModel.setEnabled(false);
		GridBagConstraints gbc_textFieldPathModel = new GridBagConstraints();
		gbc_textFieldPathModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPathModel.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldPathModel.gridx = 2;
		gbc_textFieldPathModel.gridy = 3;
		add(textFieldPathModel, gbc_textFieldPathModel);
		textFieldPathModel.setColumns(10);

		loadConfig();
	}

	protected void loadConfig() {
		Properties prop = new Properties();

		File f = new File("app.properties");
		if (!f.exists()) {
			prop.setProperty(PropertiesIO.MODEL_USED, "false");
			prop.setProperty(PropertiesIO.MODEL_PATH, "model\\trained_v0.dat");
			PropertiesIO.storeProps(prop, f);
		} else {
			PropertiesIO.loadProps(prop, f);
		}

		if (prop.get(PropertiesIO.MODEL_USED).equals("false")) {
			chckbxNewCheckBox.setSelected(false);
		} else {
			chckbxNewCheckBox.setSelected(true);
		}

		textFieldPathModel.setText(prop.getProperty(PropertiesIO.MODEL_PATH));
	}

	protected void updateConfig() {
		Properties prop = new Properties();

		File f = new File("app.properties");
		if (!f.exists()) {
			prop.setProperty(PropertiesIO.MODEL_USED, "false");
			prop.setProperty(PropertiesIO.MODEL_PATH, "model\\trained_v0.dat");
			PropertiesIO.storeProps(prop, f);
		} else {
			PropertiesIO.loadProps(prop, f);
		}

		String propStr = chckbxNewCheckBox.isSelected() ? "true" : "false";
		System.out.println("PropStr: " + propStr);
		prop.setProperty(PropertiesIO.MODEL_USED, propStr);
		prop.setProperty(PropertiesIO.MODEL_PATH, textFieldPathModel.getText());
		PropertiesIO.storeProps(prop, f);
	}
}
