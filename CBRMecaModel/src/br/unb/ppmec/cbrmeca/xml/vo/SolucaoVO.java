/*
 * 
 */
package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;


// TODO: Auto-generated Javadoc
/**
 * The Class SolucaoVO.
 */
@XmlRootElement(name = "solucao")
public class SolucaoVO {

	/** The conceito. */
	private ConceitoVO conceito;

	/**
	 * Instantiates a new solucao vo.
	 */
	public SolucaoVO() {
	}
	
	/**
	 * Gets the conceito.
	 *
	 * @return the conceito
	 */
	public ConceitoVO getConceito() {
		return conceito;
	}

	/**
	 * Sets the conceito.
	 *
	 * @param conceito the new conceito
	 */
	public void setConceito(ConceitoVO conceito) {
		this.conceito = conceito;
	}
}
