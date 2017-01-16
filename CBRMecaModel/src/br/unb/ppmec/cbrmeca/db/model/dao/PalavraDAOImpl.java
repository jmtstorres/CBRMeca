/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Palavra;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IPalavraDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class PalavraDAOImpl.
 */
public class PalavraDAOImpl extends DaoBase<Palavra> implements IPalavraDAO {

	/**
	 * Instantiates a new palavra dao impl.
	 */
	public PalavraDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}

	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IPalavraDAO#getStartedWith(java.lang.String)
	 */
	@Override
	public List<Palavra> getStartedWith(String pattern) {
		Criterion criterion = Restrictions.ilike("strPalavra", pattern, MatchMode.ANYWHERE);
		return getListByCriterion(criterion);
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IPalavraDAO#getMatching(java.lang.String)
	 */
	@Override
	public Palavra getMatching(String pattern) {
		Criterion criterion = Restrictions.ilike("strPalavra", pattern, MatchMode.EXACT);
		List<Palavra> list = getListByCriterion(criterion);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
