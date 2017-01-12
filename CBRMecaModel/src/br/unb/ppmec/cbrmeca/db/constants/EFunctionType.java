package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

public enum EFunctionType {
	//============================================================
	None	("Tipo/função", 		Color.WHITE, 				0, 0),
	TYPE_1	("Técnica", 			new Color(150, 150, 255), 	1, 0),
	TYPE_2	(" Estrutural", 		new Color(150, 200, 255), 	2, 1),
	TYPE_3	(" Operativa", 			new Color(150, 200, 255), 	3, 1),
	TYPE_4	("  Transformação", 	new Color(200, 200, 255), 	4, 3),
	TYPE_5	("  Adicional", 		new Color(200, 200, 255), 	5, 3),
	TYPE_6	("Interativa", 			new Color(255, 150, 150), 	6, 0),
	TYPE_7	(" Ergonômica", 		new Color(255, 200, 150), 	7, 6),
	TYPE_8	(" Comunicativa", 		new Color(255, 200, 150), 	8, 6),
	TYPE_9	("  Sintática", 		new Color(255, 200, 200), 	9, 8),
	TYPE_10	("  Semântica", 		new Color(255, 200, 200), 	10, 8);
	//============================================================
	
	private String description;
	private int id;
	private int idPai;
	private Color color;
	
	//============================================================
	
	private EFunctionType(String description, Color color, int id, int idPai) {
		this.description = description;
		this.id = id;
		this.idPai = idPai;
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
	
	public Color getColor() {
		return color;
	}

	public int getIdPai() {
		return idPai;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
