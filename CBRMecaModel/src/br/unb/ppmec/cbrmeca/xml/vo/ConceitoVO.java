/*
 * 
 */
package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceitoVO.
 */
@XmlRootElement(name = "conceito")
@XmlType(propOrder = { "strConceito", "imgConceito", "descConceito" })
public class ConceitoVO {

	/** The str conceito. */
	private String strConceito;
	
	/** The img conceito. */
	private ImagemConceitoVO imgConceito;
	
	/** The desc conceito. */
	private String descConceito;

	/**
	 * Instantiates a new conceito vo.
	 */
	public ConceitoVO() {
	}

	/**
	 * Gets the str conceito.
	 *
	 * @return the str conceito
	 */
	public String getStrConceito() {
		return strConceito;
	}

	/**
	 * Sets the str conceito.
	 *
	 * @param strConceito the new str conceito
	 */
	public void setStrConceito(String strConceito) {
		this.strConceito = strConceito;
	}

	/**
	 * Gets the img conceito.
	 *
	 * @return the img conceito
	 */
	public ImagemConceitoVO getImgConceito() {
		return imgConceito;
	}

	/**
	 * Sets the img conceito.
	 *
	 * @param imgConceito the new img conceito
	 */
	public void setImgConceito(ImagemConceitoVO imgConceito) {
		this.imgConceito = imgConceito;
	}

	/**
	 * Gets the desc conceito.
	 *
	 * @return the desc conceito
	 */
	public String getDescConceito() {
		return descConceito;
	}

	/**
	 * Sets the desc conceito.
	 *
	 * @param descConceito the new desc conceito
	 */
	public void setDescConceito(String descConceito) {
		this.descConceito = descConceito;
	}
}
