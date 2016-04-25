package ru.bmn;

import java.io.*;
import java.util.*;


public class Task_1002 {
	public static void main(String[] args) throws IOException {
		TaskWrapper task = new TaskWrapper();
		task.solve();
	}
}

class TaskWrapper {
	private StreamTokenizer in;
	private PrintWriter     out;
	private Trie            rootTrie;

	TaskWrapper() throws IOException {
		boolean isOnlineJudge = System.getProperty("ONLINE_JUDGE") != null;

		Reader reader = isOnlineJudge
				? new InputStreamReader(System.in)
				: new FileReader("input.txt");

		Writer writer = isOnlineJudge
				? new OutputStreamWriter(System.out)
				: new FileWriter("output.txt");

		this.in  = new StreamTokenizer(new BufferedReader(reader));
		this.out = new PrintWriter(writer);
	}

    public void solve() throws IOException {
		Phrase.setDefaultValue("No solution.");
        while (this.in.nextToken() != StreamTokenizer.TT_EOF) {
            if (this.in.nval == -1) break;

			Double targetNumber = this.in.nval;
			String targetNumberStr = Long.toString(targetNumber.longValue());

			this.in.nextToken();
			Integer helperSize  = (int)this.in.nval;

            this.rootTrie = new Trie();
            for (int i = 0; i < helperSize; i++) {
                this.in.nextToken();
                this.rootTrie.addWord(this.in.sval);
            }

            this.out.println(this.findShortPhrase(targetNumberStr.toCharArray()));
        }

		this.out.flush();
		this.out.close();
	}

	private String findShortPhrase(char[] digitPath) {
		Phrase.setCompleteLength(digitPath.length);

		ArrayList<Phrase> phrases = this.findAllPhrases(digitPath);

		Collections.sort(phrases);

		return phrases.size() > 0 ? phrases.get(0).toString() : new Phrase().toString();
	}

    private ArrayList<Phrase> findAllPhrases(char[] digitPath) {
		char[] dp  = digitPath.clone();
		ArrayList<Phrase> result = new ArrayList<>();

		for (int i = 0; i < dp.length; i++) {
			String word = this.rootTrie.findWord(Arrays.copyOfRange(dp, 0, dp.length - i));
			if (word != null) {
				Phrase currentPhrase = new Phrase();
				currentPhrase.addWord(word);
				if (currentPhrase.symbolsCount() == dp.length) {
					result.add(currentPhrase);
				}
				else {
					ArrayList<Phrase> phrases = this.findAllPhrases(Arrays.copyOfRange(dp, word.length(), dp.length));
					for (Phrase p: phrases) {
						Phrase deepPhrase = new Phrase(currentPhrase);
						deepPhrase.addPhrasePart(p);
						if (deepPhrase.symbolsCount() == dp.length) {
							result.add(deepPhrase);
						}
					}
				}
			}
		}
		return result;
	}
}

class Phrase implements Comparable<Phrase> {
	private ArrayList<String> words;
	private static String defaultValue;
	private static int completeLength;
	private int currentLength = 0;

	Phrase() {
		this.words = new ArrayList<>();
	}

	Phrase(Phrase p) {
		this.words = new ArrayList<>(p.words);
		this.currentLength += p.symbolsCount();
	}

	public static void setCompleteLength(int completeLength) {
		Phrase.completeLength = completeLength;
	}

	public void addWord(String word) {
		this.words.add(word);
		this.currentLength += word.length();
	}

	public void addPhrasePart(Phrase p) {
		this.words.addAll(p.words);
		this.currentLength += p.symbolsCount();
	}

	@Override
	public String toString() {
		String result = "";
		for (String word: this.words) {
			result += word + " ";
		}
		return result.length() > 0
				? result.substring(0, result.length() - 1)
				: this.defaultValue;
	}

	public int symbolsCount() {
		return this.currentLength;
	}

	public boolean isEmpty() {
		return this.words.size() == 0;
	}

	public int wordsCount() {
		return this.words.size();
	}

	public static void setDefaultValue(String value) {
		defaultValue = value;
	}

	public boolean isComplete() {
		return this.currentLength == Phrase.completeLength;
	}

	@Override
	public int compareTo(Phrase phrase) {
		if      (this.wordsCount() > phrase.wordsCount()) return 1;
		else if (this.wordsCount() < phrase.wordsCount()) return -1;
		else return 0;
	}
}

class Trie {
	private String word;
	private HashMap<Character, Trie> childes;
	private static final HashMap<Character, Character> DIGIT_MAP = new HashMap<Character, Character>() {{
		put('a', '2');
		put('b', '2');
		put('c', '2');
		put('d', '3');
		put('e', '3');
		put('f', '3');
		put('i', '1');
		put('j', '1');
		put('g', '4');
		put('h', '4');
		put('k', '5');
		put('l', '5');
		put('m', '6');
		put('n', '6');
		put('p', '7');
		put('r', '7');
		put('s', '7');
		put('t', '8');
		put('u', '8');
		put('v', '8');
		put('w', '9');
		put('x', '9');
		put('y', '9');
		put('o', '0');
		put('q', '0');
		put('z', '0');
	}};

	Trie() {
		this.childes = new HashMap<>();
	}

	public void addWord(String word) {
		char[] chars = word.toCharArray();

		Trie currNode = this;
		for (Character c: chars) {
			currNode = this.addChar(c, currNode);
		}
		currNode.word = word;
	}

	private Trie addChar(Character c, Trie currNode) {
		Character digit = DIGIT_MAP.get(c);

        if (currNode.childes.containsKey(digit)) {
            return currNode.childes.get(digit);
        }
        else {
            currNode.childes.put(digit, new Trie());
            return currNode.childes.get(digit);
        }
    }

    public String findWord(char[] digits) {
		if (digits.length > 0) {
			if (this.childes.containsKey(digits[0])) {
				if ((digits.length == 1) && this.childes.get(digits[0]).word != null) {
					return this.childes.get(digits[0]).word;
				}
				else if (digits.length > 1) {
					return this.childes.get(digits[0]).findWord(Arrays.copyOfRange(digits, 1, digits.length));
				}
			}
		}
        return null;
    }
}
