/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.function.visual;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class JPanelDrawTree.
 */
public class JPanelDrawTree extends JPanel {
	
	/**
	 * Instantiates a new j panel draw tree.
	 */
	public JPanelDrawTree() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
	}

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The fc dao. */
	private FuncaoCasoDAOImpl fcDao = new FuncaoCasoDAOImpl();
	
	/** The dao. */
	private FuncaoDAOImpl fDao = new FuncaoDAOImpl();
	
	/** The block_width. */
	private int block_width = 120;
	
	/** The dim. */
	private Dimension dim = new Dimension(1, 1);
	
	/** The arvore. */
	private Tree<FuncaoCaso> arvore;

	/**
	 * Sets the case id.
	 *
	 * @param caseID the new case id
	 */
	public void setCaseID(int caseID) {
		arvore = getCaseTree(caseID);
		//repaint();
		dim  = new Dimension(arvore.getRoot().getSpaces() + 20, arvore.getRoot().getLevels());
		setSize(dim);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
    public void paint(Graphics g) {
		if(arvore != null){
			printTree(arvore, g);
		}
    }
	
	/**
	 * Gets the case tree.
	 *
	 * @param funcao the funcao
	 * @return the case tree
	 */
	public Tree<FuncaoCaso> getCaseTree(int funcao){
		FuncaoCaso fc = fcDao.get(funcao);
		Tree<FuncaoCaso> arvore = new Tree<FuncaoCaso>(fc);
		
		Node<FuncaoCaso> nodeRoot = new Node<FuncaoCaso>();
		nodeRoot.setData(fc);
		nodeRoot.setChildren(searchForChildren(nodeRoot));

		arvore.setRoot(nodeRoot);
		
		return arvore;
	}
	
	/**
	 * Search for children.
	 *
	 * @param node the node
	 * @return the list
	 */
	private List<Node<FuncaoCaso>> searchForChildren(Node<FuncaoCaso> node){
		List<Node<FuncaoCaso>> nodeList = new ArrayList<>();
		if(	node == null ||
			node.getData() == null){
			return nodeList;
		}
		
		List<FuncaoCaso> fcList = fcDao.getByIdFuncaoPai(node.getData().getIdFuncaoCaso());
		if(fcList.size() == 0){
			return nodeList;
		}
		
		for(FuncaoCaso fcChild : fcList){
			Node<FuncaoCaso> nodeNew = new Node<>();
			nodeNew.setData(fcChild);
			nodeNew.setParent(node);

			List<Node<FuncaoCaso>> newList = searchForChildren(nodeNew);
			if(newList.size() == 0){
				nodeNew.setSpaces(block_width + 5);
				nodeNew.setLevels(block_width);
			}else{
				nodeNew.setSpaces(newList.size());
				nodeNew.setLevels(1);
			}
			nodeNew.setChildren(newList);
			nodeList.add(nodeNew);
		}
		
		return nodeList;
	}
	
	/**
	 * Prints the tree.
	 *
	 * @param arvore the arvore
	 * @param g the g
	 */
	private void printTree(Tree<FuncaoCaso> arvore, Graphics g){
		printNodes(arvore.getRoot(), 0, 1, g);
		setBounds(0,0, arvore.getRoot().getSpaces()*block_width, getHeight());
	}
	
	/**
	 * Prints the nodes.
	 *
	 * @param f the f
	 * @param initialSpaceX the initial space x
	 * @param levelY the level y
	 * @param g the g
	 */
	private void printNodes(Node<FuncaoCaso> f, int initialSpaceX, int levelY, Graphics g){
		int circleHeight = block_width;
		int circleWidth = block_width;
		int multiplierY = circleHeight + 20;
		int nextX = 0;
		
		if(f.getData() == null){
			return;
		}
		
		int xPos = (f.getSpaces()/2 + initialSpaceX);
		
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
		
        if(f.getData().getBolElementar()){
        	g2.setColor(Color.RED);
        }
        
		g.drawRect(	xPos,
					(multiplierY*levelY), 
					circleWidth, 
					circleHeight);

		g2.setColor(Color.BLACK);
		
		int offset = 10;
		String str = 	fDao.get(f.getData().getIdFuncao()).getStrFuncaoVerbo();
		int width = g.getFontMetrics().stringWidth(str);
		int height = g.getFontMetrics().getHeight();
		g.drawChars(	str.toCharArray(), 
						0, 
						str.length(), 
						xPos + (block_width - width)/2,
						(multiplierY*levelY) + (circleHeight - height)/2);
		
		str = 	fDao.get(f.getData().getIdFuncao()).getStrFuncaoObjeto();
		width = g.getFontMetrics().stringWidth(str);
		if(width > (block_width - offset/2)){
			String[] parts = str.split(Pattern.quote(" "));
			String top = "";
			int line = 1;
			String check = "";
			for(int i = 0; i < parts.length; i++){
				check = top.concat(parts[i] + " ");
				width = g.getFontMetrics().stringWidth(check);
				if(width > (block_width - offset/2)){
					width = g.getFontMetrics().stringWidth(top);
					g.drawChars(	top.toCharArray(), 
									0, 
									top.length(), 
									xPos + (block_width - width)/2, 
									(multiplierY*levelY) + (circleHeight - height)/2 + height*line++);
					top = "";
					i--;
				}else{
					top = check;
				}
			}

			width = g.getFontMetrics().stringWidth(top);
			g.drawChars(	top.toCharArray(), 
							0, 
							top.length(), 
							xPos + (block_width - width)/2, 
							(multiplierY*levelY) + (circleHeight - height)/2 + height*line++);

		}else{
			g.drawChars(	str.toCharArray(), 
							0, 
							str.length(), 
							xPos + (block_width - width)/2, 
							(multiplierY*levelY) + (circleHeight - height)/2 + height);			
		}
		
		levelY++;
		
		if(f.getParent() == null){
			nextX = 0;
		}else{
			nextX = initialSpaceX;
		}
		
		for(Node<FuncaoCaso> node : f.getChildren()){
			//dim = new Dimension(xPos + block_width, levelY*multiplierY + circleHeight*10);
			
			
			printNodes(node, nextX, levelY, g);
			
			g.drawLine(	xPos + circleWidth/2,
						multiplierY*(levelY - 1)+ circleHeight, 
						xPos + circleWidth/2, 
						multiplierY*levelY - 10);
			
			g.drawLine(	xPos + circleWidth/2,
						multiplierY*levelY - 10, 
						(nextX + node.getSpaces()/2) + circleWidth/2, 
						multiplierY*levelY - 10);
			
			g.drawLine(	(nextX + node.getSpaces()/2) + circleWidth/2,
						multiplierY*levelY - 10, 
						(nextX + node.getSpaces()/2) + circleWidth/2, 
						multiplierY*levelY);
			
			nextX = nextX + node.getSpaces();
		}
	}

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JPanelDrawTree panel = new JPanelDrawTree();
        //panel.printTree(panel.getCaseTree(4165));
        ScrollPane pane = new ScrollPane();
        pane.add(panel);

        jFrame.getContentPane().add(pane);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    
}