package br.unb.ppmec.cbrmeca.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "imagemConceito")
public class ImagemConceitoVO {

	private String dirImagemConceito;

	public ImagemConceitoVO() {
	}

	public String getDirImagemConceito() {
		return dirImagemConceito;
	}

	public void setDirImagemConceito(String dirImagemConceito) {
		this.dirImagemConceito = dirImagemConceito;
	}
}
