package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "conceito")
@XmlType(propOrder = { "strConceito", "imgConceito", "descConceito" })
public class ConceitoVO {

	private String strConceito;
	private ImagemConceitoVO imgConceito;
	private String descConceito;

	public ConceitoVO() {
	}

	public String getStrConceito() {
		return strConceito;
	}

	public void setStrConceito(String strConceito) {
		this.strConceito = strConceito;
	}

	public ImagemConceitoVO getImgConceito() {
		return imgConceito;
	}

	public void setImgConceito(ImagemConceitoVO imgConceito) {
		this.imgConceito = imgConceito;
	}

	public String getDescConceito() {
		return descConceito;
	}

	public void setDescConceito(String descConceito) {
		this.descConceito = descConceito;
	}
}
