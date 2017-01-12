package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "funcao")
@XmlType(propOrder = { "strFuncaoVerbo", "strFuncaoObjeto" })
public class FuncaoVO {

	private String strFuncaoVerbo;
	private String strFuncaoObjeto;

	public FuncaoVO() {
	}

	public FuncaoVO(String strFuncaoVerbo, String strFuncaoObjeto) {
		this.strFuncaoVerbo = strFuncaoVerbo;
		this.strFuncaoObjeto = strFuncaoObjeto;
	}

	public String getStrFuncaoVerbo() {
		return strFuncaoVerbo;
	}

	public void setStrFuncaoVerbo(String strFuncaoVerbo) {
		this.strFuncaoVerbo = strFuncaoVerbo;
	}

	public String getStrFuncaoObjeto() {
		return strFuncaoObjeto;
	}

	public void setStrFuncaoObjeto(String strFuncaoObjeto) {
		this.strFuncaoObjeto = strFuncaoObjeto;
	}
}
