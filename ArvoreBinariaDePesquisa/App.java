
public class App {
    public static void main(String[] args) {
        BinarySearchTreeOfInteger b = new BinarySearchTreeOfInteger();
        b.add(50);
        b.add(20);
        b.add(40);
        b.add(70);
        b.add(60);
        b.add(80);
        b.add(90);
        b.add(10);
        b.add(30);
        b.add(15);
        b.add(45);
        b.add(55);
        b.add(65);
        b.add(25);
        b.add(5);
        b.add(35);
        b.add(85);
        b.add(75);
        b.add(95);
        System.out.println(b.countEvenNumbers());
        // b.GeraDOT();
        // System.out.println("b contem 38? " + b.contains(38));
        // System.out.println("b contem 23? " + b.contains(23));
        // System.out.println("b contem 55? " + b.contains(55));
        // System.out.println("Elemento a esq de 23: " + b.getLeft(23));
        // System.out.println("Elemento a esq de 2: " + b.getLeft(2));
        // System.out.println("Total de folhas da arvore: " + b.countLeaves());
     }
   
}
