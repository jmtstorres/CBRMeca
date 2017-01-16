/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Enum EFunctionEffect.
 */
public enum EFunctionEffect {
	
	/** The None. */
	//============================================================
	None		("Efeito", 		Color.WHITE, 				0),
	
	/** The regular. */
	REGULAR		("Regular", 	new Color(150, 150, 255), 	1),
	
	/** The preventive. */
	PREVENTIVE	("Preventiva", 	new Color(150, 200, 255), 	2),
	
	/** The corretive. */
	CORRETIVE	("Corretiva", 	new Color(255, 200, 200), 	3);
	//============================================================
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id;
	
	/** The color. */
	private Color color;
	
	/**
	 * Instantiates a new e function effect.
	 *
	 * @param description the description
	 * @param color the color
	 * @param id the id
	 */
	//============================================================
	private EFunctionEffect(String description, Color color, int id) {
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
