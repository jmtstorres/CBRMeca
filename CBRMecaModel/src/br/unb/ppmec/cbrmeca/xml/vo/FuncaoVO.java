/*
 * 
 */
package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncaoVO.
 */
@XmlRootElement(name = "funcao")
@XmlType(propOrder = { "strFuncaoVerbo", "strFuncaoObjeto" })
public class FuncaoVO {

	/** The str funcao verbo. */
	private String strFuncaoVerbo;
	
	/** The str funcao objeto. */
	private String strFuncaoObjeto;

	/**
	 * Instantiates a new funcao vo.
	 */
	public FuncaoVO() {
	}

	/**
	 * Instantiates a new funcao vo.
	 *
	 * @param strFuncaoVerbo the str funcao verbo
	 * @param strFuncaoObjeto the str funcao objeto
	 */
	public FuncaoVO(String strFuncaoVerbo, String strFuncaoObjeto) {
		this.strFuncaoVerbo = strFuncaoVerbo;
		this.strFuncaoObjeto = strFuncaoObjeto;
	}

	/**
	 * Gets the str funcao verbo.
	 *
	 * @return the str funcao verbo
	 */
	public String getStrFuncaoVerbo() {
		return strFuncaoVerbo;
	}

	/**
	 * Sets the str funcao verbo.
	 *
	 * @param strFuncaoVerbo the new str funcao verbo
	 */
	public void setStrFuncaoVerbo(String strFuncaoVerbo) {
		this.strFuncaoVerbo = strFuncaoVerbo;
	}

	/**
	 * Gets the str funcao objeto.
	 *
	 * @return the str funcao objeto
	 */
	public String getStrFuncaoObjeto() {
		return strFuncaoObjeto;
	}

	/**
	 * Sets the str funcao objeto.
	 *
	 * @param strFuncaoObjeto the new str funcao objeto
	 */
	public void setStrFuncaoObjeto(String strFuncaoObjeto) {
		this.strFuncaoObjeto = strFuncaoObjeto;
	}
}
