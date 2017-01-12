package br.unb.ppmec.cbrmeca.view.fragments.cases.function;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoCasoDAOImpl;
import br.unb.ppmec.cbrmeca.db.model.dao.FuncaoDAOImpl;
import br.unb.ppmec.cbrmeca.view.fragments.cases.function.visual.Node;
import br.unb.ppmec.cbrmeca.view.fragments.cases.function.visual.Tree;

public class JPanelFunction extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FuncaoCasoDAOImpl fcDao = new FuncaoCasoDAOImpl();
	private FuncaoDAOImpl fDao = new FuncaoDAOImpl();
	
	private int block_width = JPanelFunctionBoxClassification.BLOCK_WIDTH;
	private int block_height = JPanelFunctionBoxClassification.BLOCK_HEIGHT;
	
	private Dimension dim = new Dimension(1, 1);
	private Tree<FuncaoCaso> arvore;
	
	private BufferedImage image;
	
	public JPanelFunction() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setOpaque(false);
		setLayout(null);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
		super.paint(g);
	}
	
	public Tree<FuncaoCaso> getCaseTree(int funcao){
		FuncaoCaso fc = fcDao.get(funcao);
		Tree<FuncaoCaso> arvore = new Tree<FuncaoCaso>(fc);
		
		Node<FuncaoCaso> nodeRoot = new Node<FuncaoCaso>();
		nodeRoot.setData(fc);
		nodeRoot.setChildren(searchForChildren(nodeRoot));

		arvore.setRoot(nodeRoot);
		
		return arvore;
	}
	
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
	
	private void generateTree(Tree<FuncaoCaso> arvore){
		removeAll();
		
		image = (BufferedImage) new BufferedImage(3000, 3000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		drawNodes(arvore.getRoot(), 0, 1);
		setBounds(0,0, arvore.getRoot().getSpaces()*block_width, getHeight());
		
		repaint();
	}
	
	private void drawNodes(Node<FuncaoCaso> f, int initialSpaceX, int levelY){
		int circleHeight = block_height;
		int circleWidth = block_width;
		int multiplierY = block_height + 20;
		int nextX = 0;
		
		if(f.getData() == null){
			return;
		}
		
		int xPos = (f.getSpaces()/2 + initialSpaceX);
		
		JPanelFunctionBoxClassification box = new JPanelFunctionBoxClassification(f.getData());
		box.setBounds(xPos, (multiplierY*levelY), block_width, block_height);
        if(f.getData().getBolElementar()){
        	box.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
        this.add(box);
        this.repaint();
		
		levelY++;
		
		if(f.getParent() == null){
			nextX = 0;
		}else{
			nextX = initialSpaceX;
		}
		
		Graphics2D graphics2d = (Graphics2D)image.createGraphics();
		
		graphics2d.setColor(Color.BLACK);
		graphics2d.setStroke(new BasicStroke(3));
		
		for(Node<FuncaoCaso> node : f.getChildren()){
			if(dim.getHeight() < levelY*multiplierY + circleHeight + 10){
				dim = new Dimension((int)dim.getWidth(), levelY*multiplierY + circleHeight + 10);
			}
			
			if(dim.getWidth() < xPos + 2*block_width + 100){
				dim = new Dimension(xPos + 2*block_width + 100, (int)dim.getHeight());
			}
			
			setSize(dim);
			setPreferredSize(dim);
			setMinimumSize(dim);
			setMaximumSize(dim);

			drawNodes(node, nextX, levelY);
			
			graphics2d.drawLine(	xPos + circleWidth/2,
						multiplierY*(levelY - 1)+ circleHeight, 
						xPos + circleWidth/2, 
						multiplierY*levelY - 10);
			
			graphics2d.drawLine(	xPos + circleWidth/2,
						multiplierY*levelY - 10, 
						(nextX + node.getSpaces()/2) + circleWidth/2, 
						multiplierY*levelY - 10);
			
			graphics2d.drawLine(	(nextX + node.getSpaces()/2) + circleWidth/2,
						multiplierY*levelY - 10, 
						(nextX + node.getSpaces()/2) + circleWidth/2, 
						multiplierY*levelY);
			
			nextX = nextX + node.getSpaces();
		}
		
		graphics2d.dispose();
		
		repaint();
	}

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JPanelFunction panel = new JPanelFunction();
        panel.setEnabled(true);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(panel);

        jFrame.getContentPane().add(pane);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}