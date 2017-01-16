/*
 * 
 */
package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * The Class CasoVO.
 */
@XmlRootElement(name = "caso")
@XmlType(propOrder = { "strTitulo", "strDescricao", "funcaoGeral", "idTipo", "intAno" })
public class CasoVO {

	/** The str titulo. */
	private String strTitulo;
	
	/** The str descricao. */
	private String strDescricao;
	
	/** The funcao geral. */
	private FuncaoCasoVO funcaoGeral;
	
	/** The id tipo. */
	private Integer idTipo;
	
	/** The int ano. */
	private Integer intAno;

	/**
	 * Instantiates a new caso vo.
	 */
	public CasoVO() {
	}

	/**
	 * Instantiates a new caso vo.
	 *
	 * @param strTitulo the str titulo
	 * @param strDescricao the str descricao
	 * @param funcaoGeral the funcao geral
	 * @param idTipo the id tipo
	 * @param intAno the int ano
	 */
	public CasoVO(String strTitulo, String strDescricao,
			FuncaoCasoVO funcaoGeral, Integer idTipo, Integer intAno) {
		super();
		this.strTitulo = strTitulo;
		this.strDescricao = strDescricao;
		this.funcaoGeral = funcaoGeral;
		this.idTipo = idTipo;
		this.intAno = intAno;
	}

	/**
	 * Gets the str titulo.
	 *
	 * @return the str titulo
	 */
	public String getStrTitulo() {
		return strTitulo;
	}

	/**
	 * Sets the str titulo.
	 *
	 * @param strTitulo the new str titulo
	 */
	public void setStrTitulo(String strTitulo) {
		this.strTitulo = strTitulo;
	}

	/**
	 * Gets the str descricao.
	 *
	 * @return the str descricao
	 */
	public String getStrDescricao() {
		return strDescricao;
	}

	/**
	 * Sets the str descricao.
	 *
	 * @param strDescricao the new str descricao
	 */
	public void setStrDescricao(String strDescricao) {
		this.strDescricao = strDescricao;
	}

	/**
	 * Gets the funcao geral.
	 *
	 * @return the funcao geral
	 */
	public FuncaoCasoVO getFuncaoGeral() {
		return funcaoGeral;
	}

	/**
	 * Sets the funcao geral.
	 *
	 * @param funcaoGeral the new funcao geral
	 */
	public void setFuncaoGeral(FuncaoCasoVO funcaoGeral) {
		this.funcaoGeral = funcaoGeral;
	}

	/**
	 * Gets the id tipo.
	 *
	 * @return the id tipo
	 */
	public Integer getIdTipo() {
		return idTipo;
	}

	/**
	 * Sets the id tipo.
	 *
	 * @param idTipo the new id tipo
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	/**
	 * Gets the int ano.
	 *
	 * @return the int ano
	 */
	public Integer getIntAno() {
		return intAno;
	}

	/**
	 * Sets the int ano.
	 *
	 * @param intAno the new int ano
	 */
	public void setIntAno(Integer intAno) {
		this.intAno = intAno;
	}
}
