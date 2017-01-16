/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.util.io;

import java.io.File;

import javax.swing.JFileChooser;

// TODO: Auto-generated Javadoc
/**
 * The Class CaseIO.
 */
public class CaseIO {
	
	/** The Constant EXTENSION. */
	protected static final String EXTENSION = ".cbm";

	/** The Constant FORMAT_NAME. */
	protected static final String FORMAT_NAME = "cbm";

	/** The Constant SAVE_AS_CASE. */
	protected static final LayoutFileFilter SAVE_AS_CASE = 
	        new LayoutFileFilter("CBR Case Format", EXTENSION, true);
	
	/**
	 * Choose save file.
	 *
	 * @return the file
	 */
	public static File chooseSaveFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    ExtensionFileFilter pFilter = new ExtensionFileFilter(SAVE_AS_CASE);
	    fileChooser.setFileFilter(pFilter);
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    
	    int status = fileChooser.showSaveDialog(null);

	    if (status == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        return selectedFile;
	    }

	    return null;
	}
}
