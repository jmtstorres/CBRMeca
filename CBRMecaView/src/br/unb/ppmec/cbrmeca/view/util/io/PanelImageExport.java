package br.unb.ppmec.cbrmeca.view.util.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class PanelImageExport {
	protected static final String EXTENSION = ".png";

	protected static final String FORMAT_NAME = "png";

	protected static final LayoutFileFilter SAVE_AS_IMAGE = 
	        new LayoutFileFilter("PNG Image Format", EXTENSION, true);
	
	public static File chooseSaveFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    
	    int status = fileChooser.showSaveDialog(null);

	    if (status == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        return selectedFile;
	    }

	    return null;
	}
	
	public static void saveimage(BufferedImage image, File selectedFile){
		try {
            String fileName = selectedFile.getCanonicalPath();
            if (!fileName.endsWith(EXTENSION)) {
                selectedFile = new File(fileName + EXTENSION);
            }
            ImageIO.write(image, FORMAT_NAME, selectedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
