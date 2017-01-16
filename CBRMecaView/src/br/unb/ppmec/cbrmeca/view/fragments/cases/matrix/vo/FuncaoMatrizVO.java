/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncaoMatrizVO.
 */
public class FuncaoMatrizVO {
	
	/** The funcao. */
	String funcao;
	
	/** The elementar. */
	boolean elementar;
	
	/** The escopo. */
	int escopo;
	
	/** The tipo funcao. */
	int tipoFuncao;
	
	/** The tipo efeito. */
	int tipoEfeito;
	
	/** The tipo necessidade. */
	int tipoNecessidade;
	
	/** The id tipo caso. */
	int idTipoCaso;
	
	/** The conceitos. */
	List<Conceito> conceitos;

	/**
	 * Instantiates a new funcao matriz vo.
	 *
	 * @param funcao the funcao
	 * @param elementar the elementar
	 * @param escopo the escopo
	 * @param tipoFuncao the tipo funcao
	 * @param tipoEfeito the tipo efeito
	 * @param tipoNecessidade the tipo necessidade
	 * @param idTipoCaso the id tipo caso
	 * @param conceitos the conceitos
	 */
	public FuncaoMatrizVO(String funcao, boolean elementar, int escopo,
			int tipoFuncao, int tipoEfeito, int tipoNecessidade,
			int idTipoCaso, List<Conceito> conceitos) {
		super();
		this.funcao = funcao;
		this.elementar = elementar;
		this.escopo = escopo;
		this.tipoFuncao = tipoFuncao;
		this.tipoEfeito = tipoEfeito;
		this.tipoNecessidade = tipoNecessidade;
		this.idTipoCaso = idTipoCaso;
		this.conceitos = conceitos;
	}

	/**
	 * Gets the funcao.
	 *
	 * @return the funcao
	 */
	public String getFuncao() {
		return funcao;
	}

	/**
	 * Checks if is elementar.
	 *
	 * @return true, if is elementar
	 */
	public boolean isElementar() {
		return elementar;
	}

	/**
	 * Gets the escopo.
	 *
	 * @return the escopo
	 */
	public int getEscopo() {
		return escopo;
	}

	/**
	 * Gets the tipo funcao.
	 *
	 * @return the tipo funcao
	 */
	public int getTipoFuncao() {
		return tipoFuncao;
	}

	/**
	 * Gets the tipo efeito.
	 *
	 * @return the tipo efeito
	 */
	public int getTipoEfeito() {
		return tipoEfeito;
	}

	/**
	 * Gets the tipo necessidade.
	 *
	 * @return the tipo necessidade
	 */
	public int getTipoNecessidade() {
		return tipoNecessidade;
	}

	/**
	 * Gets the id tipo caso.
	 *
	 * @return the id tipo caso
	 */
	public int getIdTipoCaso() {
		return idTipoCaso;
	}

	/**
	 * Gets the conceitos.
	 *
	 * @return the conceitos
	 */
	public List<Conceito> getConceitos() {
		return conceitos;
	}
}
