import java.util.ArrayList;
import java.util.List;

public class DisjointSetDS {
    List<Node> set;

    class Node{
        Integer key;
        Node parent;
        Integer rank = 0;
        Node(Integer key){
            this.parent = this;
            this.key=key;
        }
    }
    private DisjointSetDS() {
        this.set = new ArrayList();
    }
    public void makeSet(Integer k){
        this.set.add(new Node(k));
    }
    private static Node FindSet(Node x) {
        if (x != x.parent) {
            x.parent = FindSet(x.parent);
        }
        return x.parent;
    }
    private static void Union(Node x, Node y) {
        if (x.rank < y.rank) {
            x.parent = y;
        } else {
            y.parent = x;
            if (x.rank == y.rank) {
                y.rank++;
            }
        }
    }
    private static void PathToRoot(Node x) {
        System.out.print(x.key);
        if (x != x.parent) {
            System.out.print(" -> ");
            PathToRoot(x.parent);
        }
    }
    public static void main(String[] args) {
        DisjointSetDS Z = new DisjointSetDS();

        for (int i = 0; i < 10; i++) {
            Z.makeSet(i);
        }
        Union(FindSet(Z.set.get(0)), FindSet(Z.set.get(1)));
        Union(FindSet(Z.set.get(2)), FindSet(Z.set.get(3)));
        Union(FindSet(Z.set.get(1)), FindSet(Z.set.get(2)));
        Union(FindSet(Z.set.get(5)), FindSet(Z.set.get(6)));
        Union(FindSet(Z.set.get(7)), FindSet(Z.set.get(8)));
        Union(FindSet(Z.set.get(3)), FindSet(Z.set.get(5)));
        Union(FindSet(Z.set.get(0)), FindSet(Z.set.get(7)));
        for (Node x : Z.set) {
            System.out.format("Key: %x, Parent: %x, Rank: %x\n", x.key, x.parent.key, x.rank);
            System.out.print("Path to root: ");
            PathToRoot(Z.set.get(x.key));
            System.out.println("\n--------------------");
        }
    }
}
