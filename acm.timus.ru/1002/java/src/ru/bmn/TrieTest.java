package ru.bmn;

import junit.framework.TestCase;

/**
 * Created by bmn85 on 09.04.2016.
 */
public class TrieTest extends TestCase {
	public void testFindWord() throws Exception {
		Trie t = new Trie();
		t.addWord("cat");

		assertEquals(t.findWord(new char[] {'1', '2', '4'}), null);

		assertEquals(t.findWord(new char[] {'2', '2', '8'}), "cat");
	}
}