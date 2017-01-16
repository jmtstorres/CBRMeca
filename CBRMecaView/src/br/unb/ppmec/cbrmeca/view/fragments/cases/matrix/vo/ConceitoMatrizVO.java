/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo;

import br.unb.ppmec.cbrmeca.xml.vo.ImagemConceitoVO;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceitoMatrizVO.
 */
public class ConceitoMatrizVO {
	
	/** The str conceito. */
	private String strConceito;
	
	/** The img conceito. */
	private ImagemConceitoVO imgConceito;
	
	/** The desc conceito. */
	private String descConceito;

	/**
	 * Instantiates a new conceito matriz vo.
	 *
	 * @param strConceito the str conceito
	 * @param imgConceito the img conceito
	 * @param descConceito the desc conceito
	 */
	public ConceitoMatrizVO(String strConceito, ImagemConceitoVO imgConceito,
			String descConceito) {
		super();
		this.strConceito = strConceito;
		this.imgConceito = imgConceito;
		this.descConceito = descConceito;
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
	 * Gets the img conceito.
	 *
	 * @return the img conceito
	 */
	public ImagemConceitoVO getImgConceito() {
		return imgConceito;
	}

	/**
	 * Gets the desc conceito.
	 *
	 * @return the desc conceito
	 */
	public String getDescConceito() {
		return descConceito;
	}
}
