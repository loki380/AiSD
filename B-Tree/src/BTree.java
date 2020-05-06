
public class BTree {
    Node root;
    Integer t;
    BTree(Integer t){
        this.t=t;
        root = new Node();
    }
    class Node{
        Integer[] keys= new Integer[2*t-1];;
        Node[] childern = new Node[2*t];;
        Boolean leaf = true; // If true then node is leaf
        Integer n=0; // Amount of keys
        Node(){
        }
    }
    // Function to split the child y of Node x
    private void splitChild(Node x, Integer i, Node y){
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = t-1;
        // Copy last (t-1) keys of y to new Node z
        for(int j=0; j<t-1;j++){
            z.keys[j]=y.keys[j+t];
        }
        // Copy last t childern of y to z
        if(y.leaf==false){
            for(int j=0;j<t;j++){
                z.childern[j]=y.childern[j+t];
            }
        }
        y.n=t-1;
        // Create space for new child
        for(int j=x.n; j>=i+1; j--){
            x.childern[j+1]=x.childern[j];
        }
        x.childern[i+1]=z;
        for(int j=x.n-1;j>=i;j--){
            x.keys[j+1]=x.keys[j];
        }
        x.keys[i]=y.keys[t-1];
        x.n++;
    }
    private void insertNonFull(Node x, Integer k){
        Integer i = x.n-1;
        if(x.leaf){
            while(i>=0 && k<x.keys[i]){
                x.keys[i+1]=x.keys[i];
                i--;
            }
            x.keys[i+1]=k;
            x.n++;
        }else{
            while(i>=0 && k<x.keys[i]){
                i--;
            }
            if(x.childern[i+1].n == 2*t-1){
                splitChild(x,i+1,x.childern[i+1]);
                if(k>x.keys[i+1]){
                    i++;
                }
            }
            insertNonFull(x.childern[i+1], k);
        }
    }
    private void insert(Integer k){
        Node r = root;
        if (r.n == 2 * t - 1) {
            Node s=new Node();
            s.leaf = false;
            s.n=0;
            s.childern[0] = r;
            splitChild(s, 0, r);
            insertNonFull(s, k);
            root=s;
        } else {
            insertNonFull(r, k);
        }
    }
    void BTreePrint(Node x, int lvl) {
        if(x.leaf){
            for (int i=0; i<lvl; i++) System.out.print("  ");
            for (int i=0;i<x.n;i++) System.out.print(x.keys[i]+" ");
            System.out.println();
        } else{
            BTreePrint(x.childern[x.n], lvl+4);
            for (int i=x.n-1;i>=0;i--){
                for (int j=0;j<lvl;j++) System.out.print(" ");
                System.out.print(x.keys[i]);
                System.out.println();
                BTreePrint(x.childern[i],lvl+4);
            }
        }
    }
    Node BTreeSearch(Node x, int k){
        int i=0;
        while(i<x.n && k>x.keys[i]) i++;
        if(i<=x.n && k == x.keys[i]) {
            return x;
        }
        if(x.leaf) {
            return null;
        } else {
            return BTreeSearch(x.childern[i],k);
        }

    }
    Node getRoot(){
        return root;
    }
    public static void main(String[] args) {
        BTree tree = new BTree(3);
        int[] tab = {21, 49, 90, 6, 22, 82, 50, 64, 61, 94, 9, 3, 60, 28, 56, 10, 73, 53, 88, 78};
        for(int i: tab){
            tree.insert(i);
        }
        tree.BTreePrint(tree.getRoot(), 0);
        System.out.println("\nWynik wyszukiwania: "+tree.BTreeSearch(tree.getRoot(),21));
        System.out.println("\nWynik wyszukiwania: "+tree.BTreeSearch(tree.getRoot(),1));
    }
}
