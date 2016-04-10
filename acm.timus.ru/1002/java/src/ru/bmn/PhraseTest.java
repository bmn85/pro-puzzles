package ru.bmn;

import junit.framework.TestCase;

/**
 * Created by bmn85 on 10.04.2016.
 */
public class PhraseTest extends TestCase {

	public void testToString() throws Exception {
		Phrase p = new Phrase();

		p.setDefaultValue("default string");
		assertEquals("default string", p.toString());

		p.addWord("Hello");
		p.addWord("World!");

		assertEquals("Hello World!", p.toString());
	}
}