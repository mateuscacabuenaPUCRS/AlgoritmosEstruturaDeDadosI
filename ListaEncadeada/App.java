
public class App {
    public static void main(String[] args) {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        lista.add(3);
        lista.add(12);
        lista.add(17);
        lista.add(26);
        lista.add(38);
        lista.add(45);
        
        int[] ex = lista.toArray();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(ex[i]);
        }

        lista.reset();
        System.out.println("Lista 1:");
        for(int i=0; i<lista.size(); i++){
            System.out.println(lista.next());
        }      
    }
}
