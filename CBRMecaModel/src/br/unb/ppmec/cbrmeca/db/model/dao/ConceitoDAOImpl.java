/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IConceitoDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ConceitoDAOImpl.
 */
public class ConceitoDAOImpl extends DaoBase<Conceito> implements IConceitoDAO{
	
	/**
	 * Instantiates a new conceito dao impl.
	 */
	public ConceitoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IConceitoDAO#getStartedWith(java.lang.String)
	 */
	@Override
	public List<Conceito> getStartedWith(String pattern) {
		Criterion criterion = Restrictions.ilike("strConceito", pattern, MatchMode.ANYWHERE);
		return getListByCriterion(criterion);
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IConceitoDAO#getStartedWith(java.lang.String, int)
	 */
	@Override
	public List<Conceito> getStartedWith(String pattern, int limit) {
		Criterion criterion = Restrictions.ilike("strConceito", pattern, MatchMode.ANYWHERE);
		return getListByCriterion(criterion, limit);
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IConceitoDAO#getExact(java.lang.String)
	 */
	@Override
	public Conceito getExact(String pattern) {
		Criterion criterion = Restrictions.ilike("strConceito", pattern, MatchMode.EXACT);
		List<Conceito> conceitos = getListByCriterion(criterion);
		if(conceitos == null || conceitos.size() == 0){
			return null;
		}
		
		return conceitos.get(0);
	}
}
