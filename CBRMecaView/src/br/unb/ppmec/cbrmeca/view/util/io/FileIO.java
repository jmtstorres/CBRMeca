/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.util.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.zeroturnaround.zip.ZipUtil;
import org.zeroturnaround.zip.commons.FileUtils;

import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.ImagemConceito;
import br.unb.ppmec.cbrmeca.db.model.Solucao;
import br.unb.ppmec.cbrmeca.db.model.dao.CasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ImagemConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.SolucaoDAOImpl;
import br.unb.ppmec.cbrmeca.xml.vo.CasoVO;
import br.unb.ppmec.cbrmeca.xml.vo.ConceitoVO;
import br.unb.ppmec.cbrmeca.xml.vo.FuncaoCasoVO;
import br.unb.ppmec.cbrmeca.xml.vo.FuncaoVO;
import br.unb.ppmec.cbrmeca.xml.vo.ImagemConceitoVO;
import br.unb.ppmec.cbrmeca.xml.vo.SolucaoVO;

// TODO: Auto-generated Javadoc
/**
 * The Class FileIO.
 */
public class FileIO {
	
	/** The caso dao. */
	private static CasoDAOImpl casoDAO = new CasoDAOImpl();
	
	/** The fc dao. */
	private static FuncaoCasoDAOImpl fcDAO = new FuncaoCasoDAOImpl();
	
	/** The dao. */
	private static FuncaoDAOImpl fDAO = new FuncaoDAOImpl();
	
	/** The s dao. */
	private static SolucaoDAOImpl sDAO = new SolucaoDAOImpl();
	
	/** The c dao. */
	private static ConceitoDAOImpl cDAO = new ConceitoDAOImpl();
	
	/** The img dao. */
	private static ImagemConceitoDAOImpl imgDAO = new ImagemConceitoDAOImpl();
	
	/** The imgs to copy. */
	private static List<String> imgsToCopy = new ArrayList<String>();
	
	/** The solucoes. */
	private static HashMap<FuncaoCaso, List<SolucaoVO>> solucoes = new HashMap<FuncaoCaso,List<SolucaoVO>>();
	
	/** The id caso. */
	private static int idCaso = 0;

