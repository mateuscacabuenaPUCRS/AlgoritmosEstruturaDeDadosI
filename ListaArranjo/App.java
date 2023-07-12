
public class App {
    public static void main(String[] args) {
        ListArrayOfInteger lista = new ListArrayOfInteger();
        lista.add(9);
        lista.add(10);
        lista.add(15);
        lista.add(22);
        lista.add(37);
        lista.add(46);
        
        lista.reset();
        System.out.println("Lista 2:");
        for(int i=0; i<lista.size(); i++){
            System.out.println(lista.next());
        }        
    }
}
