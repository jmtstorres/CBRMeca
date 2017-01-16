/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.components.imagecrop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class CropImage.
 */
public class CropImage extends JFrame implements MouseListener,	MouseMotionListener {
	
	/** The file path. */
	private String filePath;
	
	/** The listener save. */
	private MouseListener listenerSave;
	
	/**
	 * Instantiates a new crop image.
	 *
	 * @param filePath the file path
	 */
	public CropImage(String filePath) {
		setUndecorated(true);
		this.filePath = filePath;
		initialize();
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c4. */
	int drag_status = 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0;
	
	/** The label. */
	private JLabel label;

	/** The panel. */
	private JPanel panel;

	/** The im. */
	private ImagePanel im;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		new CropImage("C:\\Users\\Jo�oMarcelo\\Desktop\\Untitled.png");
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		System.out.println(this.filePath);
		im = new ImagePanel(this.filePath);
		im.setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize((int)im.getSize().getWidth(), (int)(im.getSize().getHeight() + 50));
		setExtendedState( getExtendedState()|JFrame.MAXIMIZED_BOTH );
		setAlwaysOnTop(true);
		getContentPane().add(im);
		
		panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{150, 150, 0};
		gbl_panel.rowHeights = new int[]{50, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		label = new JLabel("Salvar");
		label.addMouseListener(listenerSave);
		label.setIcon(new ImageIcon(CropImage.class.getResource("/imgassets/ds.png")));
		label.setPreferredSize(new Dimension(200, 50));
		label.setMinimumSize(new Dimension(200, 50));
		label.setMaximumSize(new Dimension(200, 50));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBorder(null);
		label.setBackground(Color.GREEN);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel label_1 = new JLabel("Cancelar");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(CropImage.class.getResource("/imgassets/ds.png")));
		label_1.setPreferredSize(new Dimension(200, 50));
		label_1.setMinimumSize(new Dimension(200, 50));
		label_1.setMaximumSize(new Dimension(200, 50));
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBorder(null);
		label_1.setBackground(Color.GREEN);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);
		setVisible(true);
		getContentPane().addMouseListener(this);
		getContentPane().addMouseMotionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Resize image.
	 *
	 * @param image the image
	 * @param width the width
	 * @param height the height
	 * @return the image
	 */
	public Image resizeImage(Image image, int width, int height) {
		if (image.getHeight(null) > image.getWidth(null)) {
			int h = (150*image.getHeight(null))/image.getWidth(null);
			System.out.println("1 - w: " + width + "||| h: " + h);
			return image.getScaledInstance(width, h, Image.SCALE_SMOOTH);
		} else {
			int w = (100*image.getWidth(null))/image.getHeight(null);
			System.out.println("2 - w: " + w + "||| h: " + height);
			return image.getScaledInstance(w, height, Image.SCALE_SMOOTH);
		}
	}

	/**
	 * Gets the cropped.
	 *
	 * @return the cropped
	 * @throws Exception the exception
	 */
	public BufferedImage getCropped() throws Exception {
		int w = c1 > c3 ? c1 - c3 : c3 - c1;
		int h = c2 > c4 ? c2 - c4 : c4 - c2;
		
		System.out.println("W " + w + "| H " + h);
		
		if(	c1 == 0 &&
			c2 == 0 &&
			c3 == 0 &&
			c4 == 0){
			
			//Robot robot = new Robot();
			System.out.println("W: " + im.getWidth() + "| H: " + im.getHeight());
			/*
			return robot.createScreenCapture(new Rectangle(	1, 
															1, 
															im.getImage().getWidth(null) - 2, 
															im.getImage().getHeight(null) - 2));
															*/
			return toBufferedImage(	im.getImage(), 
									0, 
									0, 
									im.getImage().getWidth(null), 
									im.getImage().getHeight(null));
		}
		
		//Robot robot = new Robot();
		//return robot.createScreenCapture(new Rectangle(c1 + 1, c2 + 1, w - 2, h - 2));
		return toBufferedImage(	im.getImage(), 
								(c1 > c3 ? c3 : c1), 
								(c2 > c4 ? c4 : c2), 
								w, 
								h);
	}
	
	/**
	 * To buffered image.
	 *
	 * @param img the img
	 * @param x the x
	 * @param y the y
	 * @param w the w
	 * @param h the h
	 * @return the buffered image
	 */
	public static BufferedImage toBufferedImage(Image img, int x, int y, int w, int h)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    return bimage.getSubimage(x, y, w, h);
	}

	/**
	 * Save image file.
	 *
	 * @param path the path
	 * @param img the img
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void saveImageFile(String path, BufferedImage img) throws IOException{
		File save_path = new File(path);
		ImageIO.write(img, "JPG", save_path);
		System.out.println("Cropped image saved successfully.");
	}
	
	/**
	 * Save image file.
	 *
	 * @param path the path
	 * @param img the img
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void saveImageFile(String path, Image img) throws IOException{
		File save_path = new File(path);
		ImageIO.write(toBufferedImage(img, 0, 0, img.getWidth(null), img.getHeight(null)), "JPG", save_path);
		System.out.println("Cropped image saved successfully.");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		repaint();
		if(outOfBounds(arg0)){
			return;
		}
		
		c1 = arg0.getX();
		c2 = arg0.getY();
	}
	
	/**
	 * Sets the save on click listener.
	 *
	 * @param listener the new save on click listener
	 */
	public void setSaveOnClickListener(MouseAdapter listener){
		this.listenerSave = listener;
		label.addMouseListener(listenerSave);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		repaint();
		
		if(outOfBounds(arg0)){
			return;
		}
		
		if (drag_status == 1) {
			c3 = arg0.getX();
			c4 = arg0.getY();
			
			if(c3 > im.getImage().getWidth(null)){
				c3 = im.getImage().getWidth(null);
			}
			
			if(c4 > im.getImage().getHeight(null)){
				c4 = im.getImage().getHeight(null);
			}
			
			try {
				getCropped();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		repaint();
		drag_status = 1;
		if(outOfBounds(arg0)){
			return;
		}
		
		c3 = arg0.getX();
		c4 = arg0.getY();
		
		if(c3 > im.getWidth()){
			c3 = im.getWidth();
		}
		
		if(c4 > im.getHeight()){
			c4 = im.getHeight();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		//repaint();
	}
	
	/**
	 * Out of bounds.
	 *
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	private boolean outOfBounds(MouseEvent arg0){
		int mouseX = arg0.getX();
		int mouseY = arg0.getY();
		
		Rectangle rec = panel.getBounds();
		
		if (( mouseX >= rec.getX() && mouseX <= (rec.getX() + rec.getWidth())) && 
			( mouseY >= rec.getY() && mouseY <= (rec.getY() + rec.getHeight()))){
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(g);
		int w = c1 > c3 ? c1 - c3 : c3 - c1;
		int h = c2 > c4 ? c2 - c4 : c4 - c2;
		
		g.drawRect((c1 > c3 ? c3 : c1), (c2 > c4 ? c4 : c2), w, h);
	}
}