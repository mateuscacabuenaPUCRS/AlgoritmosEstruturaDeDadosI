/**
 * Classe App.
 * @author Isabel H. Manssour
 */

public class App {

    public static void main(String[] args) {
        BinaryTreeOfInteger b = new BinaryTreeOfInteger();
        b.addRoot(1);
        b.addLeft(2, 1);
        b.addRight(3, 1);
        b.addRight(4, 3);
        b.addLeft(4, 2);
        b.addRight(5, 2);
        b.addRight(6, 5);
        b.addRight(7, 6);
        
        System.out.println("Total de nodos da arvore: " + b.size());

        // System.out.println("Caminhamento pre:\n"+b.positionsPre());
        // System.out.println("Caminhamento pos:\n"+b.positionsPos());
        // System.out.println("Caminhamento central:\n"+b.positionsCentral());
        // System.out.println("Caminhamento em largura\n"+b.positionsWidth());
        // System.out.println("---------------------");

        System.out.println("média: " + b.media());
        System.out.println("qtd pares: " + b.evenNumbers());
        
        // b.removeBranch(5);
        
        // b.GeraDOT();
    }
}
