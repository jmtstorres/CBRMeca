package br.unb.ppmec.cbrmeca.view.util.io;
public class LayoutFileFilter {

    boolean isDefault;

    String description;
    String extension;

    public LayoutFileFilter() {

    }

    public LayoutFileFilter(String description, String extension,
            boolean isDefault) {
        this.description = description;
        this.extension = extension;
        this.isDefault = isDefault;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}