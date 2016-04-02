package ru.bmn;

import java.io.*;
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

            String targetNumber = this.in.sval;

            this.in.nextToken();
            Integer helperSize  = (int)this.in.nval;

            Trie t = new Trie('\0');
            for (int i = 0; i < helperSize; i++) {
                this.in.nextToken();
                t.addWord(this.in.sval);
            }

            this.out.println(this.findWords(t, targetNumber));
        }

        this.out.flush();
        this.out.close();
    }

    private String findWords(Trie t, String targetNumber) {
        char[] digits = targetNumber.toCharArray();
        t.findWord(digits);
        return "No solution.";
    }


}

class Trie {
    protected Character value;
    protected boolean isLeaf = false;
    protected String word;
    protected HashMap<Character, Trie> childs;

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
        currNode.isLeaf = true;
        currNode.word   = word;
    }

    private Trie addChar(Character c, Trie currNode) {
        if (currNode.childs.containsKey(c)) {
            return currNode.childs.get(c);
        }
        else {
            currNode.childs.put(c, new Trie(c));
            return currNode.childs.get(c);
        }
    }

    public String findWord(char[] digits) {
        String result = null;

        if (this.childs.containsKey(digits[0])) {
            if ((digits.length == 1) && this.childs.get(digits[0]).isLeaf) {
                return this.childs.get(digits[0]).word;
            }
            else if (digits.length > 1) {
                return this.childs.get(digits[0]).findWord(Arrays.copyOfRange(digits, 1, digits.length));
            }
        }
        return result;
    }
}