	/**
	 * Write to disk.
	 *
	 * @param caso the caso
	 * @param path the path
	 * @throws JAXBException the JAXB exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void writeToDisk(CasoVO caso, String path) throws JAXBException, IOException{
        JAXBContext context = JAXBContext.newInstance(CasoVO.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        File f = new File(path);
        if(!f.exists()){
        	f.createNewFile();
        }
        
        FileOutputStream fos = new FileOutputStream(f);
        m.marshal(caso, fos);
        
        fos.close();
	}
	
	/**
	 * Load from disk.
	 *
	 * @param filename the filename
	 * @return the caso vo
	 * @throws JAXBException the JAXB exception
	 * @throws FileNotFoundException the file not found exception
	 */
	private static CasoVO loadFromDisk(String filename) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(CasoVO.class);
		Unmarshaller um = context.createUnmarshaller();
		CasoVO caso = (CasoVO) um.unmarshal(new FileReader(filename));
		return caso;
	}
	
	/**
	 * Save case on xml file.
	 *
	 * @param caso the caso
	 * @param path the path
	 * @throws JAXBException the JAXB exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void saveCaseOnXMLFile(Caso caso, String path) throws JAXBException, IOException{
		idCaso = caso.getIdCaso();
		
		imgsToCopy.clear();
		
		FuncaoCaso fcGeral = fcDAO.get(caso.getIdFuncaoGeral());
		FuncaoCasoVO fcGeralVO = fillFcVO(fcGeral);
		
		fcGeralVO.setChildren(getFuncaoVOTree(fcGeral));
		
		CasoVO casoVO = new CasoVO(	caso.getStrTitulo(), 
									caso.getStrDescricao(), 
									fcGeralVO,
									caso.getIdTipo(),
									caso.getIntAno());
		
		writeToDisk(casoVO, path);
	}
	
	/**
	 * Gets the funcao vo tree.
	 *
	 * @param funcaoPai the funcao pai
	 * @return the funcao vo tree
	 */
	private static List<FuncaoCasoVO> getFuncaoVOTree(FuncaoCaso funcaoPai){
		List<FuncaoCaso> children = fcDAO.getByIdFuncaoPai(funcaoPai.getIdFuncaoCaso());
		List<FuncaoCasoVO> childrenVO = new ArrayList<FuncaoCasoVO>();
		
		for(FuncaoCaso fc : children){
			FuncaoCasoVO fcGeralVO = fillFcVO(fc);
			
			fcGeralVO.setChildren(getFuncaoVOTree(fc));
			
			List<Solucao> solucoes = sDAO.getByIdFuncaoECaso(fc.getIdFuncao(), idCaso);
			List<SolucaoVO> solucoesVO = new ArrayList<SolucaoVO>();
			for(Solucao sol : solucoes){
				solucoesVO.add(fillSVO(sol));
			}
			fcGeralVO.setSolucoes(solucoesVO);
			
			childrenVO.add(fcGeralVO);
		}
		
		return childrenVO;
	}
	
	/**
	 * Fill svo.
	 *
	 * @param sol the sol
	 * @return the solucao vo
	 */
	private static SolucaoVO fillSVO(Solucao sol){
		SolucaoVO solVO = new SolucaoVO();
		Conceito c = cDAO.get(sol.getIdConceito());
		solVO.setConceito(fillCVO(c));
		return solVO;
	}
	
	/**
	 * Fill cvo.
	 *
	 * @param c the c
	 * @return the conceito vo
	 */
	private static ConceitoVO fillCVO(Conceito c){
		ConceitoVO cVO = new ConceitoVO();
		cVO.setDescConceito(c.getDescConceito());
		cVO.setStrConceito(c.getStrConceito());
		cVO.setImgConceito(fillImgCOnceitoVO(imgDAO.get(c.getImgConceito())));
		return cVO;
	}
	
	/**
	 * Fill img c onceito vo.
	 *
	 * @param imgC the img c
	 * @return the imagem conceito vo
	 */
	private static ImagemConceitoVO fillImgCOnceitoVO(ImagemConceito imgC){
		ImagemConceitoVO imgVO = new ImagemConceitoVO();
		imgVO.setDirImagemConceito(imgC.getDirImagemConceito());
		imgsToCopy.add(imgC.getDirImagemConceito());
		System.out.println("Imgs: " + imgsToCopy.size());
		return imgVO;
	}
	
	/**
	 * Fill fc vo.
	 *
	 * @param fc the fc
	 * @return the funcao caso vo
	 */
	private static FuncaoCasoVO fillFcVO(FuncaoCaso fc){
		Funcao fGeral = fDAO.get(fc.getIdFuncao());
		FuncaoVO fVO = new FuncaoVO(fGeral.getStrFuncaoVerbo(), fGeral.getStrFuncaoObjeto());
		
		FuncaoCasoVO fcVO = new FuncaoCasoVO();
		fcVO.setBolElementar(fc.getBolElementar());
		fcVO.setFuncao(fVO);
		fcVO.setTipoEfeito(fc.getTipoEfeito());
		fcVO.setTipoEscopo(fc.getTipoEscopo());
		fcVO.setTipoFuncao(fc.getTipoFuncao());
		fcVO.setTipoNecessidade(fc.getTipoNecessidade());
		
		return fcVO;
	}
	
	/**
	 * Save case file.
	 *
	 * @param filename the filename
	 * @param caso the caso
	 * @param functionTree the function tree
	 * @param matrix the matrix
	 * @throws JAXBException the JAXB exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void saveCaseFile(String filename, Caso caso, BufferedImage functionTree, BufferedImage matrix) throws JAXBException, IOException{
		File f = new File("temp");
		if(!f.exists()){
			f.mkdirs();
		}
		
		File fcon = new File("temp" + File.separator + "conceitos");
		if(!fcon.exists()){
			fcon.mkdirs();
		}
		
		File fimg = new File("temp" + File.separator + "imagens");
		if(!fimg.exists()){
			fimg.mkdirs();
		}
		
		saveCaseOnXMLFile(caso, f.getAbsolutePath() + File.separator + "descriptor.xml");
		if(functionTree != null){
			PanelImageExport.saveimage(functionTree, new File(fimg.getAbsolutePath() + File.separator + "function.png"));
		}
		
		for(String img : imgsToCopy){
			String from = "images" + File.separator + "concepts" + File.separator;
			String to = fcon.getAbsolutePath() + File.separator;
			System.out.println("To: " + to);
			System.out.println("From: " + from);
			FileUtils.copyFile(new File(from + img), new File(to + img));
		}
		
		if(matrix != null){
			PanelImageExport.saveimage(matrix, new File(fimg.getAbsolutePath() + File.separator + "matrix.png"));
		}
		
		compressDir(f.getAbsolutePath(), filename);
		
		FileUtils.deleteDirectory(f);
	}
	
	/**
	 * Load case file.
	 *
	 * @param file the file
	 * @return the caso
	 * @throws JAXBException the JAXB exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Caso loadCaseFile(String file) throws JAXBException, IOException{
		solucoes.clear();
		
		File fcon = new File("temp" + File.separator + "conceitos");
		if(!fcon.exists()){
			fcon.mkdirs();
		}
		
		ZipUtil.unpack(new File(file), new File("temp"));
		
		CasoVO cVO = loadFromDisk("temp" + File.separator + "descriptor.xml");
		
		FuncaoCaso fCaso = saveCaseOnDB(cVO);
		
		Caso caso = new Caso();
		caso.setIdFuncaoGeral(fCaso.getIdFuncaoCaso());
		caso.setIdTipo(cVO.getIdTipo());
		caso.setIntAno(cVO.getIntAno());
		caso.setStrDescricao(cVO.getStrDescricao());
		caso.setStrTitulo(cVO.getStrTitulo());
		
		casoDAO.save(caso);
		
		idCaso = caso.getIdCaso();
		
		for(FuncaoCaso fc : solucoes.keySet()){
			List<SolucaoVO> sol = solucoes.get(fc);
			saveSolutions(sol, fc);
		}
		
		return caso;
	}
	
	/**
	 * Save case on db.
	 *
	 * @param casoVO the caso vo
	 * @return the funcao caso
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static FuncaoCaso saveCaseOnDB(CasoVO casoVO) throws IOException{
		FuncaoCasoVO fGeralVO = casoVO.getFuncaoGeral();
		return saveCaseTree(fGeralVO, 0);
	}
	
	/**
	 * Save case tree.
	 *
	 * @param fcVO the fc vo
	 * @param idFuncaoPai the id funcao pai
	 * @return the funcao caso
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static FuncaoCaso saveCaseTree(FuncaoCasoVO fcVO, int idFuncaoPai) throws IOException{
		FuncaoVO fVO = fcVO.getFuncao();
		
		Funcao f = fDAO.getByStrDescriptor(fcVO.getFuncao().getStrFuncaoVerbo() + " " + fcVO.getFuncao().getStrFuncaoObjeto());
		if(f == null){
			f = new Funcao(0, fVO.getStrFuncaoVerbo(), fVO.getStrFuncaoObjeto());
			fDAO.save(f);
		}
		
		FuncaoCaso fCaso = new FuncaoCaso();
		fCaso.setBolElementar(fcVO.isBolElementar());
		fCaso.setIdFuncao(f.getIdFuncao());
		fCaso.setIdFuncaoPai(idFuncaoPai);
		fCaso.setTipoEfeito(fcVO.getTipoEfeito());
		fCaso.setTipoEscopo(fcVO.getTipoEscopo());
		fCaso.setTipoFuncao(fcVO.getTipoFuncao());
		fCaso.setTipoNecessidade(fcVO.getTipoNecessidade());
		
		fcDAO.save(fCaso);
		
		List<SolucaoVO> solucoesVO = fcVO.getSolucoes();
		if(solucoesVO != null && solucoesVO.size() > 0){
			solucoes.put(fCaso, solucoesVO);
		}
		
		List<FuncaoCasoVO> children = fcVO.getChildren();
		if(children != null){
			for(FuncaoCasoVO fcasoVO : children){
				saveCaseTree(fcasoVO, fCaso.getIdFuncaoCaso());
			}
		}
		
		return fCaso;
	}
	
	/**
	 * Save solutions.
	 *
	 * @param solsVO the sols vo
	 * @param fc the fc
	 */
	private static void saveSolutions(List<SolucaoVO> solsVO, FuncaoCaso fc){
		for(SolucaoVO solVO :  solsVO){
			Conceito conceito = cDAO.getExact(solVO.getConceito().getStrConceito());
			if(conceito == null){
				conceito = new Conceito();
				conceito.setDescConceito(solVO.getConceito().getDescConceito());
				conceito.setStrConceito(solVO.getConceito().getStrConceito());
				
				ImagemConceitoVO imgcVO = solVO.getConceito().getImgConceito();
				
				Date date = new Date();
				String fileSaveName = Long.toString(date.getTime()) + ".jpg";
				FileOutputStream fos;
				try {
					String current = System.getProperty("user.dir") + File.separator;
					String tempImageDir = current + "temp" + File.separator + "conceitos" + File.separator + imgcVO.getDirImagemConceito();
					String outputStr = current + "images" + File.separator + "concepts" + File.separator + fileSaveName;
					
					fos = new FileOutputStream(new File(outputStr));
					FileUtils.copy(	new File(tempImageDir), 
									fos);
					fos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	
				ImagemConceito imgConceito = new ImagemConceito();
				imgConceito.setDirImagemConceito(fileSaveName);
				
				imgDAO.save(imgConceito);
				
				conceito.setImgConceito(imgConceito.getIdImagem());
				cDAO.save(conceito);
			}
			
			Solucao sol = new Solucao();
			sol.setIdFuncao(fc.getIdFuncao());
			sol.setIdConceito(conceito.getIdConceito());
			sol.setIdCaso(idCaso);
			
			sDAO.save(sol);
		}
	}
	
	/**
	 * Compress dir.
	 *
	 * @param dirIn the dir in
	 * @param FileOut the file out
	 */
	private static void compressDir(String dirIn, String FileOut) {
		ZipUtil.pack(new File(dirIn), new File(FileOut));
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		/*CasoDAOImpl cDAO = new CasoDAOImpl();
		
		Caso caso = cDAO.get(4851);
		if(caso == null){
			System.out.println("Não encontrou o caso.");
			System.exit(0);
		}
		
		try {
			FileIO.saveCaseFile("testeCaso.cbm", caso, null, null);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		try {
			Caso caso = FileIO.loadCaseFile("teste.cbm");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}
