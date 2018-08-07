package com.shiv.test.model;



public class TrieNode {
	static final int ALPHABET_SIZE = 26;
	
	TrieNode[] children = new TrieNode[ALPHABET_SIZE];
	boolean isLeaf;
	int count = 0;
	
	public TrieNode() {
		isLeaf = false;
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			children[i] = null;
		}
	}

	public TrieNode[] getChildren() {
		return children;
	}

//	public void setChildren(TrieNode[] children) {
//		this.children = children;
//	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void insertStringInTrie(String str) {
		str = str.trim();
		int len = str.length();
		TrieNode node = this;
		int index;
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
				index = Character.isUpperCase(c) ? c - 'A' : c - 'a';
				if (node.getChildren()[index] == null)
					node.getChildren()[index] = new TrieNode();
				node = node.getChildren()[index];
			}
			if (i == len - 1) {
				node.setLeaf(true);
				node.setCount(1);
			}
		}
	}
	
	public TrieNode searchWord(String word) {
		word = word.trim();
		TrieNode node = this;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
				int index = Character.isUpperCase(c) ? c - 'A' : c - 'a';
				if (node.children[index] != null) {
					node = node.children[index];
				} else {
					return null;
				}
			}
		}
		return node;
	}
	
	
}
