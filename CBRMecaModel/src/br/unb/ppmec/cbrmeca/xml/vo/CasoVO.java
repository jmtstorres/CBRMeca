package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "caso")
@XmlType(propOrder = { "strTitulo", "strDescricao", "funcaoGeral", "idTipo", "intAno" })
public class CasoVO {

	private String strTitulo;
	private String strDescricao;
	private FuncaoCasoVO funcaoGeral;
	private Integer idTipo;
	private Integer intAno;

	public CasoVO() {
	}

	public CasoVO(String strTitulo, String strDescricao,
			FuncaoCasoVO funcaoGeral, Integer idTipo, Integer intAno) {
		super();
		this.strTitulo = strTitulo;
		this.strDescricao = strDescricao;
		this.funcaoGeral = funcaoGeral;
		this.idTipo = idTipo;
		this.intAno = intAno;
	}

	public String getStrTitulo() {
		return strTitulo;
	}

	public void setStrTitulo(String strTitulo) {
		this.strTitulo = strTitulo;
	}

	public String getStrDescricao() {
		return strDescricao;
	}

	public void setStrDescricao(String strDescricao) {
		this.strDescricao = strDescricao;
	}

	public FuncaoCasoVO getFuncaoGeral() {
		return funcaoGeral;
	}

	public void setFuncaoGeral(FuncaoCasoVO funcaoGeral) {
		this.funcaoGeral = funcaoGeral;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public Integer getIntAno() {
		return intAno;
	}

	public void setIntAno(Integer intAno) {
		this.intAno = intAno;
	}
}
