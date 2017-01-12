package br.com.ppmec.cbrmeca.control;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ppmec.cbrmeca.control.models.ICBREngine;
import br.com.ppmec.cbrmeca.control.util.WordUtil;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionEffect;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionNecessity;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionScope;
import br.unb.ppmec.cbrmeca.db.constants.EFunctionType;
import br.unb.ppmec.cbrmeca.db.constants.EProjectType;
import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.Solucao;
import br.unb.ppmec.cbrmeca.db.model.dao.CasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.SolucaoDAOImpl;

public final class CBREngine implements ICBREngine {
	
	private Logger logger = LoggerFactory.getLogger(CBREngine.class);

	private static final float WHEIGHT_SCOPE = 10;
	private static final float WHEIGHT_CASETYPE = 10;
	private static final float WHEIGHT_TYPE = 10;
	private static final float WHEIGHT_NEED = 10;
	private static final float WHEIGHT_EFFECT = 10;
	
	private Word2Vec vec = null;
	
	private float targetSimilarity = 0.7f;
	
	private static final CBREngine INSTANCE = new CBREngine();
	
	public static final CBREngine getInstance(){
		return INSTANCE;
	}
	
	/**
	 * 
	 */
	private CBREngine() {
	}
	
	public void initialize(boolean usesWordModel, String path){
		logger.info("Inicializando...");
		if(usesWordModel){
			try {
				vec = WordVectorSerializer.loadFullModel(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				vec = null;
				logger.error("Não foi possível encontrar o modelo gramatical, não será utilizado nesta sessão.");
			}
		}
		logger.info("Inicializado.");
	}

	public static void main(String[] args) {
		CBREngine engine = CBREngine.getInstance();
		
		engine.initialize(true, "G:\\[Estudo]\\Mestrado\\00 - Projeto\\workspace\\Word2Vec_Test\\trained_v2.dat");

		FuncaoCaso fc = new FuncaoCaso();
		fc.setBolElementar(true);
		fc.setTipoFuncao(EFunctionType.TYPE_4.getId());
		fc.setTipoEfeito(EFunctionEffect.REGULAR.getId());
		fc.setTipoNecessidade(EFunctionNecessity.NECESSARY.getId());
		fc.setTipoEscopo(EFunctionScope.INFORMATION.getId() + EFunctionScope.POWER.getId());
		
		List<FuncaoCaso> similares = engine.retrieveSimilar(fc, EProjectType.Autonomous_Systems_Control.getId(), "colocar água");
		
		similares = engine.retrieveSimilar(fc, EProjectType.Autonomous_Systems_Control.getId(), "inserir água");
		similares = engine.retrieveSimilar(fc, EProjectType.Autonomous_Systems_Control.getId(), "alimentar água");
		similares = engine.retrieveSimilar(fc, EProjectType.Autonomous_Systems_Control.getId(), "despejar água");
		
		Map<Integer, Integer> solucoesRankeadas = engine.getSolutionsForCase(similares);
		
		engine.printMap(solucoesRankeadas);
		
		System.exit(0);
	}
	
	@Override
	public List<FuncaoCaso> retrieveSimilar(FuncaoCaso fCaso, int idTipoCaso, String fDescriptor) {

		//Verificar funções exatamente iguais gramaticalmente
		List<FuncaoCaso> casos = checkExactMatch(fDescriptor);
		if(casos.size() > 0){
			List<FuncaoCaso> weighted = new ArrayList<FuncaoCaso>();
			for(FuncaoCaso funcao : casos){
				if(!funcao.getBolElementar()){
					continue;
				}
				
				float similarity = weightSimilarity(funcao, fCaso, idTipoCaso);
				if(similarity > targetSimilarity){
					weighted.add(funcao);
				}
			}
			
			if(weighted.size() > 0){
				return weighted;
			}
		}
		
		//CasoVO exato não existe, procurar semelhantes
		logger.info("Sem funções exatamente iguais, procurando alternativas...");
		casos = retrieveAlternatives(fCaso, idTipoCaso, fDescriptor);

		return casos;
	}

	private List<FuncaoCaso> retrieveAlternatives(FuncaoCaso fCaso, int idTipoCaso, String fDescriptor){

		FuncaoCasoDAOImpl fCasoDAO = new FuncaoCasoDAOImpl();

		List<String> wordBag = WordUtil.similarWords(vec, fCaso, fDescriptor);
		for(String str : wordBag){
			System.out.println(str);
		}

		List<FuncaoCaso> funcoes = fCasoDAO.loadAll();

		List<FuncaoCaso> funcoesSimilares = new ArrayList<FuncaoCaso>();
		for (FuncaoCaso funcao : funcoes) {
			if (!funcao.getBolElementar()) {
				continue;
			}
			
			if(vec != null){
				if (!similarFunctions(funcao, wordBag)) {
					continue;
				}
			}

			float similarity = weightSimilarity(funcao, fCaso, idTipoCaso);

			if (similarity > targetSimilarity) {
				System.out.println("Similaridade: " + similarity);
				logger.info("Similaridade: " + similarity);
				funcoesSimilares.add(funcao);
			}
		}

		if (funcoesSimilares.size() == 0) {
			return funcoesSimilares;
		}
		
		printFound(funcoesSimilares);

		return funcoesSimilares;
	}
	

	
	private List<FuncaoCaso> checkExactMatch(String strFunctionDescriptor){
		FuncaoDAOImpl fDao = new FuncaoDAOImpl();
		FuncaoCasoDAOImpl fcDao = new FuncaoCasoDAOImpl();
		
		List<FuncaoCaso> fCasos = new ArrayList<FuncaoCaso>();
		
		List<Funcao> funcoes = fDao.getListByStrDescriptor(strFunctionDescriptor);
		for(Funcao f : funcoes){
			fCasos.addAll(fcDao.getByIdFuncao(f.getIdFuncao()));
		}
		
		return fCasos;
	}
	
	private boolean similarFunctions(FuncaoCaso fCaso, List<String> toMatch){
		int score = 0;
		
		for (String keyStr : WordUtil.keywords(fCaso)) {
			logger.info("\tKey: " + keyStr);
			for (String word : toMatch) {
					logger.info("\t\tWord: " + word);
				if (word.equalsIgnoreCase(keyStr)) {
					score++;
				}
			}
		}
		
		return (score > 0);
	}
	
	private float weightSimilarity(FuncaoCaso funcao, FuncaoCaso funcaoToMatch, int idTipoCaso){
		
		CasoDAOImpl cDAO = new CasoDAOImpl();

		int idTipoFuncao = funcaoToMatch.getTipoFuncao();
		int idTipoEfeito = funcaoToMatch.getTipoEfeito();
		int idTipoNecessidade = funcaoToMatch.getTipoNecessidade();
		
		int idCasoPai = getIDFuncaoCasoRaiz(funcao.getIdFuncaoCaso());
		Caso caso = cDAO.getByIDFuncaoCasoRaiz(idCasoPai);
		if(caso == null){
			return 0;
		}
		
		float similarity = 0.0f;
		
		logger.info("Escopo funcao: " + funcao.getTipoEscopo() + " | " + funcaoToMatch.getTipoEscopo());
		
		similarity = (	(matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.POWER.getId()))*WHEIGHT_SCOPE +
						(matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.INFORMATION.getId()))*WHEIGHT_SCOPE +
						(matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.CONTROL.getId()))*WHEIGHT_SCOPE +
						(matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.MECHANIC.getId()))*WHEIGHT_SCOPE + 
						(funcao.getTipoFuncao() == idTipoFuncao ? 1 : 0)*WHEIGHT_TYPE +
						(funcao.getTipoNecessidade() == idTipoNecessidade ? 1 : 0)*WHEIGHT_NEED	+
						(funcao.getTipoEfeito() == idTipoEfeito ? 1 : 0)*WHEIGHT_EFFECT + 
						(caso.getIdTipo() == idTipoCaso ? 1 : 0)*WHEIGHT_CASETYPE)
						/
						(4*WHEIGHT_SCOPE + 
						 WHEIGHT_TYPE + 
						 WHEIGHT_NEED + 
						 WHEIGHT_EFFECT + 
						 WHEIGHT_CASETYPE);
		
		System.out.println("==================================================================");
		System.out.println("SCOPE: " + (matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.POWER.getId()))*WHEIGHT_SCOPE);
		System.out.println("SCOPE: " + (matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.INFORMATION.getId()))*WHEIGHT_SCOPE);
		System.out.println("SCOPE: " + (matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.CONTROL.getId()))*WHEIGHT_SCOPE);
		System.out.println("SCOPE: " + (matches(funcao.getTipoEscopo(), funcaoToMatch.getTipoEscopo(), EFunctionScope.MECHANIC.getId()))*WHEIGHT_SCOPE); 
		System.out.println("TYPE: " + (funcao.getTipoFuncao() == idTipoFuncao ? 1 : 0)*WHEIGHT_TYPE);
		System.out.println("NEED: " + (funcao.getTipoNecessidade() == idTipoNecessidade ? 1 : 0)*WHEIGHT_NEED);
		System.out.println("EFFECT: " + (funcao.getTipoEfeito() == idTipoEfeito ? 1 : 0)*WHEIGHT_EFFECT);
		System.out.println("CASETYPE: " + (caso.getIdTipo() == idTipoCaso ? 1 : 0)*WHEIGHT_CASETYPE);
		
		System.out.println("Similaridade: " + similarity);
		logger.info("Similaridade: " + similarity);
		
		return similarity;
	}
	
	private void printFound(List<FuncaoCaso> funcoesSimilares){
		
		CasoDAOImpl cDAO = new CasoDAOImpl();
		FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
		
		logger.info("RECUPERADAS:" + funcoesSimilares.size()
				+ "===============================================");
		for (FuncaoCaso funcao : funcoesSimilares) {
			int idCasoPai = getIDFuncaoCasoRaiz(funcao.getIdFuncaoCaso());
			Caso caso = cDAO.getByIDFuncaoCasoRaiz(idCasoPai);

			Funcao f = fDAO.get(funcao.getIdFuncao());
			logger.info("\tFuncao: " + f.getStrFuncaoVerbo() + " "
					+ f.getStrFuncaoObjeto());
			logger.info("\tParametros: " + funcao.getTipoFuncao() + ","
					+ funcao.getTipoEfeito() + ","
					+ funcao.getTipoNecessidade() + ","
					+ isActive(funcao.getTipoEscopo(), 1) + ","
					+ isActive(funcao.getTipoEscopo(), 2) + ","
					+ isActive(funcao.getTipoEscopo(), 4) + ","
					+ isActive(funcao.getTipoEscopo(), 8) + ","
					+ caso.getIdTipo());
		}
	}

	@Override
	public Map<Integer, Integer> getSolutionsForCase(List<FuncaoCaso> similarCases) {
		SolucaoDAOImpl solDAO = new SolucaoDAOImpl();
		Map<Integer, Integer> scoreTable = new HashMap<Integer, Integer>();
		CasoDAOImpl cDAO = new CasoDAOImpl();
		
		for(FuncaoCaso fCaso : similarCases){
			int idCasoPai = getIDFuncaoCasoRaiz(fCaso.getIdFuncaoCaso());
			Caso caso = cDAO.getByIDFuncaoCasoRaiz(idCasoPai);
			if(caso == null){
				continue;
			}
			
			List<Solucao> solucoesCaso = solDAO.getByIdFuncaoECaso(fCaso.getIdFuncao(), caso.getIdCaso());
			for(Solucao sol : solucoesCaso){
				if(scoreTable.containsKey(sol.getIdConceito())){
					Integer score = scoreTable.get(sol.getIdConceito());
					scoreTable.put(sol.getIdConceito(), score + 1);
				}else{
					scoreTable.put(sol.getIdConceito(), 1);
				}
			}
		}

		Map<Integer, Integer> sortedMap = sortByComparator(scoreTable, DESC);
		return sortedMap;
	}
	
	public void printMap(Map<Integer, Integer> map)
    {
		ConceitoDAOImpl cDAO = new ConceitoDAOImpl();
		
		logger.info("Solucoes:===============================================");
        for (Entry<Integer, Integer> entry : map.entrySet())
        {
        	Conceito c = cDAO.get(entry.getKey());
            logger.info("Key : " + c.getStrConceito() + " Value : "+ entry.getValue());
        }
    }
	
	public static boolean ASC = true;
    public static boolean DESC = false;

	private static Map<Integer, Integer> sortByComparator(
			Map<Integer, Integer> unsortMap, final boolean order) {

		List<Entry<Integer, Integer>> list = 
				new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		for (Entry<Integer, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	private int isActive(int escopofuncao, int scopeID) {
		if ((escopofuncao & scopeID) == scopeID) {
			return 1;
		}

		return 0;
	}
	
	private int matches(int scope1, int scope2, int scopeID) {
		boolean matches = isActive(scope1, scopeID) == isActive(scope2, scopeID);
		logger.info("Scope1: " + scope1 + " | Scope2: " + scope2 + "| scope: " + scopeID + " | result: " + (matches ? 1 : 0));
		return (matches ? 1 : 0);
	}

	private int getIDFuncaoCasoRaiz(int idFuncaoCaso) {
		FuncaoCasoDAOImpl fdao = new FuncaoCasoDAOImpl();
		FuncaoCaso fCaso = fdao.getByIdFuncaoCaso(idFuncaoCaso);
		if (fCaso.getIdFuncaoPai() == 0) {
			return fCaso.getIdFuncaoCaso();
		}

		return getIDFuncaoCasoRaiz(fCaso.getIdFuncaoPai());
	}
}
