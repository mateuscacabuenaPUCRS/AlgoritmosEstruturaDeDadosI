
/**
 * Classe de arvore binaria de numeros inteiros.
 *
 * @author Isabel H. Manssour
 */

public class BinaryTreeOfInteger {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        private Integer element;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    // Atributos
    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz

    // Metodos
    public BinaryTreeOfInteger() {
        count = 0;
        root = null;
    }

    /**
     * Remove todos os elementos da arvore.
     */
    public void clear() {
        count = 0;
        root = null;
    }

    /**
     * Verifica se a arvore esta vazia ou nao.
     *
     * @return true se arvore vazia e false caso contrario.
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Retorna o total de elementos da arvore.
     *
     * @return total de elementos
     */
    public int size() {
        return count;
    }

    /**
     * Retorna o elemento armazenado na raiz da arvore.
     *
     * @throws EmptyTreeException se arvore vazia.
     * @return elemento da raiz.
     */
    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    /**
     * Retorna quem e o elemento pai do elemento passado por parametro.
     *
     * @param element
     * @return pai de element
     */
    public Integer getParent(Integer element) {
        // Implementar
        return null;
    }

    /**
     * Altera o elemento da raiz da arvore.
     *
     * @param element a ser colocado na raiz da arvore.
     */
    public void setRoot(Integer element) {
         // Implementar
    }

    /**
     * Insere o elemento como raiz da arvore, se a arvore estiver vazia.
     * @param element a ser inserido.
     * @return true se for feita a insercao, e false caso a arvore nao estiver
     * vazia e a insercao não for feita.
     */
    public boolean addRoot(Integer element) {
        if (root != null) // se a arvore nao estiver vazia
            return false;
        root = new Node(element);
        count++;
        return true;
    }

    /**
     * Insere element a esquerda de elemFather. Se nao encontrar father,
     * ou se father ja tiver um filho a esquerda, element nao e´ 
     * inserido.
     *
     * @param element a ser inserido
     * @param elemFather pai do elemento a ser inserido
     * @return true se foi feita a inserção, e false caso contrario.
     */
    public boolean addLeft(Integer element, Integer elemFather) {
        // Procura pelo pai(=elemFather)
        Node aux = searchNodeRef(elemFather, root);
        
        // Se nao encontrou o pai
        if(aux == null)
            return false;
        
        // Se o pai ja tem filho a esquerda
        if (aux.left != null)
            return false;
        
        // Senão, insere element
        Node n = new Node(element); // primeiro cria o nodo
        n.father = aux; // Faz o novo nodo apontar para o pai
        aux.left = n; // Faz o pai apontar para o novo filho
        count ++; // Atualiza count
        return true;
    }

    /**
     * Insere element a direita de elemFather. Se nao encontrar father,
     * ou se father ja tiver um filho a direita, element nao e´ 
     * inserido.
     *
     * @param element a ser inserido
     * @param elemFather pai do elemento a ser inserido
     * @return true se foi feita a inserção, e false caso contrario.
     */
    public boolean addRight(Integer element, Integer elemFather) {
        // Procura pelo pai(=elemFather)
        Node aux = searchNodeRef(elemFather, root);
        
        // Se nao encontrou o pai
        if(aux == null)
            return false;
        
        // Se o pai ja tem filho a direita
        if (aux.right != null)
            return false;
        
        // Senão, insere element
        Node n = new Node(element); // primeiro cria o nodo
        n.father = aux; // Faz o novo nodo apontar para o pai
        aux.right = n; // Faz o pai apontar para o novo filho
        count ++; // Atualiza count
        return true;
    }

