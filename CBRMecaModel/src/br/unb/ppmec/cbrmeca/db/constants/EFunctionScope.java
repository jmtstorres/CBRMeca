/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Enum EFunctionScope.
 */
public enum EFunctionScope {
	
	/** The None. */
	//============================================================
	None			("Escopo Indefinido", 	new Color(150, 150, 150), 0),
	
	/** The power. */
	POWER			("Potência",	 		new Color(150, 150, 150), 1),
	
	/** The information. */
	INFORMATION		("Informação", 			new Color(150, 150, 150), 2),
	
	/** The control. */
	CONTROL			("Controle", 			new Color(150, 150, 150), 4),
	
	/** The mechanic. */
	MECHANIC		("Mecânica", 			new Color(150, 150, 150), 8);
	//============================================================
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id;
	
	/** The color. */
	private Color color;
	
	/**
	 * Instantiates a new e function scope.
	 *
	 * @param description the description
	 * @param color the color
	 * @param id the id
	 */
	//============================================================
	private EFunctionScope(String description, Color color, int id) {
		this.description = description;
		this.id = id;
		this.color = color;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}
}
