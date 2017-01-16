/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.util.io;
// TODO: Auto-generated Javadoc

/**
 * The Class LayoutFileFilter.
 */
public class LayoutFileFilter {

    /** The is default. */
    boolean isDefault;

    /** The description. */
    String description;
    
    /** The extension. */
    String extension;

    /**
     * Instantiates a new layout file filter.
     */
    public LayoutFileFilter() {

    }

    /**
     * Instantiates a new layout file filter.
     *
     * @param description the description
     * @param extension the extension
     * @param isDefault the is default
     */
    public LayoutFileFilter(String description, String extension,
            boolean isDefault) {
        this.description = description;
        this.extension = extension;
        this.isDefault = isDefault;
    }

    /**
     * Checks if is default.
     *
     * @return true, if is default
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Sets the default.
     *
     * @param isDefault the new default
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the extension.
     *
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the extension.
     *
     * @param extension the new extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

}