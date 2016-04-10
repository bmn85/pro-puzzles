package ru.bmn;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Task_1002 {
    public static void main(String[] args) throws IOException {
	    TaskWrapper task = new TaskWrapper();
        task.solve();
    }
}

class TaskWrapper {
    boolean         isOnlineJudge;
    StreamTokenizer in;
    PrintWriter     out;



    TaskWrapper() throws IOException {
        this.isOnlineJudge = System.getProperty("ONLINE_JUDGE") != null;

        Reader reader = this.isOnlineJudge
                ? new InputStreamReader(System.in)
                : new FileReader("input.txt");

        Writer writer = this.isOnlineJudge
                ? new OutputStreamWriter(System.out)
                : new FileWriter("output.txt");

        this.in  = new StreamTokenizer(new BufferedReader(reader));
        this.out = new PrintWriter(writer);
    }

    public void solve() throws IOException {
        while (this.in.nextToken() != StreamTokenizer.TT_EOF) {
            if (this.in.nval == -1) break;

            Double targetNumber = this.in.nval;
            String targetNumberStr = Long.toString(targetNumber.longValue());

            this.in.nextToken();
            Integer helperSize  = (int)this.in.nval;

            Trie t = new Trie('\0');
            for (int i = 0; i < helperSize; i++) {
                this.in.nextToken();
                t.addWord(this.in.sval);
            }

            this.out.println(this.findWords(t, targetNumberStr.toCharArray()));
        }

        this.out.flush();
        this.out.close();
    }

    private String findWords(Trie t, char[] digitPath) {
		char[] dp = digitPath;
		Phrase phrase = new Phrase();
		phrase.setDefaultValue("No solution.");

		while (dp.length > 0) {
			String word = t.findWord(dp);
			if (word == null) {
				if (dp.length > 1) {
					dp = Arrays.copyOfRange(dp, 0, dp.length - 1);
				}
				else {
					break;
				}
			}
			else {
				phrase.addWord(word);
				dp = Arrays.copyOfRange(digitPath, phrase.symbolsCount(), digitPath.length);
			}
		}

        return phrase.toString();
    }


}

class Phrase {
	private ArrayList<String> words = new ArrayList<>();
	private String defaultValue;

	public void addWord(String word) {
		this.words.add(word);
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
		int result = 0;
		for (String word: this.words) {
			result += word.length();
		}
		return result;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}

class Trie {
    protected Character value;
    protected String word;
    protected HashMap<Character, Trie> childs;
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

	Trie(Character c) {
        this.value = c;
        this.childs = new HashMap<>();
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
        if (currNode.childs.containsKey(digit)) {
            return currNode.childs.get(digit);
        }
        else {
            currNode.childs.put(digit, new Trie(digit));
            return currNode.childs.get(digit);
        }
    }

    public String findWord(char[] digits) {
        if (this.childs.containsKey(digits[0])) {
            if ((digits.length == 1) && this.childs.get(digits[0]).word != null) {
                return this.childs.get(digits[0]).word;
            }
            else if (digits.length > 1) {
                return this.childs.get(digits[0]).findWord(Arrays.copyOfRange(digits, 1, digits.length));
            }
        }
        return null;
    }
}
