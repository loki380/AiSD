import java.io.*;
import java.util.Scanner;

public class Main {
    public static int countLines() throws FileNotFoundException {
        int lines = 0;
        File file = new File("dane.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            scanner.nextLine();
            lines++;
        }
        scanner.close();
        return lines;
    }
    public static void readFile(String[][] readInt, String[] readString) throws IOException {
        int x = 0;
        String stich;
        BufferedReader readLine = new BufferedReader(new FileReader("dane.txt"));

        while ((stich = readLine.readLine()) != null) {
            String[] tempNumAndSurname = stich.split("\\s");
            readString[x] = tempNumAndSurname[1];
            readInt[x][0] = tempNumAndSurname[0];
            readInt[x][1] = tempNumAndSurname[1];
            x++;
        }
    }
    private static void readQuick(String[] readString) throws IOException {
        int x = 0;
        String stich;
        BufferedReader readLine = new BufferedReader(new FileReader("dane.txt"));

        while ((stich = readLine.readLine()) != null) {
            String[] tempNumAndSurname = stich.split("\\s");
            readString[x] = tempNumAndSurname[1];
            x++;
        }
    }
    public static void printFile(String[][] readString, int size) throws FileNotFoundException {
        PrintWriter generuj = new PrintWriter("dane.output");
        for (int i = 0; i < size; i++) {
            generuj.println(readString[i][0]+" "+readString[i][1]);
        }
        generuj.close();
    }
    private static String[][] matchNumbers(String[][] readInt, String[] readString, int size) {
        String[][] out = new String[size][2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(readInt[j][1].equals(readString[i])) {
                    out[i][1] = readInt[j][1];
                    out[i][0] = readInt[j][0];
                }
            }
        }
        return out;
    }
    public static void main(String[] args) throws IOException {
        long startTimeRadix, endTimeRadix, startTimeQuick, endTimeQuick;
        double TimeRadix,TimeQuick;
        int size = countLines();
        String[][] readInt = new String[size][2];
        String[] readString = new String[size];
        readFile(readInt,readString);

        startTimeRadix = System.nanoTime();
        Radix.radixSort(readString);
        endTimeRadix = System.nanoTime();

        printFile(matchNumbers(readInt,readString, size), size);

        String[] readString1 = new String[size];
        readQuick(readString1);

        startTimeQuick = System.nanoTime();
        QuickSort.quickSort(readString1,0,readString1.length-1);
        endTimeQuick = System.nanoTime();

        TimeRadix = (endTimeRadix-startTimeRadix)/1000000000.0;
        TimeQuick = (endTimeQuick-startTimeQuick)/1000000000.0;

        System.out.println("Radix Sort: " + TimeRadix);
        System.out.println("Quick Sort: " + TimeQuick);
    }
}
