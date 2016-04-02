package ru.bmn;

import java.io.*;
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
        this.in = new StreamTokenizer(new BufferedReader(reader));

        Writer writer = this.isOnlineJudge
                ? new OutputStreamWriter(System.out)
                : new FileWriter("output.txt");
        this.out = new PrintWriter(writer);
    }

    public void solve() throws IOException {
        while (this.in.nextToken() != 0 && this.in.sval != "-1") {
            System.out.print(this.in.nextToken());
            String targetNumber = this.in.sval;

            this.in.nextToken();
            int helperSize = (int)this.in.nval;

            Trie t = new Trie();
            for (int i = 0; i < helperSize; i++) {
                this.in.nextToken();
                t.addWord(this.in.sval);
            }

            this.out.print(this.findWords(targetNumber));
        }
    }

    private String findWords(String targetNumber) {
        return "No solution.";
    }
}

class Trie {
    private Character value;
    private Character realValue;
    private boolean   isLeaf;
    private HashMap<Character, Trie> childs;
    private HashMap<Character, Trie> root = new HashMap<Character, Trie>();

    Trie() {
        this.root.put('\0', null);
    }

    public void addWord(String word) {
        char[] chars = word.toCharArray();

        for (Character c: chars) {
            this.addChar(c);
        }

    }

    private void addChar(Character c) {


    }
}
