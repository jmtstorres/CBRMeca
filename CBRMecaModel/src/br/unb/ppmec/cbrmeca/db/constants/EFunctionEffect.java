package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

public enum EFunctionEffect {
	
	//============================================================
	None		("Efeito", 		Color.WHITE, 				0),
	REGULAR		("Regular", 	new Color(150, 150, 255), 	1),
	PREVENTIVE	("Preventiva", 	new Color(150, 200, 255), 	2),
	CORRETIVE	("Corretiva", 	new Color(255, 200, 200), 	3);
	//============================================================
	
	private String description;
	private int id;
	private Color color;
	
	//============================================================
	private EFunctionEffect(String description, Color color, int id) {
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
