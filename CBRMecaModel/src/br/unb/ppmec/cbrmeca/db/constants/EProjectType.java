package br.unb.ppmec.cbrmeca.db.constants;

public enum EProjectType {
	None("Selecione tipo de projeto", 0),
	Byomedical_Systems("Sistemas e Tecnologias Biomédicas", 1),
	Aerospace_Systems("Sistemas e Tecnologias Aeroespaciais", 2),
	Vehicle_Systems("Sistemas e Tecnologias Veiculares", 3),
	Autonomous_Systems_Control("Sistemas Autônomos e de Controle", 4),
	Energy_Sustainable("Sistemas Sustentáveis e Energia", 5),
	Printing_Imaging_Systems("Sistemas e Tecnologias de Impressão e Imageria", 6),
	Chemical_Mat_Processing("Processamento Químico e Materiais", 7),
	Process_Inovation("Inovação de Processos", 8);
	
	private String description;
	private int id;
	
	private EProjectType(String description, int id) {
		this.description = description;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
