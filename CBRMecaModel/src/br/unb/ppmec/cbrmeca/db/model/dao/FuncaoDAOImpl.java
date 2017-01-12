package br.unb.ppmec.cbrmeca.db.model.dao;

import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IFuncaoDAO;

public class FuncaoDAOImpl extends DaoBase<Funcao> implements IFuncaoDAO{
	
	public FuncaoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public Funcao getByStrDescriptor(String pattern) {
		String[] splitted = pattern.split(" ", 2);
		
		Criterion criterion3;
		Criterion criterion1 = Restrictions.ilike("strFuncaoVerbo", splitted[0], MatchMode.ANYWHERE);
		if(splitted.length > 1){
			Criterion criterion2 = Restrictions.ilike("strFuncaoObjeto", splitted[1], MatchMode.ANYWHERE);
			criterion3 = Restrictions.and(criterion1, criterion2);
		}else{
			criterion3 = Restrictions.and(criterion1);
		}
		
		List<Funcao> list = getListByCriterion(criterion3);
		
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	public List<Funcao> getListByStrDescriptor(String pattern) {
		String[] splitted = pattern.split(Pattern.quote(" "));
		
		Criterion criterion4;
		Criterion criterion1 = Restrictions.ilike("strFuncaoVerbo", splitted[0], MatchMode.ANYWHERE);
		Criterion criterion2 = Restrictions.ilike("strFuncaoObjeto", splitted[0], MatchMode.ANYWHERE);
		
		if(splitted.length > 1){
			Criterion criterion3 = Restrictions.ilike("strFuncaoVerbo", splitted[1], MatchMode.ANYWHERE);
			Criterion criterion5 = Restrictions.ilike("strFuncaoObjeto", splitted[1], MatchMode.ANYWHERE);
			criterion4 = Restrictions.or(criterion1, criterion3, criterion2, criterion5);
		}else{
			criterion4 = Restrictions.or(criterion1, criterion2);
		}
		
		return getListByCriterion(criterion4);
	}
	
	public static void main(String[] args) {
		FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
		List<Funcao> list = fDAO.getListByStrDescriptor("Proteger usuário");
		
		for(Funcao f : list){
			System.out.println(f.getStrFuncaoVerbo() + " " + f.getStrFuncaoObjeto());
		}
		
		System.out.println("----------------");
		
		list = fDAO.getListByStrDescriptor("Proteger máquina");
		
		for(Funcao f : list){
			System.out.println(f.getStrFuncaoVerbo() + " " + f.getStrFuncaoObjeto());
		}
		
		System.exit(0);
	}
}
