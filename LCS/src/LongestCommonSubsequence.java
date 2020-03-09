public class LongestCommonSubsequence {
    void lcs (String s1, String s2){
        char[] X=s1.toCharArray();
        char[] Y=s2.toCharArray();
        int m = X.length;
        int n = Y.length;
        int[][] c = new int[m][n];
        char[][] b = new char[m][n];
        for(int i=0; i<m; i++){ c[i][0] = 0; } // zerujemy pierwszą kolumne i wiersz
        for(int i=1; i<n; i++){ c[0][i] = 0; }
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if (X[i] == Y[i]){
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = '\\';
                }
                else if(c[i-1][j] >= c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    b[i][j] = '|';
                }
                else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = '-';
                }
            }
        }
        PrintLCS(X,b, m-1 , n-1);
//        for(char c: X){
//            System.out.print(c);
//        }
    }
    void PrintLCS(char[] X, char[][] b, int i, int j){
        if(i==0 || j== 0) return;
        if(b[i][j] == '\\'){
            PrintLCS(X,b,i-1, j-1);
            System.out.print(X[i]);
        }else if (b[i][j]== '|'){
            PrintLCS(X,b,i-1, j);
        }else{
            PrintLCS(X,b,i, j-1);
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s1 = "aabca";
        String s2 = "cabba";

        lcs.lcs(s1,s2);
    }
}
