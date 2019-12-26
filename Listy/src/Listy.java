public class Listy {

    class Node {
        Node next;
        Node prev;
        String word;

        Node(String data) {
            next = null;
            prev = null;
            this.word = data;
        }
    }

    public void wstaw(String word, Node x) {
        Node newNode = new Node(word);
        newNode.next = x.next;
        newNode.prev = x;
        x.next.prev = newNode;
        x.next = newNode;
    }

    public void wstaw1(String word, Node x) {
        Node newNode = new Node(word);
        newNode.next = x.prev.next;
        newNode.prev = x.prev;
        x.prev.next = newNode;
        x.prev = newNode;
    }

    public void drukuj(Node x) {
        String output = "";
        Node newNode = x.next;
        while (newNode != x) {
            output += newNode.word + " ";
            newNode = newNode.next;
        }
        System.out.println(output);
    }

    public Node szukaj(String word2, Node x) {
        Node newNode = x.next;
        if(newNode == null) {
            return null;
        } else {
            while (newNode != x) {
                if(newNode.word.equals(word2)) {
                    return newNode;
                }
                newNode = newNode.next;
            }
            return null;
        }
    }

    public void usun(String word, Node x) {
        Node newNode;
        if((newNode = szukaj(word,x)) != null) {
            while ((newNode= szukaj(word,x))!=null) {
                newNode.prev.next = newNode.next;
                newNode.next.prev = newNode.prev;
            }
        } else {
            System.out.println("Nie ma elementu do usuniecia.");
        }
    }

    public void kasuj(Node x) {
        Node newNode = x.next;
        while (newNode != x) {
            newNode.prev.next = newNode.next;
            newNode.next.prev = newNode.prev;
            newNode = newNode.next;
        }
        x = null;
    }

    public Node bezpowtorzen(Node x) {
        Node sentinel2 = new Node(null);
        sentinel2.prev = sentinel2.next = sentinel2;
        Node newNode = x.next;
        while (newNode.word != null) {
            if(szukaj(newNode.word,sentinel2) == null) {
                wstaw1(newNode.word,sentinel2);
            }
            newNode = newNode.next;
        }
        return sentinel2;
    }

    public Node scal(Node l1, Node l2) {
        Node l3 = new Node(null);
        l3.prev = l3.next = l3;

        l3.next = l1.next;
        l1.next.prev = l3;

        l1.prev.next = l2.next;
        l2.next.prev = l1.prev;

        l2.prev.next = l3;
        l3.prev = l2.prev;

        l1 = l2 = null;
        return l3;
    }

    public static void main(String[] args) {
        Listy list = new Listy();
        Listy.Node l1 = list.new Node(null);
        l1.next = l1.prev = l1;
        System.out.println("------LISTA------");
        list.wstaw("Pierwsze", l1);
        list.wstaw("Drugie", l1);
        list.wstaw("Trzecie", l1);
        list.usun("Drugie", l1);
        list.wstaw("Czwarte", l1);
        list.wstaw("Piate", l1);
        list.wstaw("Pierwsze", l1);
        list.wstaw("Szoste", l1);
        list.drukuj(l1);
        Node l2 = list.bezpowtorzen(l1); // l2 - l1 bez powtorzen
        //list.print(l2);
        Node l3 = list.scal(l1,l2); // l3 - scalona lista l1 z l2
        //list.print(l3);
    }
}