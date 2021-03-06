package br.com.ppmec.cbrmeca.control.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;

/**
 * Classe WordUtil.
 * @author Joao Marcelo
 */
public final class WordUtil {
	
	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(WordUtil.class);
	
	/**
	 * Retorna as palavras chave do descritor da funcao.
	 *
	 * @param fCaso - funcao caso
	 * @return Set de palavras chave
	 */
	public static Set<String> keywords(FuncaoCaso fCaso) {
		Set<String> words = new LinkedHashSet<>();

		FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
		Funcao funcaoEntrada = fDAO.get(fCaso.getIdFuncao());

		String phrase = funcaoEntrada.getStrFuncaoVerbo() + " "	+ funcaoEntrada.getStrFuncaoObjeto();
		logger.info(phrase + ": ");
		String[] values = phrase.split("[ /]");
		for (String value : values) {
			if (value.length() > 3) {
				words.add(value);
			}
		}

		return words;
	}
	
	/**
	 * Retorna as palavras chave do descritor da funcao.
	 *
	 * @param fDescriptor - descritor da funcao caso
	 * @return Set de palavras chave
	 */
	public static Set<String> keywords(String fDescriptor) {
		Set<String> words = new LinkedHashSet<>();

		String[] values = fDescriptor.split("[ /]");
		for (String value : values) {
			if (value.length() > 3) {
				words.add(value);
			}
		}

		return words;
	}
	
	/**
	 * Busca por palavras similares.
	 *
	 * @param vec - vetor de similaridade gramatical
	 * @param fCaso - funcao caso
	 * @param fDescriptor - descritor da funcao
	 * @return lista de palavras similares
	 */
	public static List<String> similarWords(Word2Vec vec, FuncaoCaso fCaso, String fDescriptor){
		List<String> wordBag = new ArrayList<String>();
		if(vec != null){
			Set<String> keywords;
			if(fCaso.getIdFuncao() == null || fCaso.getIdFuncao() == 0){
				keywords = keywords(fDescriptor);
			}else{
				keywords = keywords(fCaso);
			}
			
			for (String keyWord : keywords) {
				System.out.println("Key-------------------------" + keyWord);
				Collection<String> col = vec.wordsNearest(keyWord, 20);
				for(String str : col){
					System.out.println(str);
				}
				wordBag.addAll(col);
			}
			
			wordBag.addAll(keywords);
		}
		
		return wordBag;
	}
}
