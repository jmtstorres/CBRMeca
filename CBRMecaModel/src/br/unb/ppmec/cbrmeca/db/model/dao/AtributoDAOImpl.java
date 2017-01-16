/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.dao;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Atributo;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IAtributoDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class AtributoDAOImpl.
 */
public class AtributoDAOImpl extends DaoBase<Atributo> implements IAtributoDAO{
	
	/**
	 * Instantiates a new atributo dao impl.
	 */
	public AtributoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
}
