package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Solucao;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.ISolucaoDAO;

public class SolucaoDAOImpl extends DaoBase<Solucao> implements ISolucaoDAO{
	
	public SolucaoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public List<Solucao> getByIdFuncaoECaso(Integer idFuncao, Integer idCaso) {
		
		Criterion criterion1 = Restrictions.eq("idFuncao", idFuncao);
		Criterion criterion2 = Restrictions.eq("idCaso", idCaso);
		Criterion criterion3 = Restrictions.and(criterion1, criterion2);
		
		return getListByCriterion(criterion3);
	}
	
	public static void main(String[] args) {
		SolucaoDAOImpl dao = new SolucaoDAOImpl();
		List<Solucao> list = dao.getByIdFuncaoECaso(3707, 3963);
		for(Solucao sol : list){
			System.out.println(sol.getIdConceito());
		}
	}
}
