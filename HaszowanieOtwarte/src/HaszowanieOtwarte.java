// WARIANT S+OK

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Dane{
    String nazwisko;
    int liczba;
    Dane(){
        String nazwisko=null;
        int liczba=0;
    }
    Dane(String nazwisko, Integer liczba){
        this.nazwisko=nazwisko;
        this.liczba=liczba;
    }
}
public class HaszowanieOtwarte {
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
    static void wypelnijTablice(Dane[] T, String nazwaPliku, int m) throws FileNotFoundException {
        int i = 0;
        File file = new File(nazwaPliku);
        Scanner scanner = new Scanner(file);
        while(i<m) {
            T[i]= new Dane();
            T[i].liczba = scanner.nextInt();
            T[i].nazwisko = scanner.next();
            i++;
        }
        scanner.close();
    }
    static int konwertuj(String word){
        int wordvalue = 256 * (int) word.charAt(0) + word.charAt(1);
        int x=0;
        for(int j=2; j<word.length(); j++){
            x=256*(int)word.charAt(j++);
            if(j<word.length()) {
                x += word.charAt(j);
            }
            wordvalue ^= x;
        }
        return wordvalue;
    }
    static int H(int k, int i, int m){
        return (((k%m)+i*i)%m);
    }
    static void wstaw(Dane[] T, int m, Dane[] tab){
        for(int i=0; i<m*0.8; i++){
            int value = konwertuj(T[i].nazwisko);
            int flaga=0;
            int j=0;
            int H=0;
            while(flaga==0) {
                H = H(value, j, m);
                if (tab[H] == null) {
                    flaga = 1;
                }
                j++;
            }
            tab[H]= new Dane(T[i].nazwisko,T[i].liczba);
        }
    }
    static void szukaj(String nazwisko,int m, Dane[] tab){
        int value = konwertuj(nazwisko);
        int flaga=0;
        int i=0;
        int j=0;
        int H=0;
        int x=0;
        int y=0;
        while(i<m) {
            H = H(value, j, m);
            if (tab[H].nazwisko.equals(nazwisko)) {
                flaga = 1;
                break;
            }else if(tab[H]==null) {
                break;
            }
             else{
                    j++;
                    i++;
             }
        }
        if(flaga==1)
            System.out.println("Znaleziono szukane słowo w kluczu = "+ H+" Ilość prób: "+j);
        else
            System.out.println("Niestety nie znaleziono szukanego slowa");
    }
    public static void main(String[] args) throws FileNotFoundException {
        int m = ilosclini("nazwiskaASCII.txt");
        Dane[] T = new Dane[m];
        Dane[] tab = new Dane[m];
        for(int i=0;i<m; i++){
            tab[i]=null;
        }
        wypelnijTablice(T, "nazwiskaASCII.txt", m);
        wstaw(T, m, tab);
        System.out.println("\n--------- DUŻA TABLICA ----------");
        szukaj(T[10000].nazwisko,m, tab);
        szukaj(T[12441].nazwisko,m, tab);
        szukaj(T[124].nazwisko,m, tab);
        szukaj(T[15476].nazwisko,m, tab);
        szukaj(T[2512].nazwisko,m, tab);
        szukaj(T[12356].nazwisko,m, tab);
        szukaj(T[7000].nazwisko,m, tab);

        // Mała TAB
        m=10;
        Dane[] mT = new Dane[m*2];
        Dane[] mtab = new Dane[m*2];
        for(int i=0;i<m; i++){
            mtab[i]=null;
        }
        wypelnijTablice(mT, "nazwiskaASCII.txt", m);
        wstaw(mT, m, mtab);
        System.out.println("\n--------- MAŁA TABLICA ----------");
        for(int i=0; i<10;i++) {
            try {
                System.out.println("NR: " + i + "\tWartosc:" + mtab[i].liczba + " " + mtab[i].nazwisko);
            }catch (NullPointerException e){

            }
        }
        szukaj("Kowalski",m, mtab);
    }
}
