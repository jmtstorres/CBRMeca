/*
 * 
 */
package br.unb.ppmec.cbrmeca.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.ppmec.cbrmeca.control.CBREngine;
import br.unb.ppmec.cbrmeca.view.fragments.cases.CBRMecaCasesPanel;
import br.unb.ppmec.cbrmeca.view.fragments.cases.dialog.ConfirmationDialog;
import br.unb.ppmec.cbrmeca.view.fragments.configuration.CBRMecaConfigurationPanel;
import br.unb.ppmec.cbrmeca.view.fragments.dashboard.CBRMecaDashboardPanel;
import br.unb.ppmec.cbrmeca.view.navigation.CBRMecaMenu;
import br.unb.ppmec.cbrmeca.view.splash.SplashScreen;
import br.unb.ppmec.cbrmeca.view.util.io.PropertiesIO;

// TODO: Auto-generated Javadoc
/**
 * The Class CBRMecaMain.
 */
public class CBRMecaMain {

	/** The frame. */
	private JFrame frame;
	
	/** The active panel. */
	private JPanel activePanel;
	
	/** The panel main. */
	private JPanel panelMain;
	
	/** The panel. */
	private JPanel panel;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The uses model. */
	private boolean usesModel = false;
	
	/** The model path. */
	private String modelPath = "";
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen splash = new SplashScreen(3000);
					splash.showSplash();
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            @Override
					            public void run() {
					            	splash.setText("Iniciando motor...");
					            	CBRMecaMain window;
									try {
										window = new CBRMecaMain();
										splash.setText("Carregando modelos...");
										CBREngine.getInstance().initialize(window.usesModel(), window.getModelPath());
										splash.setText("Finalizado.");
										window.frame.setVisible(true);
										splash.dispose();
									} catch (ClassNotFoundException
											| InstantiationException
											| IllegalAccessException
											| UnsupportedLookAndFeelException e) {
										e.printStackTrace();
									}
					            }
					        }, 
					        1000
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Uses model.
	 *
	 * @return true, if successful
	 */
	public boolean usesModel() {
		return usesModel;
	}

	/**
	 * Gets the model path.
	 *
	 * @return the model path
	 */
	public String getModelPath() {
		return modelPath;
	}

	/**
	 * Load config.
	 */
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

		usesModel = prop.getProperty(PropertiesIO.MODEL_USED).equals("true");
		modelPath = prop.getProperty(PropertiesIO.MODEL_PATH);
	}

	/**
	 * Create the application.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 */
	public CBRMecaMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		loadConfig();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 */
	private void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager
		.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(0, 0));
		CBRMecaDashboardPanel panelCrud = new CBRMecaDashboardPanel();
		panelMain.add(panelCrud);
		frame.getContentPane().add(panelMain, BorderLayout.CENTER);
		activePanel = panelCrud;
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIconTextGap(0);
		lblNewLabel.setIcon(new ImageIcon(CBRMecaMain.class.getResource("/imgassets/ds_low.png")));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		CBRMecaMenu menu = new CBRMecaMenu();
		panel.add(menu);
		menu.setPreferredSize(new Dimension(181, 100));
		menu.setListener(CBRMecaMenu.LEFT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel instanceof CBRMecaDashboardPanel){
					return;
				}
				System.out.println("LEFT");
				
				if(isEditing()){
					ConfirmationDialog d = new ConfirmationDialog();
					d.setMessage("Existem dados não salvos no caso. Mudar de tela fará com que os mesmos sejam apagados. Deseja prosseguir?");
					d.setLocationRelativeTo(null);
					d.setVisible(true);
					if(!d.isConfirmed()){
						return;
					}
					menu.setSelected(CBRMecaMenu.LEFT);
				}
				
				panelMain.removeAll();
				CBRMecaDashboardPanel panelCrud = new CBRMecaDashboardPanel();
				panelMain.add(panelCrud);
				frame.revalidate();
				frame.repaint();
				activePanel = panelCrud;
			}
		});
		menu.setListener(CBRMecaMenu.MIDDLE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel instanceof CBRMecaCasesPanel){
					return;
				}
				System.out.println("MIDDLE");
				
				panelMain.removeAll();
				CBRMecaCasesPanel panelResult = new CBRMecaCasesPanel();
				panelMain.add(panelResult);
				frame.revalidate();
				frame.repaint();
				activePanel = panelResult;
			}
		});
		menu.setListener(CBRMecaMenu.RIGHT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel instanceof CBRMecaConfigurationPanel){
					return;
				}
				System.out.println("RIGHT");
				
				if(isEditing()){
					ConfirmationDialog d = new ConfirmationDialog();
					d.setMessage("Existem dados não salvos no caso. Mudar de tela fará com que os mesmos sejam apagados. Deseja prosseguir?");
					d.setLocationRelativeTo(null);
					d.setVisible(true);
					if(!d.isConfirmed()){
						return;
					}
					menu.setSelected(CBRMecaMenu.RIGHT);
				}
				
				panelMain.removeAll();
				CBRMecaConfigurationPanel panelProcessing = new CBRMecaConfigurationPanel();
				panelMain.add(panelProcessing);
				frame.revalidate();
				frame.repaint();
				activePanel = panelProcessing;
			}
		});
	}
	
	/**
	 * Checks if is editing.
	 *
	 * @return true, if is editing
	 */
	private boolean isEditing(){
		if(activePanel instanceof CBRMecaCasesPanel){
			return ((CBRMecaCasesPanel)activePanel).isEditing();
		}
		
		return false;
	}

}
