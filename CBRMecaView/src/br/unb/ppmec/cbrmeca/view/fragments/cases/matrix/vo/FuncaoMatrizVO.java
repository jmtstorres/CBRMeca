package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo;

import java.util.List;

import br.unb.ppmec.cbrmeca.db.model.Conceito;

public class FuncaoMatrizVO {
	
	String funcao;
	boolean elementar;
	int escopo;
	int tipoFuncao;
	int tipoEfeito;
	int tipoNecessidade;
	int idTipoCaso;
	List<Conceito> conceitos;

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

	public String getFuncao() {
		return funcao;
	}

	public boolean isElementar() {
		return elementar;
	}

	public int getEscopo() {
		return escopo;
	}

	public int getTipoFuncao() {
		return tipoFuncao;
	}

	public int getTipoEfeito() {
		return tipoEfeito;
	}

	public int getTipoNecessidade() {
		return tipoNecessidade;
	}

	public int getIdTipoCaso() {
		return idTipoCaso;
	}

	public List<Conceito> getConceitos() {
		return conceitos;
	}
}
