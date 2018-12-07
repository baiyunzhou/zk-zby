package com.zby.pathtrie;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathTrie {
	private static final Logger LOG = LoggerFactory.getLogger(PathTrie.class);
	private final TrieNode rootNode;

	public PathTrie() {
		this.rootNode = new TrieNode(null);
	}

	public void addPath(String path) {
		if (path == null) {
			return;
		}
		String[] pathComponents = path.split("/");
		TrieNode parent = rootNode;
		String part = null;
		if (pathComponents.length <= 1) {
			throw new IllegalArgumentException("Invalid path " + path);
		}
		for (int i = 1; i < pathComponents.length; i++) {
			part = pathComponents[i];
			if (parent.getChild(part) == null) {
				parent.addChild(part, new TrieNode(parent));
			}
			parent = parent.getChild(part);
		}
		parent.setProperty(true);
	}

	public void deletePath(String path) {
		if (path == null) {
			return;
		}
		String[] pathComponents = path.split("/");
		TrieNode parent = rootNode;
		String part = null;
		if (pathComponents.length <= 1) {
			throw new IllegalArgumentException("Invalid path " + path);
		}
		for (int i = 1; i < pathComponents.length; i++) {
			part = pathComponents[i];
			if (parent.getChild(part) == null) {
				// the path does not exist
				return;
			}
			parent = parent.getChild(part);
			LOG.info("{}", parent);
		}
		TrieNode realParent = parent.getParent();
		realParent.deleteChild(part);
	}

	public String findMaxPrefix(String path) {
		if (path == null) {
			return null;
		}
		if ("/".equals(path)) {
			return path;
		}
		String[] pathComponents = path.split("/");
		TrieNode parent = rootNode;
		List<String> components = new ArrayList<String>();
		if (pathComponents.length <= 1) {
			throw new IllegalArgumentException("Invalid path " + path);
		}
		int i = 1;
		String part = null;
		StringBuilder sb = new StringBuilder();
		int lastindex = -1;
		while ((i < pathComponents.length)) {
			if (parent.getChild(pathComponents[i]) != null) {
				part = pathComponents[i];
				parent = parent.getChild(part);
				components.add(part);
				if (parent.getProperty()) {
					lastindex = i - 1;
				}
			} else {
				break;
			}
			i++;
		}
		for (int j = 0; j < (lastindex + 1); j++) {
			sb.append("/" + components.get(j));
		}
		return sb.toString();
	}

	public void clear() {
		for (String child : rootNode.getChildren()) {
			rootNode.deleteChild(child);
		}
	}

	@Override
	public String toString() {
		return "PathTrie [rootNode=" + rootNode + "]";
	}
}
