package ru.bmn;

import junit.framework.TestCase;

/**
 * Created by bmn85 on 10.04.2016.
 */
public class PhraseTest extends TestCase {

	public void testToString() throws Exception {
		Phrase p = new Phrase();

		p.addWord("Hello");
		p.addWord("World!");

		assertEquals("Hello World!", p.toString());
	}

	public void testSymbolsCount() throws Exception {
		Phrase p = new Phrase();

		assertEquals(0, p.symbolsCount());

		p.addWord("abc");
		p.addWord("xyz");

		assertEquals(6, p.symbolsCount());
	}

	public void testWordsCount() throws Exception {
		Phrase p = new Phrase();

		assertEquals(0, p.wordsCount());

		p.addWord("abc");
		p.addWord("xyz");

		assertEquals(2, p.wordsCount());
	}

	public void testSetDefaultValue() throws Exception {
		Phrase p = new Phrase();

		p.setDefaultValue("default string");
		assertEquals("default string", p.toString());
	}
}