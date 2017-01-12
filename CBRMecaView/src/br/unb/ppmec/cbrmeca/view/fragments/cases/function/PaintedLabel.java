package br.unb.ppmec.cbrmeca.view.fragments.cases.function;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class PaintedLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4168133313424954502L;
	
	private BufferedImage image;
	
	public PaintedLabel(BufferedImage image) {
		this.image = image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public void paint(Graphics g) {
		if(image != null){
			int x = (getWidth() - image.getWidth())/2;
			g.drawImage(image, x, 0, null);
		}
		super.paint(g);
	}
}
