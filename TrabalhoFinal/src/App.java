import java.util.List;
import java.util.Scanner;

public class App {
    Scanner in;
    WordTree wordTree;

    App() {
        in = new Scanner(System.in);
        wordTree = new WordTree();
        wordTree.start();
    }

    public void executa() {
        System.out.println("-----Bem vindo ao menu do dicionário!-----");
        System.out.println("Digite um conjunto de caracteres, após a pesquisa na árvore será retornado uma lista de palavras que iniciam com os caracteres fornecidos.");
        String caracteres = capitalize(in.nextLine().toLowerCase());
        List<String> words = wordTree.searchAll(caracteres);

        if(words == null) {
            System.out.println("Desculpe, não encontramos esta palavra :(");
            System.out.println("Gostaria de tentar novamente? Y/N");
            String opcao = in.nextLine();
            
            if(opcao.equalsIgnoreCase("Y")) {
                executa();
            }
            return;
        }

        System.out.println(words);
        System.out.println("Digite uma palavra desta lista para apresentar o significado desta palavra (certifique-se de digitar a primeira letra em maiúsculo).");
        String palavra = capitalize(in.nextLine().toLowerCase());

        if(wordTree.getSignificado(palavra) == null) {
            System.out.println("Não há esta palavra na lista. Gostaria de tentar novamente? Y/N");
            String opcao2 = in.nextLine();

            if(opcao2.equalsIgnoreCase("Y")) {
                executa();
            }
            return;
        }
        System.out.println(palavra + "; " + wordTree.getSignificado(palavra));
    }

    private String capitalize(String string) {
        return String.valueOf(string.charAt(0)).toUpperCase() + string.substring(1,string.length());
    }
}
