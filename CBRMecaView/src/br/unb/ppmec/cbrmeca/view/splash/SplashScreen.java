package br.unb.ppmec.cbrmeca.view.splash;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class SplashScreen extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8372592507563345808L;
	
	private int duration;

	private JLabel lblNewLabel_1;
    
    public SplashScreen(int d) {
    	setUndecorated(true);
    	setBackground(new Color(0, 255, 0, 0));
        
        setBounds(0,0,850,450);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        lblNewLabel_1 = new JLabel("Inicializando...");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(Color.DARK_GRAY);
        lblNewLabel_1.setBorder(new LineBorder(Color.WHITE, 5, true));
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBounds(179, 368, 496, 34);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 850, 450);
    	lblNewLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/imgassets/splash.png")));
    	lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	getContentPane().add(lblNewLabel);
    	
        duration = d;
    }
    
    public void setText(String text){
    	lblNewLabel_1.setText(text);
    	revalidate();
        repaint();
    	try {Thread.sleep(500);} catch (InterruptedException e) {}
    }
    
    public void showSplash() {        
        setVisible(true);
        revalidate();
        repaint();
    }
    
    public void showSplashAndExit() {        
        showSplash();
        try {Thread.sleep(duration);} catch (InterruptedException e) {}
        dispose();
    }
    
    public static void main(String[] args) {        
        SplashScreen splash = new SplashScreen(3000);
        splash.showSplashAndExit();        
    }
}