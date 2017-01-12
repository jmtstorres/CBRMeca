package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "solucao")
public class SolucaoVO {

	private ConceitoVO conceito;

	public SolucaoVO() {
	}
	
	public ConceitoVO getConceito() {
		return conceito;
	}

	public void setConceito(ConceitoVO conceito) {
		this.conceito = conceito;
	}
}
