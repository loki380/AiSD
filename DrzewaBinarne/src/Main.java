public class Main {
    class Node{
        int key;
        Node left;
        Node right;
        char przelacznik;

        Node(int key){
            this.key = key;
            right = left = null;
            this.przelacznik = 'L';
        }
    }
    Node root = null;
    public void WSTAW(int key){
        root = insert(root, key);
    }
    public Node insert(Node root, int key){
        if(root==null){
            root = new Node(key);
            return root;
        }
        if(key<root.key)
            root.left = insert(root.left,key);
        else if(key>root.key)
            root.right = insert(root.right,key);
        else{
            if(root.przelacznik == 'L') {
                root.przelacznik = 'P';
                root.left = insert(root.left, key);
            }
            else{
                root.przelacznik = 'L';
                root.right = insert(root.right, key);
            }

        }
        return root;
    }
    void DRUKUJ()  {
        postorder(root, 0);
    }
    void postorder(Node root, int level) {
        if (root != null) {
            postorder(root.right, level+1);
            for(int i=0;i<level; i++){
                System.out.print("   ");
            }
            System.out.println(root.key);
            postorder(root.left, level+1);
        }
    }
    void SZUKAJ(int key)  {
        try {
            Node szukane = search(root, key);
            System.out.println("Znaleziono " + szukane.key);
        }catch(NullPointerException ex){
            System.out.println("Nie znaleziono "+key);
        }
    }
    public Node search(Node root, int key)
    {
        if (root==null || root.key==key)
            return root;
        if (root.key > key)
            return search(root.left, key);
        return search(root.right, key);
    }
    void USUN(int key)
    {
        root = delete(root, key);
    }
    public Node delete(Node root, int key){
        if (root == null)  return root;
        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else
        {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = delete(root.right, root.key);
        }

        return root;
    }
    public Integer minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    public static void main(String[] args) {
        Main tree = new Main();
        tree.WSTAW(12);
        tree.WSTAW(12);
        tree.WSTAW(12);
        tree.WSTAW(17);
        tree.WSTAW(30);
        tree.WSTAW(12);
        tree.WSTAW(12);
        tree.WSTAW(12);
        tree.WSTAW(0);
        tree.WSTAW(4);
        tree.WSTAW(12);
        tree.DRUKUJ();
        tree.SZUKAJ(17);
        tree.USUN(17);
        tree.SZUKAJ(17);
        tree.DRUKUJ();
    }
}
