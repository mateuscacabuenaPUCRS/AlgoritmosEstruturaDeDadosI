
public class LinkedListOfInteger {

    // Classe interna Node
    private class Node {

        public Integer element;
        public Node next;

        public Node(Integer element) {
            this.element = element;
            next = null;
        }

        public Node(Integer element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Referência para uma posicao corrente da lista encadeada.
    private Node current;     
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    /**
     * Construtor da lista.
     */
    public LinkedListOfInteger() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     *
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     *
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um elemento ao final da lista.
     *
     * @param element elemento a ser adicionado ao final da lista
     */
    public void add(Integer element) { // O(1)
        Node n = new Node(element);
        if (head == null) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        count++;
    }

    /**
     * Faz a posicao corrente apontar para o primeiro elemento da lista.
     */
    public void reset() { // O(1)
        current = head;
    }
    
    /**
     * Retorna o elemento da posicao corrente e atualiza a posicao corrente 
     * para o proximo elemento da lista.
     * @return o elemento da posicao corrente. 
     */
    public Integer next() { // O(1)
        Integer numPosCurrent = null;
        if (current != null) { // se nao chegou no final da lista
            numPosCurrent = current.element; // guarda o elemento da posicao corrente
            current = current.next; // faz current apontar para proxima posicao
            return numPosCurrent; // retorna o elemento da posicao corrente
        }
        return null;
    }    
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     *
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Integer get(int index) { // O(n)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count - 1) {
            return tail.element;
        }

        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.element);
    }

    /**
     * Substitui o elemento armanzenado em uma determinada posicao da lista pelo
     * elemento indicado.
     *
     * @param index a posicao da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Integer set(int index, Integer element) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Integer elem = null;

        if (index == count - 1) { // se for o ultimo, não precisa percorrer a lista
            elem = tail.element;
            tail.element = element;
            return elem;
        }

        Node aux = head;
        for (int i = 0; i < index; i++) { // vai ate a posicao index
            aux = aux.next;
        }
        elem = aux.element;
        aux.element = element;
        return (elem);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Node aux = head;

        while (aux != null) {
            s.append(aux.element.toString());
            s.append("\n");
            aux = aux.next;
        }

        return s.toString();
    }

    ////////////////////////////////////////////////////////////////
    /**
     *
     * Insere um elemento em uma determinada posicao da lista.
     *
     * @param index a posicao da lista onde o elemento sera inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(int index, Integer element) {
        // Primeiro verifica se o indice eh valido
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        // Depois cria o nodo
        Node n = new Node(element);

        // "Encadear" o nodo criado na lista
        if (index == 0) { // se insercao no inicio
            if (count == 0) { // se a lista estava vazia
                tail = n;
            } else {
                n.next = head;
            }
            head = n;
        } else if (index == count) { // se insercao no fim
            tail.next = n;
            tail = n;
        } else { // se insercao no meio
            Node ant = head;
            for (int i = 0; i < index - 1; i++) {
                ant = ant.next;
            }
            n.next = ant.next;
            ant.next = n;
        }

        // Atualiza o atributo count
        count++;
    }

    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente.
     *
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado e ele foi
     * removido, false caso contrario
     */
    public boolean remove(Integer element) {
        if (count == 0) {  // se lista vazia
            return false;
        }

        // Se remocao do primeiro
        if (element.equals(head.element)) {
            head = head.next;
            if (count == 1) {// se tinha apenas um elemento na lista
                tail = null;
            }
            count--;
            return true;
        }

        Node ant = head; // referencia aponta para o anterior
        Node aux = head.next; // referencia aponta para o elemento que esta sendo verificado

        while (aux != null) {
            if (aux.element.equals(element)) { // se achou o elemento a ser removido
                if (aux == tail) { // se remocao do ultimo
                    tail = ant;
                    tail.next = null;
                } else { // se remocao do meio
                    ant.next = aux.next;
                }
                count--;
                return true;
            }
            aux = aux.next;
            ant = ant.next;
        }

        return false;
    }

    /**
     * Remove o elemento de uma determinada posicao da lista.
     *
     * @param index a posicao da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Integer removeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(); // erro
        }
        // Se remocao do primeiro
        if (index == 0) {
            Integer elem = head.element;
            head = head.next;
            if (count == 1) {// se tinha apenas um elemento na lista
                tail = null;
            }
            count--;
            return elem;
        }

        Node ant = head;
        for (int i = 0; i < index - 1; i++) {
            ant = ant.next;
        }
        Integer elem = ant.next.element;
        if (index == count - 1) { //se remocao do ultimo
            tail = ant;
            tail.next = null;
        } else { // se remocao do meio
            ant.next = ant.next.next;
            // Alternativa para o comando acima
            // Node aux = ant.next;
            // ant.next = aux.next;
        }
        count--;
        return elem;
    }

    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento.
     *
     * @param element o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public int indexOf(Integer element) {
        Node aux = head;
        for(int i=0; i<count; i++) {
            if (aux.element.equals(element)) {
                return i;
            }
            aux = aux.next;
        }
        return -1;
    }

    /**
     * Retorna true se a lista contem o elemento especificado.
     * @param element o elemento a ser testado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(Integer element) {
        Node aux = head;
        while(aux != null) {
            if (aux.element.equals(element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
    
    /**
     * Retorna um arranjo que contem os elementos da lista original entre 
     * fromIndex (inclusivo) e toIndex (exclusivo).
     * @param fromIndex posicao a partir da qual os elementos serao inseridos no
     * arranjo a ser retornado
     * @param toIndex indica a posicao final dos elementos que devem ser inseridos
     * @return Um arranjo com um subconjunto dos elementos da lista.
     * @throws IndexOutOfBoundsException se (fromIndex < 0 || toIndex > size())
     * @throws IllegalArgumentException se (fromIndex > toIndex)
     */   
    public Integer[] subList (int fromIndex , int toIndex) {
        // Primeiro verifica se os indices sao validos
        if (fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();
        if (fromIndex >= toIndex)
            throw new IllegalArgumentException();
        
        // Cria o arranjo
         Integer []a = new Integer[toIndex-fromIndex];
         
        // Percorre a lista ate´ a posicao fromIndex
        Node aux = head;
        for (int i = 0; i < fromIndex; i++) { // para "chegar" ate a posicao fromIndex
            aux = aux.next;
        }
        
        // Percorre a lista de fromIndex a toIndex copiando os elementos para o arranjo
        int pos = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            a[pos] = aux.element;
            aux = aux.next;
            pos++;
        }
        
        // Retorna o arranjo
        return a;        
    }    

    public boolean equals (LinkedListOfInteger outra) {
        if (count != outra.count) { // se as listas possuem tamanhos diferentes
            return false; // retorna false
        }
        Node aux = head; // referencia auxiliar para "caminhar" na lista
        Node auxOutra = outra.head; // referencia auxiliar para "caminhar" na outra lista
        for (int i=0; i<count; i++) {
            if (! aux.element.equals(auxOutra.element))  { // se os elementos forem diferentes
                return false; // retorna false
            }
            aux = aux.next;
            auxOutra = auxOutra.next;
        }
        // Se chegou aqui, todos elementos sao iguais e retorna true
        return true;
    }
    
    public int[] toArray() {
        int[] array = new int[count];
        Node n = head;
        for (int i = 0; i < count; i++) {
            array[i] = n.element;
            n = n.next;
        }
        return array;
    }
}
