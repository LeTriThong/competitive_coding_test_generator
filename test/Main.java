import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static FastReader scanner = new FastReader();
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World");
////        int n = scanner.nextInt();
////        while (n-- > 0) {
////            solve();
////        }
//
//        PrintStream o = new PrintStream("A.txt");
//
////        if (o.)
//
//        // Store current System.out before assigning a new value
//        PrintStream console = System.out;
//
//        // Assign o to output stream
//        System.setOut(o);
//        System.out.println("This will be written to the text file");
//
//        // Use stored value for output stream
//        System.setOut(console);
//        System.out.println("This will be written on the console!");

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
