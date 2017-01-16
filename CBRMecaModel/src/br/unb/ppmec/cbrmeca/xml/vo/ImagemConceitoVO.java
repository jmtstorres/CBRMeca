/*
 * 
 */
package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class ImagemConceitoVO.
 */
@XmlRootElement(name = "imagemConceito")
public class ImagemConceitoVO {

	/** The dir imagem conceito. */
	private String dirImagemConceito;

	/**
	 * Instantiates a new imagem conceito vo.
	 */
	public ImagemConceitoVO() {
	}

	/**
	 * Gets the dir imagem conceito.
	 *
	 * @return the dir imagem conceito
	 */
	public String getDirImagemConceito() {
		return dirImagemConceito;
	}

	/**
	 * Sets the dir imagem conceito.
	 *
	 * @param dirImagemConceito the new dir imagem conceito
	 */
	public void setDirImagemConceito(String dirImagemConceito) {
		this.dirImagemConceito = dirImagemConceito;
	}
}
