package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.ImagemConceito;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ImagemConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.xml.XMLImport;
import br.unb.ppmec.cbrmeca.view.components.imagecrop.CropImage;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.events.ConceptMatrixObserver;
import br.unb.ppmec.cbrmeca.view.notification.Notification;
import br.unb.ppmec.cbrmeca.view.notification.NotificationManager;

public class CaseMatrixRegisterElement extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5719755019820164327L;

	private JPanel contentPane;
	private CropImage cropImage = null;

	private final JFileChooser fc = new JFileChooser();

	private Date date;

	private String fileSaveName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CaseMatrixRegisterElement frame = new CaseMatrixRegisterElement();
					//frame.setVisible(true);
					XMLImport xml = new XMLImport();
					xml.readFromXML("G:\\[Estudo]\\Mestrado\\00 - Projeto\\workspace\\Casos Documentados\\P15001\\descritor.xml");
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaseMatrixRegisterElement(JFrame parent) {
		setUndecorated(true);
		setBackground(Color.WHITE);
		setVisible(true);
		setBounds(100, 100, 400, 300);
		setLocationRelativeTo(parent);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setSize(new Dimension(400, 300));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.setBounds(0, 0, 400, 300);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 150, 150 };
		gbl_contentPane.rowHeights = new int[] { 150, 60 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);
		
		date = new Date();
		fileSaveName = Long.toString(date.getTime()) + ".jpg";
		
		FileFilter filter = new FileNameExtensionFilter("PNG image",
				"png");
		FileFilter filter2 = new FileNameExtensionFilter("Jpg image",
				"jpg");
		FileFilter filter3 = new FileNameExtensionFilter("Jpeg image",
				"jpeg");

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(filter);
		fc.addChoosableFileFilter(filter2);
		fc.addChoosableFileFilter(filter3);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

		CaseMatrixElement element = new CaseMatrixElement();
		element.setEditable(true);
		element.setCloseEnabled(false);
		element.setImageClickListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int returnVal = fc
						.showOpenDialog(CaseMatrixRegisterElement.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fileFrom = fc.getSelectedFile();
					
					String current = System.getProperty("user.dir");
					
					if(cropImage != null){
						cropImage.dispose();
					}
					
					cropImage = new CropImage(fileFrom.getAbsolutePath());
					
					cropImage.setSaveOnClickListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							try {
								BufferedImage bimg = cropImage.getCropped();
								Image img = (Image)bimg;
								
								CropImage.saveImageFile(
										current + File.separator
										+ CaseMatrixElement.IMAGE_FOLDER
										+ fileSaveName,
										cropImage.resizeImage(img, 150, 100));
								
								element.setImage(fileSaveName);
								
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							cropImage.dispose();
						}
					});
				}
			}
		});
		GridBagConstraints gbc_element = new GridBagConstraints();
		gbc_element.insets = new Insets(0, 0, 5, 0);
		gbc_element.gridwidth = 2;
		gbc_element.gridx = 0;
		gbc_element.gridy = 0;
		contentPane.add(element, gbc_element);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblSalvar = new JLabel("Salvar");
		lblSalvar.setIcon(new ImageIcon(CaseMatrixRegisterElement.class
				.getResource("/imgassets/ds.png")));
		panel.add(lblSalvar, BorderLayout.EAST);
		lblSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				if(element.getImgName().contains("concept_null.png")){
					try {
						saveDefaultImage(element);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				ImagemConceitoDAOImpl imagemDAO = new ImagemConceitoDAOImpl();
				ImagemConceito imagem = new ImagemConceito();
				imagem.setDirImagemConceito(element.getImgName());
				imagemDAO.save(imagem);

				ConceitoDAOImpl conceitoDAO = new ConceitoDAOImpl();
				Conceito conceito = new Conceito();
				conceito.setImgConceito(imagem.getIdImagem());
				conceito.setStrConceito(element.getText());
				conceito.setDescConceito(element.getText());

				try{
					conceitoDAO.save(conceito);
				}catch(Exception ex){
					NotificationManager.getInstance().notifyUser("Conceito", "O conceito já existe.", Notification.FAIL);
					conceito.setIdConceito(0);
				}

				ConceptMatrixObserver.getInstance().sendConceptSelectedEvent(conceito);
				
				if(cropImage != null){
					cropImage.dispose();
				}
				dispose();
			}
		});
		lblSalvar.setPreferredSize(new Dimension(200, 50));
		lblSalvar.setMinimumSize(new Dimension(200, 50));
		lblSalvar.setMaximumSize(new Dimension(200, 50));
		lblSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalvar.setForeground(Color.BLACK);
		lblSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSalvar.setBorder(null);
		lblSalvar.setBackground(Color.GREEN);

		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setIcon(new ImageIcon(CaseMatrixRegisterElement.class
				.getResource("/imgassets/ds.png")));
		panel.add(lblCancelar, BorderLayout.WEST);
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(cropImage != null){
					cropImage.dispose();
				}
				dispose();
			}
		});
		lblCancelar.setPreferredSize(new Dimension(200, 50));
		lblCancelar.setMinimumSize(new Dimension(200, 50));
		lblCancelar.setMaximumSize(new Dimension(200, 50));
		lblCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setForeground(Color.BLACK);
		lblCancelar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCancelar.setBorder(null);
		lblCancelar.setBackground(Color.GREEN);
	}
	
	public void saveDefaultImage(CaseMatrixElement element) throws IOException, Exception{
		Date date = new Date();
		String fileSaveName = Long.toString(date.getTime()) + ".jpg";
		String current = System.getProperty("user.dir");
		
		File img = new File(element.getImgPath());
		
		CropImage.saveImageFile(
				current + File.separator
				+ CaseMatrixElement.IMAGE_FOLDER
				+ fileSaveName,
				ImageIO.read(img));
		
		element.setImage(fileSaveName);
	}
}
