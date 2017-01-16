/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.dialect;

import br.unb.ppmec.cbrmeca.db.model.Palavra;
import br.unb.ppmec.cbrmeca.db.model.dao.PalavraDAOImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class Hibernate5InsertTest.
 */
public class Hibernate5InsertTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Palavra emp = new Palavra();
		PalavraDAOImpl dao = new PalavraDAOImpl();
		emp.setStrPalavra("dafsdfasd");
		dao.save(emp);
		System.out.println("Saiu");
		System.exit(0);
	}
}