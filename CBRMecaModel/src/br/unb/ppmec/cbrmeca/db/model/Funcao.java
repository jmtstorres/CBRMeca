package br.unb.ppmec.cbrmeca.db.model;

// Generated May 25, 2016 11:48:00 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FuncaoParcial generated by hbm2java
 */
@Entity
@Table(name = "Funcao")
public class Funcao implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4908688476247627544L;
	private Integer idFuncao;
	private String strFuncaoVerbo;
	private String strFuncaoObjeto;

	public Funcao() {
	}

	public Funcao(Integer idFuncao) {
		this.idFuncao = idFuncao;
	}

	public Funcao(	Integer idFuncaoParcial, 
					String strFuncaoVerbo,
					String strFuncaoObjeto) {
		
		this.idFuncao = idFuncaoParcial;
		this.strFuncaoVerbo = strFuncaoVerbo;
		this.strFuncaoObjeto = strFuncaoObjeto;
	}

	@Id
	@Column(name = "id_funcao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdFuncao() {
		return this.idFuncao;
	}

	public void setIdFuncao(Integer idFuncao) {
		this.idFuncao = idFuncao;
	}

	@Column(name = "str_funcao_verbo", length = 2000000000)
	public String getStrFuncaoVerbo() {
		return this.strFuncaoVerbo;
	}

	public void setStrFuncaoVerbo(String strFuncaoVerbo) {
		this.strFuncaoVerbo = strFuncaoVerbo;
	}

	@Column(name = "str_funcao_objeto", length = 2000000000)
	public String getStrFuncaoObjeto() {
		return this.strFuncaoObjeto;
	}

	public void setStrFuncaoObjeto(String strFuncaoObjeto) {
		this.strFuncaoObjeto = strFuncaoObjeto;
	}
	
	public String toString(){
		return strFuncaoVerbo + " " + strFuncaoObjeto;
	}
}