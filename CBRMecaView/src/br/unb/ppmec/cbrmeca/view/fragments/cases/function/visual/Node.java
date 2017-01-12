package br.unb.ppmec.cbrmeca.view.fragments.cases.function.visual;

import java.util.List;

public class Node<T> {
	private T data;
	private Node<T> parent;
	private List<Node<T>> children;
	private int spaces = 0;
	private int levels = 0;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	public int getSpaces() {
		return spaces;
	}

	public void setSpaces(int spaces) {
		if(parent != null){
			parent.setSpaces(spaces);
		}
		this.spaces += spaces;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		if(parent != null){
			parent.setLevels(levels);
		}
		this.levels += levels;

	}
}