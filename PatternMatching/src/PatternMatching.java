import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatternMatching {
    static List<Integer> list = new ArrayList<Integer>();
    public static void naive(String p, String s){
        int n = s.length();
        int m = p.length();
        int z;
        for(int i=0; i<=n-m; i++){
            int j=0;
            while ((j<m) && (p.charAt(j) == s.charAt(i+j))) j++;
            if (j==m) {
                int x = 0;
                int k=i;
                int line = list.get(x);
                while (line <= k) {
                    k -= line;
                    x++;
                    line = list.get(x);
                }
                System.out.println("Naive Search - Pattern found at line: " + (x+1) + ", character: " + (k+1));
            }
        }
    }
    // KR (Karpa-Rabina)
    public static void KR(String p, String s){
        int r = 256; // (0-255) ASCII
        int q = 8237; // Prime number
        int rm=1;

        int n = s.length();
        int m = p.length();
        int h2=0,h1=0;
        // Calculate hasz for pattern & string
        for (int i=0; i<m; i++) {
            h1=((h1*r) + p.charAt(i));
            h1%=q;
            h2=((h2*r) + s.charAt(i));
            h2%=q;
        }
        // Calculate (r^(m-1)%q)
        for (int i = 0; i < m-1; i++)
            rm = (rm * r) % q;

        for (int i = 0; i <= n-m; i++) {

            if (h1 == h2) {
                int j;
                for (j = 0; j < m; j++) {
                    if (s.charAt(i + j) != p.charAt(j))
                        break;
                }
                if (j == m) {
                    int x = 0;
                    int k = i;
                    int line = list.get(x);
                    while (line < k) {
                        k -= line;
                        x++;
                        line = list.get(x);
                    }
                    System.out.println("KR Search - Pattern found at line: " + (x + 1) + ", character: " + (k + 1));
                }
            }

            // Calculate next hash value
            if (i < n-m) {
                h2 = (r * (h2 - s.charAt(i) * rm) + s.charAt(i + m)) % q;
                //Convert to positive int
                if (h2 < 0)
                    h2 = (h2 + q);
            }
        }
    }
    public static void KMP(String p, String s){
        int n = s.length();
        int m = p.length();
        int P[]= new int[m+1];

        // Compute tab P
        P[0]=0; P[1]=0;
        int k=0;
        for (int j=2; j<=m; j++) {
            while ((k>0) && (p.charAt(k)!=p.charAt(j-1)))
                k=P[k];
            if (p.charAt(k)==p.charAt(j-1))
                k++;
            P[j]=k;
        }
        // KMP
        int j=0;
        int i=1;
        while (i<=n-m+1){
            j=P[j];
            while(j<m && p.charAt(j)==s.charAt(i+j-1))
                j++;
            if (j==m)
                if (j == m) {
                    int x = 0;
                    int y = i;
                    int line = list.get(x);
                    while (line < y) {
                        y -= line;
                        x++;
                        line = list.get(x);
                    }
                    System.out.println("KMP Search - Pattern found at line: " + (x + 1) + ", character: " + y);
                }
            i=i+Math.max(1,j-P[j]);
        }
    }
    public static String readpattern() throws IOException {
        BufferedReader fileReader = null;
        String pattern1="";
        try {
            fileReader = new BufferedReader(new FileReader("wzorzec.txt"));
            String line = fileReader.readLine();
            while (line != null) {
                pattern1=pattern1+line;
                line = fileReader.readLine();
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return pattern1;
    }
    public static String readtext() throws IOException {
        BufferedReader fileReader = null;
        String word1="";
        try {
            fileReader = new BufferedReader(new FileReader("tekst.txt"));
            String line = fileReader.readLine();
            while (line != null) {
                word1 = word1 + line;
                list.add(line.length());
                line = fileReader.readLine();
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return word1;
    }
    public static void main(String[] args) throws IOException {

        readpattern();
        readtext();
        String pattern = readpattern();
        String word = readtext();
        long start;
        long stop;
        System.out.println("------------------------------------------");
        start=System.nanoTime();
        naive(pattern, word);
        stop=System.nanoTime();
        System.out.println("\nTIME: "+(stop-start)+" ns");
        System.out.println("\n------------------------------------------");
        start=System.nanoTime();
        KR(pattern, word);
        stop=System.nanoTime();
        System.out.println("\nTIME: "+(stop-start)+" ns");
        System.out.println("\n------------------------------------------");
        start=System.nanoTime();
        KMP(pattern, word);
        stop=System.nanoTime();
        System.out.println("\nTIME: "+(stop-start)+" ns");
        System.out.println("\n------------------------------------------");
    }
}
