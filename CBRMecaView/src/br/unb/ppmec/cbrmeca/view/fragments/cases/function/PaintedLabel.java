/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.function;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class PaintedLabel.
 */
public class PaintedLabel extends JLabel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4168133313424954502L;
	
	/** The image. */
	private BufferedImage image;
	
	/**
	 * Instantiates a new painted label.
	 *
	 * @param image the image
	 */
	public PaintedLabel(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if(image != null){
			int x = (getWidth() - image.getWidth())/2;
			g.drawImage(image, x, 0, null);
		}
		super.paint(g);
	}
}
