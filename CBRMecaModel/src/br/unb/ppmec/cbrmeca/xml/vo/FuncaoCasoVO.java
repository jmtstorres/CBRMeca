package br.unb.ppmec.cbrmeca.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "funcaoCaso")
@XmlType(propOrder = { "funcao", "bolElementar", "tipoFuncao", "tipoNecessidade", "tipoEfeito", "tipoEscopo", "children", "solucoes" })
public class FuncaoCasoVO {
	
	//============================================================
	//Identificador da funcao
	private FuncaoVO funcao;
	//Se função elementar, será utilziada na matriz
	private boolean bolElementar;
	//Tipo funcao de produto
	private Integer tipoFuncao;
	//Grau de necessidade (necessária/derivada)
	private Integer tipoNecessidade;
	//Tipo do efeito (Indiferente, prevenção, correção)
	private Integer tipoEfeito;
	//Escopo abrangido (Informação,Potência, mecânica, controle)
	private Integer tipoEscopo;
	//Lista de funcoes
	private List<FuncaoCasoVO> children;
	//Lista de solucoes
	private List<SolucaoVO> solucoes;
	//============================================================
	
	public FuncaoCasoVO() {
	}

	public FuncaoVO getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoVO funcao) {
		this.funcao = funcao;
	}

	public boolean isBolElementar() {
		return bolElementar;
	}

	public void setBolElementar(boolean bolElementar) {
		this.bolElementar = bolElementar;
	}

	public Integer getTipoFuncao() {
		return tipoFuncao;
	}

	public void setTipoFuncao(Integer tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	public Integer getTipoNecessidade() {
		return tipoNecessidade;
	}

	public void setTipoNecessidade(Integer tipoNecessidade) {
		this.tipoNecessidade = tipoNecessidade;
	}

	public Integer getTipoEfeito() {
		return tipoEfeito;
	}

	public void setTipoEfeito(Integer tipoEfeito) {
		this.tipoEfeito = tipoEfeito;
	}

	public Integer getTipoEscopo() {
		return tipoEscopo;
	}

	public void setTipoEscopo(Integer tipoEscopo) {
		this.tipoEscopo = tipoEscopo;
	}

	public List<FuncaoCasoVO> getChildren() {
		return children;
	}

	public void setChildren(List<FuncaoCasoVO> children) {
		this.children = children;
	}

	public List<SolucaoVO> getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(List<SolucaoVO> solucoes) {
		this.solucoes = solucoes;
	}
}
