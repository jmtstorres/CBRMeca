/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.dao;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.ItemAvaliacao;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IItemAvaliacaoDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemAvaliacaoDAOImpl.
 */
public class ItemAvaliacaoDAOImpl extends DaoBase<ItemAvaliacao> implements IItemAvaliacaoDAO{
	
	/**
	 * Instantiates a new item avaliacao dao impl.
	 */
	public ItemAvaliacaoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
}
