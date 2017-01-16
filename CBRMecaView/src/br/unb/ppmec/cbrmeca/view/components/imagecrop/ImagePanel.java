/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.components.imagecrop;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ImagePanel.
 */
class ImagePanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The img. */
	private Image img;
	
	/** The size. */
	private Dimension size;

	/**
	 * Instantiates a new image panel.
	 */
	public ImagePanel() {

	}

	/**
	 * Instantiates a new image panel.
	 *
	 * @param img the img
	 */
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	/**
	 * Instantiates a new image panel.
	 *
	 * @param img the img
	 */
	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		this.size = size;
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return getScaled();
	}

	/**
	 * Gets the scaled.
	 *
	 * @return the scaled
	 */
	private Image getScaled() {
		return this.img;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#getSize()
	 */
	public Dimension getSize() {
		return size;
	}
}
