package com.zby.pathtrie;

public class PathTrieMain {

	public static void main(String[] args) {
		PathTrie pathTrie = new PathTrie();
		pathTrie.addPath("/zby");
		pathTrie.addPath("/zby/a");
		pathTrie.addPath("/zby/a/b/c");
		System.out.println(pathTrie);
		String maxPrefix = pathTrie.findMaxPrefix("/zby/a/b");
		System.out.println(maxPrefix);
	}

}
