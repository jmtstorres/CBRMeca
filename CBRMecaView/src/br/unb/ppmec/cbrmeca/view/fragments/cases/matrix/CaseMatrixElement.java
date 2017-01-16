/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class CaseMatrixElement.
 */
public class CaseMatrixElement extends JLayeredPane{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2118724261987316216L;
	
	/** The Constant IMAGE_FOLDER. */
	public static final String IMAGE_FOLDER = "images" + File.separator + "concepts" + File.separator;
	
	/** The img path. */
	private String imgPath;
	
	/** The img name. */
	private String imgName;
	
	/** The concept id. */
	private Integer conceptID = 0;

	/** The lbl image. */
	private JLabel lblImage;
	
	/** The txt descrio. */
	private JTextPane txtDescrio;
	
	/** The text. */
	private String text;
	
	/** The cleared. */
	private boolean cleared = false;
	
	/** The close enabled. */
	private boolean closeEnabled = false;

	/** The lbl close. */
	private JLabel lblClose;

	/** The edit. */
	private boolean edit;

	/**
	 * Instantiates a new case matrix element.
	 */
	public CaseMatrixElement() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(150, 150));
		setMinimumSize(new Dimension(150, 150));
		setMaximumSize(new Dimension(150, 150));
		initialize();
	}

	/**
	 * Initialize.
	 */
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
	
	/**
	 * Sets the initial image.
	 *
	 * @param imageName the new initial image
	 */
	public void setInitialImage(String imageName){
		this.imgName = imageName;
		this.imgPath = System.getProperty("user.dir") + File.separator + imageName;
		this.lblImage.setIcon(new ImageIcon(this.imgPath));
		revalidate();
		repaint();
	}
	
	/**
	 * Sets the image.
	 *
	 * @param imageName the new image
	 */
	public void setImage(String imageName){
		this.imgName = imageName;
		this.imgPath = IMAGE_FOLDER + imageName;
		this.lblImage.setIcon(new ImageIcon(this.imgPath));
		this.lblImage.setText("");
		revalidate();
		repaint();
	}
	
	/**
	 * Sets the subtitle.
	 *
	 * @param text the new subtitle
	 */
	public void setSubtitle(String text){
		this.text = text;
		this.txtDescrio.setText("<center>" + text + "</center>");
	}

	/**
	 * Gets the img path.
	 *
	 * @return the img path
	 */
	public String getImgPath() {
		return imgPath;
	}
	
	/**
	 * Gets the img name.
	 *
	 * @return the img name
	 */
	public String getImgName() {
		return imgName;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return Jsoup.parse(this.text).text();
	}
	
	/**
	 * Sets the image click listener.
	 *
	 * @param listener the new image click listener
	 */
	public void setImageClickListener(MouseListener listener){
		this.lblImage.addMouseListener(listener);
	}
	
	/**
	 * Sets the lbl close click listener.
	 *
	 * @param listener the new lbl close click listener
	 */
	public void setLblCloseClickListener(MouseListener listener){
		this.lblClose.addMouseListener(listener);
		this.closeEnabled = true;
	}

	/**
	 * Gets the concept id.
	 *
	 * @return the concept id
	 */
	public Integer getConceptID() {
		return conceptID;
	}

	/**
	 * Checks if is close enabled.
	 *
	 * @return true, if is close enabled
	 */
	public boolean isCloseEnabled() {
		return closeEnabled;
	}

	/**
	 * Sets the close enabled.
	 *
	 * @param closeEnabled the new close enabled
	 */
	public void setCloseEnabled(boolean closeEnabled) {
		this.closeEnabled = closeEnabled;
	}

	/**
	 * Sets the concept id.
	 *
	 * @param conceptID the new concept id
	 */
	public void setConceptID(Integer conceptID) {
		this.conceptID = conceptID;
	}
	
	/**
	 * Sets the editable.
	 *
	 * @param edit the new editable
	 */
	public void setEditable(boolean edit){
		this.edit = edit;
		this.txtDescrio.setEditable(edit);
		this.lblClose.setVisible(edit);
	}

	/**
	 * Hover action.
	 *
	 * @param inside the inside
	 */
	public void hoverAction(boolean inside) {
		if(closeEnabled){
			this.lblClose.setVisible(inside);
		}
	}
}
