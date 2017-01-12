package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jsoup.Jsoup;
import javax.swing.JPanel;

public class CaseMatrixElement extends JLayeredPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2118724261987316216L;
	
	public static final String IMAGE_FOLDER = "images" + File.separator + "concepts" + File.separator;
	
	private String imgPath;
	private String imgName;
	private Integer conceptID = 0;

	private JLabel lblImage;
	private JTextPane txtDescrio;
	private String text;
	
	private boolean cleared = false;
	private boolean closeEnabled = false;

	private JLabel lblClose;

	private boolean edit;

	public CaseMatrixElement() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(150, 150));
		setMinimumSize(new Dimension(150, 150));
		setMaximumSize(new Dimension(150, 150));
		initialize();
	}

	private void initialize(){
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		lblImage = new JLabel("Clique para adicionar imagem");
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayer(lblImage, 1);
		lblImage.setLocation(1, 1);
		lblImage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImage.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImage.setSize(new Dimension(149, 103));
		lblImage.setPreferredSize(new Dimension(150, 120));
		lblImage.setMinimumSize(new Dimension(150, 120));
		lblImage.setMaximumSize(new Dimension(150, 120));
		lblImage.setBackground(Color.WHITE);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		setInitialImage("concept_null.png");
		setLayout(null);
		
		lblClose = new JLabel("");
		setLayer(lblClose, 2);
		lblClose.setVisible(false);
		lblClose.setSize(new Dimension(15, 15));
		lblClose.setPreferredSize(new Dimension(15, 15));
		lblClose.setMinimumSize(new Dimension(15, 15));
		lblClose.setMaximumSize(new Dimension(15, 15));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setHorizontalTextPosition(SwingConstants.CENTER);
		lblClose.setIcon(new ImageIcon(CaseMatrixElement.class.getResource("/imgassets/icon_close.png")));
		lblClose.setBounds(119, 1, 30, 30);
		add(lblClose);
		add(lblImage);
		
		txtDescrio = new JTextPane();
		setLayer(txtDescrio, 1);
		txtDescrio.setBounds(1, 104, 148, 45);
		txtDescrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!cleared && edit){
					cleared = true;
					setSubtitle("");
				}
			}
		});
		txtDescrio.setContentType("text/html");
		txtDescrio.setMargin(new Insets(3, 5, 5, 5));
		txtDescrio.setPreferredSize(new Dimension(150, 45));
		txtDescrio.setMinimumSize(new Dimension(150, 45));
		txtDescrio.setMaximumSize(new Dimension(150, 45));
		txtDescrio.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescrio.setBackground(Color.WHITE);
		txtDescrio.setText("<center>Descri\u00E7\u00E3o</center>");
		txtDescrio.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				text = txtDescrio.getText();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				text = txtDescrio.getText();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				text = txtDescrio.getText();
			}
		});
		add(txtDescrio);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(1, 1, 148, 148);
		add(panel);
	}
	
	public void setInitialImage(String imageName){
		this.imgName = imageName;
		this.imgPath = System.getProperty("user.dir") + File.separator + imageName;
		this.lblImage.setIcon(new ImageIcon(this.imgPath));
		revalidate();
		repaint();
	}
	
	public void setImage(String imageName){
		this.imgName = imageName;
		this.imgPath = IMAGE_FOLDER + imageName;
		this.lblImage.setIcon(new ImageIcon(this.imgPath));
		this.lblImage.setText("");
		revalidate();
		repaint();
	}
	
	public void setSubtitle(String text){
		this.text = text;
		this.txtDescrio.setText("<center>" + text + "</center>");
	}

	public String getImgPath() {
		return imgPath;
	}
	
	public String getImgName() {
		return imgName;
	}

	public String getText() {
		return Jsoup.parse(this.text).text();
	}
	
	public void setImageClickListener(MouseListener listener){
		this.lblImage.addMouseListener(listener);
	}
	
	public void setLblCloseClickListener(MouseListener listener){
		this.lblClose.addMouseListener(listener);
		this.closeEnabled = true;
	}

	public Integer getConceptID() {
		return conceptID;
	}

	public boolean isCloseEnabled() {
		return closeEnabled;
	}

	public void setCloseEnabled(boolean closeEnabled) {
		this.closeEnabled = closeEnabled;
	}

	public void setConceptID(Integer conceptID) {
		this.conceptID = conceptID;
	}
	
	public void setEditable(boolean edit){
		this.edit = edit;
		this.txtDescrio.setEditable(edit);
		this.lblClose.setVisible(edit);
	}

	public void hoverAction(boolean inside) {
		if(closeEnabled){
			this.lblClose.setVisible(inside);
		}
	}
}
