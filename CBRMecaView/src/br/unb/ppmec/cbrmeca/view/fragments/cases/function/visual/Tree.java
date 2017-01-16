/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.function.visual;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Tree.
 *
 * @param <T> the generic type
 */
public class Tree<T> {
	
	/** The root. */
	private Node<T> root;

	/**
	 * Instantiates a new tree.
	 *
	 * @param rootData the root data
	 */
	public Tree(T rootData) {
		root = new Node<T>();
		root.setData(rootData);
		root.setChildren(new ArrayList<Node<T>>());
	}

	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public Node<T> getRoot() {
		return root;
	}

	/**
	 * Sets the root.
	 *
	 * @param root the new root
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}
}