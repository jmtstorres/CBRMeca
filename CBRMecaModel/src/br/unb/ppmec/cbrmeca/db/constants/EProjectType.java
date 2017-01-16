/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.constants;

// TODO: Auto-generated Javadoc
/**
 * The Enum EProjectType.
 */
public enum EProjectType {
	
	/** The None. */
	None("Selecione tipo de projeto", 0),
	
	/** The Byomedical_ systems. */
	Byomedical_Systems("Sistemas e Tecnologias Biomédicas", 1),
	
	/** The Aerospace_ systems. */
	Aerospace_Systems("Sistemas e Tecnologias Aeroespaciais", 2),
	
	/** The Vehicle_ systems. */
	Vehicle_Systems("Sistemas e Tecnologias Veiculares", 3),
	
	/** The Autonomous_ systems_ control. */
	Autonomous_Systems_Control("Sistemas Autônomos e de Controle", 4),
	
	/** The Energy_ sustainable. */
	Energy_Sustainable("Sistemas Sustentáveis e Energia", 5),
	
	/** The Printing_ imaging_ systems. */
	Printing_Imaging_Systems("Sistemas e Tecnologias de Impressão e Imageria", 6),
	
	/** The Chemical_ mat_ processing. */
	Chemical_Mat_Processing("Processamento Químico e Materiais", 7),
	
	/** The Process_ inovation. */
	Process_Inovation("Inovação de Processos", 8);
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new e project type.
	 *
	 * @param description the description
	 * @param id the id
	 */
	private EProjectType(String description, int id) {
		this.description = description;
		this.id = id;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}
}
