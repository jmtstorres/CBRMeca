/*
 * 
 */
package br.unb.ppmec.cbrmeca.view;

import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.db.model.dao.CasoDAOImpl;


// TODO: Auto-generated Javadoc
/**
 * The Class PalavraTeste.
 */
public class PalavraTeste {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		PalavraTeste teste = new PalavraTeste();
		
		
		System.out.println(teste.renameCase("teste1"));
		System.out.println(teste.renameCase("teste2 (1)"));
		System.out.println(teste.renameCase("teste3 (2)"));
		
		String title = "Hélice de propulsão magnética(1)";
		CasoDAOImpl cDAO = new CasoDAOImpl();
		Caso exists = cDAO.getExactTitle(title);
		System.out.println(exists.getStrTitulo());
	}
	
	/**
	 * Rename case.
	 *
	 * @param title the title
	 * @return the string
	 */
	private String renameCase(String title){
		String numberedTitle = null; 
		int number = getVariationNumber(title);
		
		if(number > 0){
			numberedTitle = title.replaceAll("\\(" + number + "\\)"
											 ,"(" + (number+1) + ")");
		}else if(!(title.contains("(") && title.contains(")"))){
			numberedTitle = title + "(" + (number+1) + ")";
		}
		
		return numberedTitle;
	}
	
	/**
	 * Gets the variation number.
	 *
	 * @param title the title
	 * @return the variation number
	 */
	private int getVariationNumber(String title){
		int number = 1;
		
		if(!(title.contains("(") && title.contains(")"))){
			return 0;
		}
		
		while(!title.contains("(" + number + "") &&
			  (title.contains("(") && title.contains(")"))){
			number++;
		}
		
		return number;
	}
}
