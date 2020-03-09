public class Main {
    static final boolean RED = true;
    static final boolean BLACK = false;
    private int counterRed=0;
    class Node{
        Node left=null;
        Node right=null;
        Node parent=null;
        int key;
        boolean color= RED;

        Node(int key) {
            this.key = key;
            this.left = leaf;
            this.right = leaf;
        }
        Node() {
            this.key = 0;
            this.color = BLACK;
            this.left = leaf;
            this.right = leaf;
        }

    }
    Node leaf = new Node();
    Node root = leaf;
    public void WSTAW(int key){
        insert(new Node(key));
    }
    public void insert(Node node){
        if(root==leaf){
            root = node;
            root.parent = leaf;
            root.color=BLACK;
        }
        else {
            Node tmp = root;
            while(true){
                if(node.key < tmp.key){
                    if(tmp.left == leaf){
                        tmp.left = node;
                        node.parent = tmp;
                        break;
                    }
                    else{
                        tmp = tmp.left;
                    }
                }
                else {
                    if(tmp.right == leaf){
                        tmp.right = node;
                        node.parent = tmp;
                        break;
                    }
                    else{
                        tmp = tmp.right;
                    }
                }
            }
        }
        fixInsert(node);
    }
    void fixInsert(Node node){
        while(node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) { // rodzic jest lewym dzieckiem
                if (node.parent.color == RED && node.parent.parent.right.color == RED) { // jeżeli wujek jest Czerwony
                    node.parent.color = BLACK;
                    node.parent.parent.right.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if(node.parent.color == RED && node.parent.parent.right.color == BLACK){ // jeżeli wujek jest Czarny
                    if(node == node.parent.right) {
                        node = node.parent;
                        rot_L(node);
                    }
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rot_R(node.parent.parent);

            } else { // rodzic jest prawym dzieckiem
                if (node.parent.color == RED && node.parent.parent.left.color == RED) {
                    node.parent.color = BLACK;
                    node.parent.parent.left.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if(node.parent.color == RED && node.parent.parent.right.color == BLACK){ // jeżeli wujek jest Czarny
                    if(node == node.parent.left) {
                        node = node.parent;
                        rot_R(node);
                    }
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rot_L(node.parent.parent);
            }
        }
        root.color = BLACK;
    }
    void rot_L(Node node){ // Rotacja w Lewo
        Node tmp = node.right;
        node.right = tmp.left;
        if (tmp.left != leaf) { // jesli temp ma lewe dziecko
            tmp.left.parent = node;
        }
        tmp.parent = node.parent;
        tmp.left = node;
        if (node.parent == leaf) { // jesli node jest korzeniem
            root = tmp;
        }else if(node == node.parent.left) {
            node.parent.left = tmp;
        } else {
            node.parent.right = tmp;
        }
        node.parent = tmp;

    }
    void rot_R(Node node){ // Rotacja w prawo
        Node tmp = node.left;
        node.left = tmp.right;
        if (tmp.right != leaf) { // jeśli temp ma prawe dziecko
            tmp.right.parent = node;
        }
        tmp.parent = node.parent;
        if (node.parent == leaf) { // jesli node jest korzeniem
            root = tmp;
        } else if(node == node.parent.left) {
            node.parent.left = tmp;
        } else {
            node.parent.right = tmp;
        }
        tmp.right = node;
        node.parent = tmp;

    }
    void DRUKUJ()  {
        postorder(root, 0);
    }
    void postorder(Node root, int level) {
        if (root != leaf) {
            postorder(root.right, level+1);
            for(int i=0;i<level; i++){
                System.out.print("     ");
            }
            char colorr = 'R';
            if (root.color == BLACK) colorr = 'B';
            System.out.println(root.key + "(" + colorr + ")");
            postorder(root.left, level+1);
        }
    }
    int min(Node node) {
        if (node == leaf) {
            return 0;
        }
        int x = min(node.left);
        int y = min(node.right);

        return (1 + ((x < y) ? x : y));
    }

    int max(Node node) {
        if (node == leaf) {
            return 0;
        }
        int x = max(node.left);
        int y = max(node.right);

        return (1 + ((x > y) ? x : y));
    }
    void counterRedNodes() {
        counterRedNodes(root);
        System.out.println("Liczba czerwonych wezlow: " + counterRed);
    }
    void counterRedNodes(Node node) {
        if(node != leaf) {
            if(node.color == RED) {
                counterRed++;
            }
            counterRedNodes(node.left);
            counterRedNodes(node.right);
        }
    }
    public static void main(String[] args) {
        Main tree = new Main();
        int[] nodes = {23,43,16,8,22,34,11,8};
//        for (int i = 0; i < nodes.length; i++) {
//            tree.WSTAW(nodes[i]);
//        }
//        tree.DRUKUJ();
        for (int j = 1; j <= 1000; j++) {
            tree.WSTAW(j);
        }
        tree.DRUKUJ();
        System.out.println("Wyniki dla 1,2,3,...,1000");
        System.out.println("\nMinimalna: "+ tree.min(tree.root));
        System.out.println("Maksymalna: "+ tree.max(tree.root));
        tree.counterRedNodes();
    }
}