    /**
     * Verifica se element esta ou nao armazenado na arvore.
     *
     * @param element
     * @return true se element estiver na arvore, false caso contrario.
     */
    public boolean contains(Integer element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    // Metodo privado que procura por element a partir de target
    // e retorna a referencia para o nodo no qual element esta
    // armazenado. Retorna null se nao encontrar element.
    private Node searchNodeRef(Integer element, Node target) {
        if (target == null)
            return null;
        
        // Visita a raiz
        if (element.equals(target.element))
            return target;
        
        // Se não achou na raiz, visita a subarvore da esquerda
        Node aux = searchNodeRef(element, target.left);
        
        // Se não achou, visita a subarvore da direita
        if (aux == null) 
            aux = searchNodeRef(element, target.right);
        
        return aux;
    }

    /**
     * Remove um galho da arvore a partir do elemento recebido por parametro.
     *
     * @param element raiz da subarvore a ser removida.
     * @return true se for feita a remocao.
     */
    public boolean removeBranch(Integer element) {
        if (root == null) // se a arvore esta vazia      
            return false;
        
        if(root.element.equals(element)) { // se element esta na raiz
            root = null;
            count = 0;
            return true;
        }
        
        Node aux = searchNodeRef(element, root); // procura por element
        
        if (aux == null) { // se nao achou element
            return false;
        }
        // Se achou element, faz a remocao e atualiza count
        Node pai = aux.father;
        if (pai.left == aux)
            pai.left = null;
        else
            pai.right = null;
        count = count - countNodes(aux);
        aux.father = null; // opcional
        return true;
    }

    // Conta o numero de nodos a partir de "n"
    private int countNodes(Node n) {
        if (n == null)
            return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    /**
     * Troca um elemento da arvore pelo elemento passado por parametro.
     *
     * @param old elemento a ser encontrado para ser substituido.
     * @param element elemento a ser colocado no lugar de old.
     * @return true se fez a troca, false caso contrario.
     */
    public boolean set(Integer old, Integer element) {
        // Implementar
        return false;
    }

    /**
     * Retorna true se element esta armazenado em um nodo externo.
     *
     * @param element
     * @return true se element esta em um nodo externo.
     */
    public boolean isExternal(Integer element) {
        // Implementar
        return false;
    }

    /**
     * Retorna true se element esta armazenado em um nodo interno.
     *
     * @param element
     * @return true se element esta em um nodo interno.
     */
    public boolean isInternal(Integer element) {
        // Implementar
        return false;
    }

    /**
     * Retorna true se element tem um filho a esquerda.
     *
     * @param element
     * @return true se element tem um filho a esquerda, false caso contrario.
     */
    public boolean hasLeft(Integer element) {
        // Implementar
        return false;
    }

    /**
     * Retorna true se element tem um filho a direita.
     *
     * @param element
     * @return true se element tem um filho a direita, false caso contrario.
     */
    public boolean hasRight(Integer element) {
        // Implementar
        return false;
    }

    /**
     * Retorna o filho a esquerda de element.
     *
     * @param element
     * @return o filho a esquerda, ou null se nao tiver filho a esquerda.
     */
    public Integer getLeft(Integer element) {
        // Implementar
        return null;
    }

    /**
     * Retorna o filho a direita de element.
     *
     * @param element
     * @return o filho a direita, ou null se nao tiver filho a direita.
     */
    public Integer getRight(Integer element) {
        // Implementar
        return null;
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem do
     * caminhamento pre-fixado.
     *
     * @return lista com todos os elementos da arvore.
     */
    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        positionsPreAux(root, lista);
        return lista;
    }
    private void positionsPreAux(Node n, LinkedListOfInteger lista) {
        if (n!=null) {
            // Visita a raiz
            lista.add(n.element);
            // Visita a subarvore da esquerda
            positionsPreAux(n.left, lista);
            // Visita a subarvore da direita
            positionsPreAux(n.right, lista);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem do
     * caminhamento pos-fixado.
     *
     * @return lista com todos os elementos da arvore.
     */
    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        positionsPosAux(root, lista);
        return lista;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger lista) {
        if (n!=null) {
            // Visita a subarvore da esquerda
            positionsPosAux(n.left, lista);
            // Visita a subarvore da direita
            positionsPosAux(n.right, lista);
            // Visita a raiz
            lista.add(n.element);            
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem do
     * caminhamento central.
     *
     * @return lista com todos os elementos da arvore.
     */
    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        positionsCentralAux(root, lista);
        return lista;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger lista) {
        if (n!=null) {
            // Visita a subarvore da esquerda
            positionsCentralAux(n.left, lista);
            // Visita a raiz
            lista.add(n.element);              
            // Visita a subarvore da direita
            positionsCentralAux(n.right, lista);          
        }
    }

    /**
     * Retorna uma lista com todos os elementos da arvore na ordem do
     * caminhamento em largura (por niveis).
     *
     * @return lista com todos os elementos da arvore.
     */
    public LinkedListOfInteger positionsWidth() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        if (root != null) { // se a arvore nao estiver vazia
            // Cria a fila
            Queue<Node> fila = new Queue<>();
            fila.enqueue(root);
            while(!fila.isEmpty()) {
                Node aux = fila.dequeue(); // tira o nodo da fila
                lista.add(aux.element); // coloca o elemento na lista
                // Coloca os filhos na fila
                if(aux.left!=null)
                    fila.enqueue(aux.left);
                if(aux.right!=null)
                    fila.enqueue(aux.right);                
            }
        }
        return lista;
    }

    /**
     * Retorna uma String com todos os elementos da arvore na ordem do
     * caminhamento central.
     *
     * @return String com todos os elementos da arvore.
     */
    public String strPositionsCentral() {
        return strPositionsCentral(root);
    }

    private String strPositionsCentral(Node n) {
        String s = "";

        return s;
    }

    public int height() {
        // Implementar
        return -1;
    }

    public int level(Integer element) {
        // Implementar
        return -1;
    }

    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" " + "\n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline 
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }

    public double media() {
        return media(root) / count;
    }

    private double media(Node n) {
        if(n == null) {
            return 0;
        }
        return n.element + media(n.right) + media(n.left);
    }

    public Integer evenNumbers() {
        return evenNumbers(root);
    }

    private Integer evenNumbers(Node n) {
        if(n == null) {
            return 0;
        }
        if(n.element % 2 == 0) {
            return 1 + evenNumbers(n.left) + evenNumbers(n.right);
        }
        return evenNumbers(n.left) + evenNumbers(n.right);

    }
}
