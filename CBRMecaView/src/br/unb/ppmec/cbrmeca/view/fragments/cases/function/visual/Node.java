/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.fragments.cases.function.visual;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 *
 * @param <T> the generic type
 */
public class Node<T> {
	
	/** The data. */
	private T data;
	
	/** The parent. */
	private Node<T> parent;
	
	/** The children. */
	private List<Node<T>> children;
	
	/** The spaces. */
	private int spaces = 0;
	
	/** The levels. */
	private int levels = 0;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public Node<T> getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<Node<T>> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	/**
	 * Gets the spaces.
	 *
	 * @return the spaces
	 */
	public int getSpaces() {
		return spaces;
	}

	/**
	 * Sets the spaces.
	 *
	 * @param spaces the new spaces
	 */
	public void setSpaces(int spaces) {
		if(parent != null){
			parent.setSpaces(spaces);
		}
		this.spaces += spaces;
	}

	/**
	 * Gets the levels.
	 *
	 * @return the levels
	 */
	public int getLevels() {
		return levels;
	}

	/**
	 * Sets the levels.
	 *
	 * @param levels the new levels
	 */
	public void setLevels(int levels) {
		if(parent != null){
			parent.setLevels(levels);
		}
		this.levels += levels;

	}
}