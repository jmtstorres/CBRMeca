package br.unb.ppmec.cbrmeca.db.model.dao;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.ItemAvaliacao;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IItemAvaliacaoDAO;

public class ItemAvaliacaoDAOImpl extends DaoBase<ItemAvaliacao> implements IItemAvaliacaoDAO{
	
	public ItemAvaliacaoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
}
