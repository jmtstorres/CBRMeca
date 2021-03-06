/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model;

// Generated May 25, 2016 11:48:00 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * FuncaoParcial generated by hbm2java.
 */
@Entity
@Table(name = "Funcao")
public class Funcao implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4908688476247627544L;
	
	/** The id funcao. */
	private Integer idFuncao;
	
	/** The str funcao verbo. */
	private String strFuncaoVerbo;
	
	/** The str funcao objeto. */
	private String strFuncaoObjeto;

	/**
	 * Instantiates a new funcao.
	 */
	public Funcao() {
	}

	/**
	 * Instantiates a new funcao.
	 *
	 * @param idFuncao the id funcao
	 */
	public Funcao(Integer idFuncao) {
		this.idFuncao = idFuncao;
	}

	/**
	 * Instantiates a new funcao.
	 *
	 * @param idFuncaoParcial the id funcao parcial
	 * @param strFuncaoVerbo the str funcao verbo
	 * @param strFuncaoObjeto the str funcao objeto
	 */
	public Funcao(	Integer idFuncaoParcial, 
					String strFuncaoVerbo,
					String strFuncaoObjeto) {
		
		this.idFuncao = idFuncaoParcial;
		this.strFuncaoVerbo = strFuncaoVerbo;
		this.strFuncaoObjeto = strFuncaoObjeto;
	}

	/**
	 * Gets the id funcao.
	 *
	 * @return the id funcao
	 */
	@Id
	@Column(name = "id_funcao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdFuncao() {
		return this.idFuncao;
	}

	/**
	 * Sets the id funcao.
	 *
	 * @param idFuncao the new id funcao
	 */
	public void setIdFuncao(Integer idFuncao) {
		this.idFuncao = idFuncao;
	}

	/**
	 * Gets the str funcao verbo.
	 *
	 * @return the str funcao verbo
	 */
	@Column(name = "str_funcao_verbo", length = 2000000000)
	public String getStrFuncaoVerbo() {
		return this.strFuncaoVerbo;
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
	@Column(name = "str_funcao_objeto", length = 2000000000)
	public String getStrFuncaoObjeto() {
		return this.strFuncaoObjeto;
	}

	/**
	 * Sets the str funcao objeto.
	 *
	 * @param strFuncaoObjeto the new str funcao objeto
	 */
	public void setStrFuncaoObjeto(String strFuncaoObjeto) {
		this.strFuncaoObjeto = strFuncaoObjeto;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return strFuncaoVerbo + " " + strFuncaoObjeto;
	}
}
