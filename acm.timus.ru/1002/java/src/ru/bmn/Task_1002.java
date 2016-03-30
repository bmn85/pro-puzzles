package ru.bmn;

import java.io.*;


public class Task_1002 {
    public static void main(String[] args) throws IOException {
	    TaskWrapper task = new TaskWrapper();
        task.solve();
    }

    private static class TaskWrapper {
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

        public void solve() {
            boolean processing = false;
            this.in.nextToken();

        }
    }
}
