package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.ICasoDAO;

public class CasoDAOImpl extends DaoBase<Caso> implements ICasoDAO{
	
	public CasoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public List<Caso> getStartedWith(String pattern) {
		Criterion criterion1 = Restrictions.ilike("strTitulo", pattern, MatchMode.ANYWHERE);
		Criterion criterion2 = Restrictions.ilike("strDescricao", pattern, MatchMode.ANYWHERE);
		Criterion criterion3 = Restrictions.or(criterion1, criterion2);
		return getListByCriterion(criterion3);
	}
	
	@Override
	public Caso getExactTitle(String pattern) {
		Criterion criterion1 = Restrictions.ilike("strTitulo", pattern, MatchMode.EXACT);
		List<Caso> list = getListByCriterion(criterion1);
		
		if(list == null || list.size() == 0){
			return null;
		}
		
		return list.get(0);
	}

	@Override
	public Caso getByIDFuncaoCasoRaiz(int id) {
		Criterion criterion1 = Restrictions.eq("idFuncaoGeral", id);
		List<Caso> list = getListByCriterion(criterion1);
		
		if(list == null || list.size() == 0){
			return null;
		}
		
		return getListByCriterion(criterion1).get(0);
	}
}
