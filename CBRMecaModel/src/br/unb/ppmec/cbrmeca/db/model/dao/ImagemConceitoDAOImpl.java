/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.dao;

import br.unb.ppmec.cbrmeca.db.hibernate.HibernateUtil;
import br.unb.ppmec.cbrmeca.db.model.ImagemConceito;
import br.unb.ppmec.cbrmeca.model.interfaces.DaoBase;
import br.unb.ppmec.cbrmeca.model.interfaces.IImagemConceitoDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ImagemConceitoDAOImpl.
 */
public class ImagemConceitoDAOImpl extends DaoBase<ImagemConceito> implements IImagemConceitoDAO{
	
	/**
	 * Instantiates a new imagem conceito dao impl.
	 */
	public ImagemConceitoDAOImpl() {
		setSession(HibernateUtil.getSessionFactory());
	}
}
