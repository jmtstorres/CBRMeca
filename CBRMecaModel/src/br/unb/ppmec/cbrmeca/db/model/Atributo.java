/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model;

// Generated May 25, 2016 11:48:00 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Atributo generated by hbm2java.
 */
@Entity
@Table(name = "Atributo")
public class Atributo implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 362176579016825085L;
	
	/** The id atributo. */
	private Integer idAtributo;
	
	/** The str atributo. */
	private String strAtributo;

	/**
	 * Instantiates a new atributo.
	 */
	public Atributo() {
	}

	/**
	 * Instantiates a new atributo.
	 *
	 * @param idAtributo the id atributo
	 */
	public Atributo(Integer idAtributo) {
		this.idAtributo = idAtributo;
	}

	/**
	 * Instantiates a new atributo.
	 *
	 * @param idAtributo the id atributo
	 * @param strAtributo the str atributo
	 */
	public Atributo(Integer idAtributo, String strAtributo) {
		this.idAtributo = idAtributo;
		this.strAtributo = strAtributo;
	}

	/**
	 * Gets the id atributo.
	 *
	 * @return the id atributo
	 */
	@Id
	@Column(name = "id_atributo")
	public Integer getIdAtributo() {
		return this.idAtributo;
	}

	/**
	 * Sets the id atributo.
	 *
	 * @param idAtributo the new id atributo
	 */
	public void setIdAtributo(Integer idAtributo) {
		this.idAtributo = idAtributo;
	}

	/**
	 * Gets the str atributo.
	 *
	 * @return the str atributo
	 */
	@Column(name = "str_atributo", length = 2000000000)
	public String getStrAtributo() {
		return this.strAtributo;
	}

	/**
	 * Sets the str atributo.
	 *
	 * @param strAtributo the new str atributo
	 */
	public void setStrAtributo(String strAtributo) {
		this.strAtributo = strAtributo;
	}

}
