/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.util.io;
import java.io.File;

import javax.swing.filechooser.FileFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtensionFileFilter.
 */
public class ExtensionFileFilter extends FileFilter {

    /** The filter. */
    protected LayoutFileFilter filter;

    /** The description. */
    protected String description;
    
    /** The extensions. */
    protected String[] extensions;

    /**
     * Instantiates a new extension file filter.
     *
     * @param filter the filter
     */
    public ExtensionFileFilter(LayoutFileFilter filter) {
        this(filter.getDescription(), filter.getExtension());
        this.filter = filter;
    }

    /**
     * Instantiates a new extension file filter.
     *
     * @param description the description
     * @param extension the extension
     */
    public ExtensionFileFilter(String description, String extension) {
        this(description, new String[] {extension});
    }

    /**
     * Instantiates a new extension file filter.
     *
     * @param description the description
     * @param extensions the extensions
     */
    public ExtensionFileFilter(String description, String[] extensions) {
        if ((description == null) || (description.equals(""))) {
            this.description = extensions[0] + " {" + extensions.length + "}";
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    /**
     * To lower.
     *
     * @param extensions the extensions
     */
    private void toLower(String[] extensions) {
        for (int i = 0, n = extensions.length; i < n; i++) {
            extensions[i].toLowerCase();
        }
    }

    /* (non-Javadoc)
     * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0, n = extensions.length; i < n; i++) {
                String extension = extensions[i];
                if (path.endsWith(extension)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Gets the layout file filter.
     *
     * @return the layout file filter
     */
    public LayoutFileFilter getLayoutFileFilter() {
        return filter;
    }

}