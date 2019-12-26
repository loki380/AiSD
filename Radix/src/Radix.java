
public class Radix {
    public static void radixSort(String[] tab) {
        int max = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].length()-1 > max) {
                max = tab[i].length()-1;
            }
        }

        for (int i = max; i >= 0; i--) {
            countingSort(tab, i);
        }
    }
    public static void countingSort(String[] tab, int index) {
        int charIndex;
        int rozmiar = tab.length;
        int[] zlicz = new int[500];
        String[] out = new String[rozmiar];
        for (int i =0; i < 500; i++) {
            zlicz[i]=0;
        }
        for (int i = 0; i < rozmiar; i++) {
            if (tab[i].length() - 1 < index) {
                charIndex = 0;
            }
            else {
                charIndex = tab[i].charAt(index) + 1;
            }
            zlicz[charIndex]++;
        }

        for (int i = 1; i < zlicz.length; i++) {
            zlicz[i] += zlicz[i - 1];
        }

        for (int i = rozmiar - 1; i >= 0; i--) {
            if (tab[i].length() - 1 < index) {
                charIndex = 0;
            }
            else {
                charIndex = tab[i].charAt(index)+1;
            }
            out[zlicz[charIndex] - 1] = tab[i];
            zlicz[charIndex]--;
        }

        for (int i = 0; i < rozmiar; i++) {
            tab[i] = out[i];
        }
    }
}
