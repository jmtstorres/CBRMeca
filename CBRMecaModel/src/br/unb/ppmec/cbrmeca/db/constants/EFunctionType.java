/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.constants;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Enum EFunctionType.
 */
public enum EFunctionType {
	
	/** The None. */
	//============================================================
	None	("Tipo/função", 		Color.WHITE, 				0, 0),
	
	/** The TYP e_1. */
	TYPE_1	("Técnica", 			new Color(150, 150, 255), 	1, 0),
	
	/** The TYP e_2. */
	TYPE_2	(" Estrutural", 		new Color(150, 200, 255), 	2, 1),
	
	/** The TYP e_3. */
	TYPE_3	(" Operativa", 			new Color(150, 200, 255), 	3, 1),
	
	/** The TYP e_4. */
	TYPE_4	("  Transformação", 	new Color(200, 200, 255), 	4, 3),
	
	/** The TYP e_5. */
	TYPE_5	("  Adicional", 		new Color(200, 200, 255), 	5, 3),
	
	/** The TYP e_6. */
	TYPE_6	("Interativa", 			new Color(255, 150, 150), 	6, 0),
	
	/** The TYP e_7. */
	TYPE_7	(" Ergonômica", 		new Color(255, 200, 150), 	7, 6),
	
	/** The TYP e_8. */
	TYPE_8	(" Comunicativa", 		new Color(255, 200, 150), 	8, 6),
	
	/** The TYP e_9. */
	TYPE_9	("  Sintática", 		new Color(255, 200, 200), 	9, 8),
	
	/** The TYP e_10. */
	TYPE_10	("  Semântica", 		new Color(255, 200, 200), 	10, 8);
	//============================================================
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id;
	
	/** The id pai. */
	private int idPai;
	
	/** The color. */
	private Color color;
	
	//============================================================
	
	/**
	 * Instantiates a new e function type.
	 *
	 * @param description the description
	 * @param color the color
	 * @param id the id
	 * @param idPai the id pai
	 */
	private EFunctionType(String description, Color color, int id, int idPai) {
		this.description = description;
		this.id = id;
		this.idPai = idPai;
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

	/**
	 * Gets the id pai.
	 *
	 * @return the id pai
	 */
	public int getIdPai() {
		return idPai;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}
}
