package com.zby.pathtrie;

import java.util.HashMap;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月25日
 * @Description 字典树节点
 */
public class TrieNode {

	private boolean property = false;
	private final HashMap<String, TrieNode> children;
	private TrieNode parent = null;

	public TrieNode(TrieNode parent) {
		children = new HashMap<String, TrieNode>();
		this.parent = parent;
	}

	public TrieNode getParent() {
		return this.parent;
	}

	public void setParent(TrieNode parent) {
		this.parent = parent;
	}

	public void setProperty(boolean prop) {
		this.property = prop;
	}

	public boolean getProperty() {
		return this.property;
	}

	void addChild(String childName, TrieNode node) {
		synchronized (children) {
			if (children.containsKey(childName)) {
				return;
			}
			children.put(childName, node);
		}
	}

	void deleteChild(String childName) {
		synchronized (children) {
			if (!children.containsKey(childName)) {
				return;
			}
			TrieNode childNode = children.get(childName);
			// this is the only child node.
			if (childNode.getChildren().length == 1) {
				childNode.setParent(null);
				children.remove(childName);
			} else {
				// their are more child nodes
				// so just reset property.
				childNode.setProperty(false);
			}
		}
	}

	TrieNode getChild(String childName) {
		synchronized (children) {
			if (!children.containsKey(childName)) {
				return null;
			} else {
				return children.get(childName);
			}
		}
	}

	String[] getChildren() {
		synchronized (children) {
			return children.keySet().toArray(new String[0]);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Children of trienode: ");
		synchronized (children) {
			for (String str : children.keySet()) {
				sb.append(" " + str);
			}
		}
		return sb.toString();
	}

}
