package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo;

import br.unb.ppmec.cbrmeca.xml.vo.ImagemConceitoVO;

public class ConceitoMatrizVO {
	
	private String strConceito;
	private ImagemConceitoVO imgConceito;
	private String descConceito;

	public ConceitoMatrizVO(String strConceito, ImagemConceitoVO imgConceito,
			String descConceito) {
		super();
		this.strConceito = strConceito;
		this.imgConceito = imgConceito;
		this.descConceito = descConceito;
	}

	public String getStrConceito() {
		return strConceito;
	}

	public ImagemConceitoVO getImgConceito() {
		return imgConceito;
	}

	public String getDescConceito() {
		return descConceito;
	}
}
