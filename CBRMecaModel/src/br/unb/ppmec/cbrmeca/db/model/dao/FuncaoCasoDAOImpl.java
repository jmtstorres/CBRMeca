package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoCasoDAO;

public class FuncaoCasoDAOImpl extends DaoBase<FuncaoCaso> implements IFuncaoCasoDAO{
	
	public FuncaoCasoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public FuncaoCaso getByIdFuncaoCaso(Integer idFuncaoCaso) {
		Criterion criterion1 = Restrictions.eq("idFuncaoCaso", idFuncaoCaso);
		return getListByCriterion(criterion1).get(0);
	}
	
	@Override
	public List<FuncaoCaso> getByIdFuncaoPai(Integer idFuncaoPai) {
		Criterion criterion1 = Restrictions.eq("idFuncaoPai", idFuncaoPai);
		return getListByCriterion(criterion1);
	}
	
	@Override
	public List<FuncaoCaso> getByIdType(Integer tipo_funcao) {
		Criterion criterion1 = Restrictions.eq("tipoFuncao", tipo_funcao);
		return getListByCriterion(criterion1);
	}
	
	@Override
	public List<FuncaoCaso> getByIdFuncao(Integer id_funcao) {
		Criterion criterion1 = Restrictions.eq("idFuncao", id_funcao);
		return getListByCriterion(criterion1);
	}
	
	public List<FuncaoCaso> getByIdFuncao(Integer id_funcao, boolean elementar) {
		Criterion criterion1 = Restrictions.eq("idFuncao", id_funcao);
		Criterion criterion2 = Restrictions.eq("bolElementar", elementar);
		Criterion criterion3 = Restrictions.and(criterion1, criterion2);
		return getListByCriterion(criterion3);
	}
	
	public static void main(String[] args) {
		FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
		for(FuncaoCaso fc : fcDAO.getByIdFuncao(4197, false)){
			System.out.println(fc.getIdFuncaoCaso());
		}
	}
}
