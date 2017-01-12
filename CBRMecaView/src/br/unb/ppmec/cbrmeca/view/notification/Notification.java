package br.unb.ppmec.cbrmeca.view.notification;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class Notification extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2817350343105968579L;
	private JPanel contentPane;
	private JTextPane lblMessage;
	private JLabel lblTitle;
	
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private JLabel lblIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notification frame = new Notification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setStrings(String title, String message, int type){
		lblMessage.setText(message);
		lblTitle.setText(title);
		if(type == FAIL){
			ImageIcon myIcon2 = new ImageIcon(Notification.class.getResource("/imgassets/fail.png"));
			Image img = myIcon2.getImage();
			Image newimg = img.getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			lblIcon.setIcon(newIcon);
		}else{
			lblIcon.setIcon(new ImageIcon(Notification.class.getResource("/imgassets/success.png")));
		}
	}

	/**
	 * Create the frame.
	 */
	public Notification() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 150);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIcon = new JLabel("");
		lblIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(Notification.class.getResource("/imgassets/fail.png")));
		lblIcon.setBounds(10, 36, 100, 103);
		contentPane.add(lblIcon);
		
		lblMessage = new JTextPane();
		lblMessage.setEditable(false);
		lblMessage.setOpaque(false);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessage.setBounds(114, 36, 176, 103);
		contentPane.add(lblMessage);
		
		lblTitle = new JLabel("Postado!");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(10, 11, 280, 14);
		contentPane.add(lblTitle);
	}
}
