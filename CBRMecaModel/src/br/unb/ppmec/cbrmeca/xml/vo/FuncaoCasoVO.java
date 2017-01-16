/*
 * 
 */
package br.unb.ppmec.cbrmeca.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncaoCasoVO.
 */
@XmlRootElement(name = "funcaoCaso")
@XmlType(propOrder = { "funcao", "bolElementar", "tipoFuncao", "tipoNecessidade", "tipoEfeito", "tipoEscopo", "children", "solucoes" })
public class FuncaoCasoVO {
	
	//============================================================
	/** The funcao. */
	//Identificador da funcao
	private FuncaoVO funcao;
	
	/** The bol elementar. */
	//Se função elementar, será utilziada na matriz
	private boolean bolElementar;
	
	/** The tipo funcao. */
	//Tipo funcao de produto
	private Integer tipoFuncao;
	
	/** The tipo necessidade. */
	//Grau de necessidade (necessária/derivada)
	private Integer tipoNecessidade;
	
	/** The tipo efeito. */
	//Tipo do efeito (Indiferente, prevenção, correção)
	private Integer tipoEfeito;
	
	/** The tipo escopo. */
	//Escopo abrangido (Informação,Potência, mecânica, controle)
	private Integer tipoEscopo;
	
	/** The children. */
	//Lista de funcoes
	private List<FuncaoCasoVO> children;
	
	/** The solucoes. */
	//Lista de solucoes
	private List<SolucaoVO> solucoes;
	//============================================================
	
	/**
	 * Instantiates a new funcao caso vo.
	 */
	public FuncaoCasoVO() {
	}

	/**
	 * Gets the funcao.
	 *
	 * @return the funcao
	 */
	public FuncaoVO getFuncao() {
		return funcao;
	}

	/**
	 * Sets the funcao.
	 *
	 * @param funcao the new funcao
	 */
	public void setFuncao(FuncaoVO funcao) {
		this.funcao = funcao;
	}

	/**
	 * Checks if is bol elementar.
	 *
	 * @return true, if is bol elementar
	 */
	public boolean isBolElementar() {
		return bolElementar;
	}

	/**
	 * Sets the bol elementar.
	 *
	 * @param bolElementar the new bol elementar
	 */
	public void setBolElementar(boolean bolElementar) {
		this.bolElementar = bolElementar;
	}

	/**
	 * Gets the tipo funcao.
	 *
	 * @return the tipo funcao
	 */
	public Integer getTipoFuncao() {
		return tipoFuncao;
	}

	/**
	 * Sets the tipo funcao.
	 *
	 * @param tipoFuncao the new tipo funcao
	 */
	public void setTipoFuncao(Integer tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	/**
	 * Gets the tipo necessidade.
	 *
	 * @return the tipo necessidade
	 */
	public Integer getTipoNecessidade() {
		return tipoNecessidade;
	}

	/**
	 * Sets the tipo necessidade.
	 *
	 * @param tipoNecessidade the new tipo necessidade
	 */
	public void setTipoNecessidade(Integer tipoNecessidade) {
		this.tipoNecessidade = tipoNecessidade;
	}

	/**
	 * Gets the tipo efeito.
	 *
	 * @return the tipo efeito
	 */
	public Integer getTipoEfeito() {
		return tipoEfeito;
	}

	/**
	 * Sets the tipo efeito.
	 *
	 * @param tipoEfeito the new tipo efeito
	 */
	public void setTipoEfeito(Integer tipoEfeito) {
		this.tipoEfeito = tipoEfeito;
	}

	/**
	 * Gets the tipo escopo.
	 *
	 * @return the tipo escopo
	 */
	public Integer getTipoEscopo() {
		return tipoEscopo;
	}

	/**
	 * Sets the tipo escopo.
	 *
	 * @param tipoEscopo the new tipo escopo
	 */
	public void setTipoEscopo(Integer tipoEscopo) {
		this.tipoEscopo = tipoEscopo;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<FuncaoCasoVO> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(List<FuncaoCasoVO> children) {
		this.children = children;
	}

	/**
	 * Gets the solucoes.
	 *
	 * @return the solucoes
	 */
	public List<SolucaoVO> getSolucoes() {
		return solucoes;
	}

	/**
	 * Sets the solucoes.
	 *
	 * @param solucoes the new solucoes
	 */
	public void setSolucoes(List<SolucaoVO> solucoes) {
		this.solucoes = solucoes;
	}
}
