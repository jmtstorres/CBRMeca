package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

public enum EFunctionNecessity {
	
	//============================================================
	None		("Necessidade", Color.WHITE, 				0),
	NECESSARY	("Necessária", 	new Color(150, 150, 150), 	1),
	DERIVED		("Derivada", 	new Color(150, 150, 150), 	2);
	//============================================================
	
	private String description;
	private int id;
	private Color color;
	
	//============================================================
	private EFunctionNecessity(String description, Color color, int id) {
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
