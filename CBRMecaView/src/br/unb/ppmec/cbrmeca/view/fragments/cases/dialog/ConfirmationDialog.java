/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfirmationDialog.
 */
public class ConfirmationDialog extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7003082256689178762L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The confirmed. */
	private boolean confirmed = false;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ConfirmationDialog dialog = new ConfirmationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfirmationDialog() {
		setModal(true);
		setUndecorated(true);
		setBounds(100, 100, 320, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblNewLabel = new JLabel("Salvar caso na base para uso posterior?");
			lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{47, 65, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						confirmed = true;
						dispose();
					}
				});
				okButton.setBorder(new LineBorder(new Color(0, 0, 0)));
				okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				okButton.setHorizontalTextPosition(SwingConstants.CENTER);
				okButton.setIcon(new ImageIcon(ConfirmationDialog.class.getResource("/imgassets/ds.png")));
				okButton.setPreferredSize(new Dimension(200, 60));
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.BOTH;
				gbc_okButton.gridx = 0;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						confirmed = false;
						dispose();
					}
				});
				cancelButton.setBorder(new LineBorder(new Color(0, 0, 0)));
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
				cancelButton.setIcon(new ImageIcon(ConfirmationDialog.class.getResource("/imgassets/ds.png")));
				cancelButton.setPreferredSize(new Dimension(200, 60));
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.BOTH;
				gbc_cancelButton.gridx = 1;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message){
		this.lblNewLabel.setText("<html>" + message + "</html>");
	}

	/**
	 * Checks if is confirmed.
	 *
	 * @return true, if is confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * Sets the confirmed.
	 *
	 * @param confirmed the new confirmed
	 */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
}
