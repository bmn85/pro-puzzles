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

		p.addPhrasePart(p);

		assertEquals(12, p.symbolsCount());

		Phrase p2 = new Phrase(p);

		assertEquals(12, p2.symbolsCount());
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

		Phrase.setDefaultValue("default string");
		assertEquals("default string", p.toString());
	}

	public void testIsEmpty() throws Exception {
		Phrase p = new Phrase();

		assertTrue(p.isEmpty());

		p.addWord("abc");

		assertFalse(p.isEmpty());
	}

	public void testIsComplete() throws Exception {
		Phrase p = new Phrase();
		Phrase.setCompleteLength(10);

		assertFalse(p.isComplete());

		p.addWord("abc");

		assertFalse(p.isComplete());

		p.addWord("1234567");

		assertTrue(p.isComplete());
	}

	public void testCompareTo() throws Exception {
		Phrase p1 = new Phrase();
		p1.addWord("abc");
		p1.addWord("abc");

		Phrase p2 = new Phrase();
		p2.addWord("abc");
		p2.addWord("abc");
		p2.addWord("abc");

		assertEquals(p1.compareTo(p2), -1);

		p1.addWord("abc");

		assertEquals(p1.compareTo(p2), 0);

		p1.addWord("abc");

		assertEquals(p1.compareTo(p2), 1);

	}

	public void testAddPhrasePart() throws Exception {
		Phrase p1 = new Phrase();
		p1.addWord("abc");
		p1.addWord("def");

		Phrase p2 = new Phrase();
		p2.addWord("123");
		p2.addWord("456");

		p2.addPhrasePart(p1);

		assertEquals(p2.toString(), "123 456 abc def");

	}
}