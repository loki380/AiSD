import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Haszowanie {
    static int ilosclini(String plik) throws FileNotFoundException {
        int linie = 0;
        File file = new File(plik);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            scanner.nextLine();
            linie++;
        }
        scanner.close();
        return linie;
    }
    static void wypelnijTablice(String[] T, String nazwaPliku) throws FileNotFoundException {
        int i = 0;
        File file = new File(nazwaPliku);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            T[i] = scanner.next();
            i++;
        }
        scanner.close();
    }
    static void liczkolizje(Integer[] T, String[] tab, int m){
        // ZERUJEMY Tablice T
        for(int i=0; i<m; i++){
            T[i]=0;
        }
        // ZLICZAMY WARTOSC KAZDEGO SLOWA I DODAJEMY
        for(int i=0; i<2*m; i++){
            String word=tab[i];
            int wordvalue = 256 * (int) word.charAt(0) + word.charAt(1);
            int x=0;
            // KAZDY ZNAK
            for(int j=2; j<word.length(); j++){
                x=256*(int)word.charAt(j++);
                if(j<word.length()) {
                    x += word.charAt(j);
                }
                wordvalue ^= x;
            }
            T[wordvalue%m]++;
        }
        int zera=0;
        int max=0;
        double srednia=0;
        int count=0;
        for(int i=0; i<m; i++){
            if(T[i]==0){
                zera++;
            }
            else{
                srednia+=T[i];
                count++;
            }
            if(T[i]>max){
                max=T[i];
            }
        }
        srednia=srednia/count;
        System.out.println("M="+m+"\tSrednia="+srednia+"\tMaximum="+max+"\tIlosc zerowych="+zera);
    }
    public static void main(String[] args) throws FileNotFoundException {
        int n = ilosclini("3700.txt");
        String[] tab = new String[n];
        wypelnijTablice(tab, "3700.txt");
        int i=0;
        int m=0;
        while(i<5) {
            if(i==0) m = 1009;
            if(i==1) m = 1361;
            if(i==2) m = 1553;
            if(i==3) m = 1600;
            if(i==4) m = 1400;
            Integer[] T = new Integer[m];
            liczkolizje(T, tab, m);
            i++;
        }
    }
}
