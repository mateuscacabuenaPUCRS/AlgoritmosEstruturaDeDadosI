// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1
import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class WordTree {

    // Classe interna
    class CharNode {
        private CharNode father;
        private char character;
        private LinkedList<CharNode> children;
        private String significado;
        private boolean isFinal;

        private Stack<CharNode> words;

        public CharNode(char character) {
            father = null;
            this.character = character;
            children = new LinkedList<>();
        }

        public CharNode(char character, boolean isFinal) {
            father = null;
            this.character = character;
            this.isFinal = isFinal;
            children = new LinkedList<>();
        }

        /**
         * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
         * 
         * @param character - caracter a ser adicionado
         * @param isfinal   - se é final da palavra ou não
         */
        public CharNode addChild(char character, boolean isFinal) {
            for (CharNode child : children) { // for nos filhos do nodo
                if (child.character == character) { // se o nodo já obter este caracter
                    return child;
                }
            }

            // Cria um novo nodo para o filho
            CharNode childNode = new CharNode(character, isFinal);
            childNode.father = this; // Define o nodo pai

            children.add(childNode); // Adiciona o filho à lista de filhos

            return childNode;
        }

        public int getNumberOfChildren() {
            return children.size();
        }

        public CharNode getChild(int index) {
            if ((index < 0) || (index >= children.size())) {
                throw new IndexOutOfBoundsException();
            }
            return children.get(index);
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * 
         * @return a palavra
         */
        public String getWord() {

            words = new Stack<>();
            String word = "";
            CharNode aux = this;

            while (aux != root) {
                words.push(aux);
                aux = aux.father;
            }

            StringBuilder sb = new StringBuilder();
            while (!words.isEmpty()) {
                aux = words.pop();
                sb.append(aux.character);
            }

            word = sb.toString();
            return word;
        }

        /**
         * Encontra e retorna o nodo que tem determinado caracter.
         * 
         * @param character - caracter a ser encontrado.
         */
        public CharNode findChildChar(char character) {
            if (this.character == character) { // se character é o nodo atual
                return this;
            }

            for (CharNode child : children) {
                if (child.character == character) {
                    return child;
                }
            }
            return null;
        }

    }

    // Atributos
    private CharNode root;
    private int totalNodes = 0; // nodos contidos na wordTrre
    private int totalWords = 0; // palavras contidas na wordTree
    private LinkedList<Palavra> lista; // lista das palavras do .csv

    // Construtor
    public WordTree() {
        root = new CharNode('\0', false);
        start();
    }

    public void start() {
        lista = new LinkedList<>();
        String aux[];

        Path path1 = Paths.get("src\\dicionario.csv");

        try (
                BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {// Charset.defaultCharset())
            String line = reader.readLine();
            while (line != null) {
                aux = line.split(";");
                if (lista.size() == 0) {
                    aux[0] = aux[0].substring(1);
                }
                Palavra p = new Palavra(aux[0], aux[1]);
                lista.add(p);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e.getMessage());
        }

        String palavra = "";
        for (int i = 0; i < lista.size(); i++) {
            palavra = lista.get(i).getPalavra();
            addWord(palavra);
        }
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    /**
     * Adiciona palavra na estrutura em árvore
     * 
     * @param word
     */
    public void addWord(String word) {
        CharNode n = null;
        for (int i = 0; i < word.length(); i++) { // "for" para cada caractere da palavra
            if (i == 0) { // se "i" for o primeiro
                n = root.addChild(word.charAt(i), false);
                totalNodes++;
            } else if (i == word.length() - 1) { // se "i" for o último
                n = n.addChild(word.charAt(i), true); // addChild isFinal true
                addSignificado(n, word);
                totalNodes++;
            } else { // se "i" for no meio
                n = n.addChild(word.charAt(i), false); // addChild isFinal false
                totalNodes++;
            }
        }
        totalWords++;
    }

    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * 
     * @param word
     * @return o nodo final encontrado
     */
    public CharNode findCharNodeForWord(String word) {
        CharNode n = root;
        // verifica se palavra existe
        for (int i = 0; i < word.length(); i++) {
            n = n.findChildChar(word.charAt(i));
            if (n == null) {
                return null;
            }
        }

        return n;
    }

    /**
     * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo
     * dado.
     * Tipicamente, um método recursivo.
     * 
     * @param prefix
     */
    public List<String> searchAll(String prefix) {
        CharNode n = findCharNodeForWord(prefix); // pega o nodo que possui este prefixo
        List<String> palavras = new LinkedList<>();
        return positionsPreAux(n, palavras); // caminhamento para adicionar todas as palavras em que pertence
    }

    public List<String> positionsPreAux(CharNode n, List<String> lista) {
        if (n == null) {// se n for nulo, retorna nada
            return null;
        } else if (!n.isFinal) { // se o nodo não for final
            for (int i = 0; i < n.getNumberOfChildren(); i++) { // for dos filhos
                positionsPreAux(n.getChild(i), lista); // acessa os filhos
            }
        } else {
            String word = n.getWord(); // caso seja final, forma a palavra
            lista.add(word); // adiciona a palavra na lista
        }
        return lista;
    }

    public void addSignificado(CharNode n, String word) {
        for (Palavra p : lista) {
            if (p.getPalavra() == word) {
                n.significado = p.getSignificado();
            }
        }
    }

    public String getSignificado(String word) {
        return findCharNodeForWord(word) != null
                ? findCharNodeForWord(word).significado
                : null;
    }
}
