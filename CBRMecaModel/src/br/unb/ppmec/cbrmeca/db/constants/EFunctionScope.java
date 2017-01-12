package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

public enum EFunctionScope {
	
	//============================================================
	None			("Escopo Indefinido", 	new Color(150, 150, 150), 0),
	POWER			("Potência",	 		new Color(150, 150, 150), 1),
	INFORMATION		("Informação", 			new Color(150, 150, 150), 2),
	CONTROL			("Controle", 			new Color(150, 150, 150), 4),
	MECHANIC		("Mecânica", 			new Color(150, 150, 150), 8);
	//============================================================
	
	private String description;
	private int id;
	private Color color;
	
	//============================================================
	private EFunctionScope(String description, Color color, int id) {
		this.description = description;
		this.id = id;
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

	@Override
	public String toString() {
		return this.description;
	}
}
