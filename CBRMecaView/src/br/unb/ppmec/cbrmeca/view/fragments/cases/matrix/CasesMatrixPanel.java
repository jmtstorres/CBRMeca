/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.unb.ppmec.cbrmeca.db.model.Conceito;
import br.unb.ppmec.cbrmeca.view.fragments.cases.matrix.vo.FuncaoMatrizVO;

// TODO: Auto-generated Javadoc
/**
 * The Class CasesMatrixPanel.
 */
public class CasesMatrixPanel extends JPanel {

	/** The list. */
	private List<CasesMatrixRow> list = null;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4534025550144186364L;

	/** The panel_matrix. */
	private JPanel panel_matrix;

	/**
	 * Create the panel.
	 */
	public CasesMatrixPanel() {
		setBackground(Color.WHITE);
		initialize();
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		panel_matrix = new JPanel();
		add(panel_matrix, BorderLayout.WEST);
		panel_matrix.setBackground(Color.WHITE);
		GridBagLayout gbl_panel_matrix = new GridBagLayout();
		gbl_panel_matrix.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_matrix.rowHeights = new int[] { 30, 30 };
		gbl_panel_matrix.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panel_matrix.rowWeights = new double[] { 0.0, 0.0 };
		panel_matrix.setLayout(gbl_panel_matrix);
	}

	/**
	 * Adds the function.
	 *
	 * @param funcao the funcao
	 */
	public void addFunction(FuncaoMatrizVO funcao) {
		
		if (list == null) {
			list = new ArrayList<CasesMatrixRow>();
		}
		
		if(checkExisting(funcao)){
			return;
		}
		
		System.out.println("Matriz: " + funcao.getFuncao());

		CasesMatrixRow row = new CasesMatrixRow(false);
		row.setFunction(funcao);
		if(funcao.getConceitos() != null && funcao.getConceitos().size() > 0){
			for(Conceito cVO : funcao.getConceitos()){
				row.addElement(cVO);
			}
		}
		list.add(row);
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = list.size();
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.weighty = 0;
		gbc_lblNewLabel_1.gridheight = 1;
		gbc_lblNewLabel_1.gridwidth = 1;
		panel_matrix.add(row, gbc_lblNewLabel_1);
		
		panel_matrix.revalidate();
		panel_matrix.repaint();
		revalidate();
		repaint();
	}

	/**
	 * Check existing.
	 *
	 * @param funcao the funcao
	 * @return true, if successful
	 */
	private boolean checkExisting(FuncaoMatrizVO funcao) {
		for(CasesMatrixRow row : list){
			if(row.getFuncao().equals(funcao.getFuncao())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the functions.
	 *
	 * @param list2 the new functions
	 */
	public void setFunctions(List<FuncaoMatrizVO> list2) {
		if (list == null) {
			list = new ArrayList<CasesMatrixRow>();
		}
		
		System.out.println("Buscando para remover...");
		List<CasesMatrixRow> removed = new ArrayList<CasesMatrixRow>();
		for(CasesMatrixRow row : list){
			boolean found = false;
			for (FuncaoMatrizVO funcao : list2) {
				if(row.getFuncao().getFuncao().matches(funcao.getFuncao())){
					found = true;
				}
			}
			
			if(!found){
				System.out.println("Eliminar: " + row.getName() + " ?");
				removed.add(row);
			}
		}
		
		list.removeAll(removed);
		for(CasesMatrixRow row : removed){
			panel_matrix.remove(row);
		}
		
		System.out.println("Buscando para adicionar...");
		List<FuncaoMatrizVO> added = new ArrayList<FuncaoMatrizVO>();
		for (FuncaoMatrizVO funcao : list2) {
			boolean found = false;
			for(CasesMatrixRow row : list){
				if(row.getFuncao().getFuncao().matches(funcao.getFuncao())){
					found = true;
				}
			}
			
			if(!found){
				System.out.println("Adicionar: " + funcao.getFuncao() + " ?");
				added.add(funcao);
			}
		}
		
		for(FuncaoMatrizVO f : added){
			addFunction(f);
		}
	}
	
	/**
	 * Clear all.
	 */
	public void clearAll(){
		if(list != null){
			list.clear();
		}
		panel_matrix.removeAll();
	}
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<CasesMatrixRow> getList(){
		return this.list;
	}
	
	/**
	 * Show buttons.
	 *
	 * @param show the show
	 */
	public void showButtons(boolean show){
		for(CasesMatrixRow row : list){
			row.showButtons(show);
		}
	}
}
