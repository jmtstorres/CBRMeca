/*
 * 
 */
package br.unb.ppmec.cbrmeca.db.model.xml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.unb.ppmec.cbrmeca.db.model.Caso;
import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.db.model.Funcao;
import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.ImagemConceito;
import br.unb.ppmec.cbrmeca.db.model.dao.CasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ConceitoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.ImagemConceitoDAOImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLImport.
 */
public class XMLImport {

	/** The Constant IMAGE_FOLDER. */
	public static final String IMAGE_FOLDER = "images" + File.separator
			+ "concepts" + File.separator;
	
	/** The caso. */
	private Caso caso = null;
	
	/** The xml file. */
	private File fXmlFile = null;
	
	/** The level. */
	private int level = 0;

	/**
	 * Read from xml.
	 *
	 * @param xmlFile the xml file
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void readFromXML(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		fXmlFile = new File(xmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();

		System.out.println("Root element :"
				+ doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("caso");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("ID : "
						+ eElement.getElementsByTagName("id").item(0)
								.getTextContent());
				System.out.println("titulo : "
						+ eElement.getElementsByTagName("titulo").item(0)
								.getTextContent());
				System.out.println("descricao : "
						+ eElement.getElementsByTagName("descricao").item(0)
								.getTextContent());

				caso = new Caso();
				caso.setStrTitulo(eElement.getElementsByTagName("titulo")
						.item(0).getTextContent());
				caso.setStrDescricao(eElement.getElementsByTagName("descricao")
						.item(0).getTextContent());

				NodeList nListf = eElement.getElementsByTagName("funcoes");

				for (int tempf = 0; tempf < nListf.getLength(); tempf++) {
					Node nNodef = nListf.item(tempf);
					if (nNodef.getNodeType() == Node.ELEMENT_NODE) {
						Element eElementfe = (Element) nNodef;
						
						System.out.println("Funcoes---------------------------------------");

						NodeList nListC = eElementfe.getChildNodes();
						NodeList nListCE = eElementfe.getElementsByTagName("conceito");
						if(nListC.getLength() > 0){
							getFuncao(nListC, null, (nListCE.getLength() > 0));
						}

						if(nListCE.getLength() > 0){
							getConceitos(nListCE);
						}
						
					}
				}
			}
		}
		
		if(caso != null){
			CasoDAOImpl casodao = new CasoDAOImpl();
			casodao.save(caso);
		}
	}
	
	/**
	 * N tabs.
	 *
	 * @return the string
	 */
	private String nTabs(){
		String tabs = "";
		for(int i = 0; i < level; i++){
			tabs = tabs.concat("\t");
		}
		return tabs;
	}

	/**
	 * Gets the funcao.
	 *
	 * @param nListf the n listf
	 * @param fpai the fpai
	 * @param elementar the elementar
	 * @return the funcao
	 */
	private int getFuncao(NodeList nListf, Funcao fpai, boolean elementar) {
		int idFGeral = 0;
		level++;
		for (int tempfe = 0; tempfe < nListf.getLength(); tempfe++) {
			Node nNodefe = nListf.item(tempfe);
			if (nNodefe.getNodeType() == Node.ELEMENT_NODE) {
				Element eElementfe = (Element) nNodefe;
				System.out.println(nTabs() + eElementfe.getAttribute("desc"));

				String strFe = eElementfe.getAttribute("desc");
				if(strFe != null && !strFe.isEmpty()){
					String[] parsedFE = strFe.split(" ");
					
					Funcao fe = new Funcao();
					fe.setStrFuncaoVerbo(parsedFE[0]);
					
					String objetoe = "";
					for (int i = 1; i < parsedFE.length; i++) {
						objetoe = objetoe.concat(parsedFE[i] + " ");
					}
					fe.setStrFuncaoObjeto(objetoe);
					
					FuncaoDAOImpl feDao = new FuncaoDAOImpl();
					feDao.save(fe);
					
					FuncaoCaso funcaoCaso = new FuncaoCaso();
					funcaoCaso.setBolElementar(elementar);
					funcaoCaso.setIdFuncao(fe.getIdFuncao());
					
					if(fpai == null){
						funcaoCaso.setIdFuncaoPai(0);
					}else{
						funcaoCaso.setIdFuncaoPai(fpai.getIdFuncao());
					}
					
					if(funcaoCaso.getIdFuncaoPai() == 0){
						caso.setIdFuncaoGeral(fe.getIdFuncao());
					}
					
					idFGeral = fe.getIdFuncao();
					
					NodeList nListC = eElementfe.getChildNodes();
					NodeList nListCE = eElementfe.getElementsByTagName("conceito");
					if(nListC.getLength() > 0){
						getFuncao(nListC, fe, (nListCE.getLength() > 0));
					}

					if(nListCE.getLength() > 0){
						getConceitos(nListCE);
					}
				}
			}
		}
		level--;
		return idFGeral;
	}
	
	/**
	 * Gets the conceitos.
	 *
	 * @param nListC the n list c
	 * @return the conceitos
	 */
	public void getConceitos(NodeList nListC){
		level++;
		for (int tempc = 0; tempc < nListC.getLength(); tempc++) {
			Node nNodec = nListC.item(tempc);
			if (nNodec.getNodeType() == Node.ELEMENT_NODE) {
				Element eElementc = (Element) nNodec;

				Conceito conceito = new Conceito();
				conceito.setStrConceito(eElementc
						.getAttribute("descri��o"));
				conceito.setDescConceito(eElementc
						.getAttribute("descri��o"));

				ImagemConceito img = new ImagemConceito();
				img.setDirImagemConceito(eElementc
						.getAttribute("imagem"));

				ImagemConceitoDAOImpl imgDao = new ImagemConceitoDAOImpl();
				imgDao.save(img);

				conceito.setImgConceito(img.getIdImagem());
				ConceitoDAOImpl conceitoDAO = new ConceitoDAOImpl();

				List<Conceito> list = conceitoDAO
						.getStartedWith(conceito.getStrConceito());
				if (list.size() == 0
						|| conceitoDAO.getStartedWith(
								conceito.getStrConceito()).get(0) == null) {

					conceitoDAO.save(conceito);

					if (eElementc.getAttribute("imagem") != null
							&& !eElementc.getAttribute("imagem")
									.matches("\\?")
							&& !eElementc.getAttribute("imagem")
									.isEmpty()) {
						File fileFrom = new File(fXmlFile.getParent()
								+ File.separator + "imagens"
								+ File.separator
								+ eElementc.getAttribute("imagem"));

						String current = System.getProperty("user.dir");
						File fileTo = new File(current + File.separator
								+ IMAGE_FOLDER + fileFrom.getName());
						try {
							Files.copy(fileFrom.toPath(),
									fileTo.toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
		level--;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		XMLImport xml = new XMLImport();
		try {
			xml.readFromXML("G:\\[Estudo]\\Mestrado\\00 - Projeto\\Casos\\P15001\\descritor.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
