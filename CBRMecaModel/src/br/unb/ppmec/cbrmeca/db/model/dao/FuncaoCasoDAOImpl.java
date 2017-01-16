/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoCasoDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncaoCasoDAOImpl.
 */
public class FuncaoCasoDAOImpl extends DaoBase<FuncaoCaso> implements IFuncaoCasoDAO{
	
	/**
	 * Instantiates a new funcao caso dao impl.
	 */
	public FuncaoCasoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoCasoDAO#getByIdFuncaoCaso(java.lang.Integer)
	 */
	@Override
	public FuncaoCaso getByIdFuncaoCaso(Integer idFuncaoCaso) {
		Criterion criterion1 = Restrictions.eq("idFuncaoCaso", idFuncaoCaso);
		return getListByCriterion(criterion1).get(0);
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoCasoDAO#getByIdFuncaoPai(java.lang.Integer)
	 */
	@Override
	public List<FuncaoCaso> getByIdFuncaoPai(Integer idFuncaoPai) {
		Criterion criterion1 = Restrictions.eq("idFuncaoPai", idFuncaoPai);
		return getListByCriterion(criterion1);
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoCasoDAO#getByIdType(java.lang.Integer)
	 */
	@Override
	public List<FuncaoCaso> getByIdType(Integer tipo_funcao) {
		Criterion criterion1 = Restrictions.eq("tipoFuncao", tipo_funcao);
		return getListByCriterion(criterion1);
	}
	
	/* (non-Javadoc)
	 * @see br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoCasoDAO#getByIdFuncao(java.lang.Integer)
	 */
	@Override
	public List<FuncaoCaso> getByIdFuncao(Integer id_funcao) {
		Criterion criterion1 = Restrictions.eq("idFuncao", id_funcao);
		return getListByCriterion(criterion1);
	}
	
	/**
	 * Gets the by id funcao.
	 *
	 * @param id_funcao the id_funcao
	 * @param elementar the elementar
	 * @return the by id funcao
	 */
	public List<FuncaoCaso> getByIdFuncao(Integer id_funcao, boolean elementar) {
		Criterion criterion1 = Restrictions.eq("idFuncao", id_funcao);
		Criterion criterion2 = Restrictions.eq("bolElementar", elementar);
		Criterion criterion3 = Restrictions.and(criterion1, criterion2);
		return getListByCriterion(criterion3);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
		for(FuncaoCaso fc : fcDAO.getByIdFuncao(4197, false)){
			System.out.println(fc.getIdFuncaoCaso());
		}
	}
}
