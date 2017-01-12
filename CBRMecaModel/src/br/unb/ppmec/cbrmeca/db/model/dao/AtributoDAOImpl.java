package br.unb.ppmec.cbrmeca.db.model.dao;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Atributo;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IAtributoDAO;

public class AtributoDAOImpl extends DaoBase<Atributo> implements IAtributoDAO{
	
	public AtributoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
}
