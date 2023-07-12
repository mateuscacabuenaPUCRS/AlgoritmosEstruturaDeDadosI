/**
 * Classe que implementa uma lista linear usando arranjo.
 * @author Isabel H. Manssour
 */

public class ListArrayOfInteger {

    // Atributos
    private static final int INITIAL_SIZE = 10;
    private Integer[] data;
    private int current;
    private int count;

    /**
     * Construtor da lista.
     */
    public ListArrayOfInteger() {
        this(INITIAL_SIZE);
    }

    /**
     * Construtor da lista.
     * @param tam tamanho inicial a ser alocado para data[]
     */
    public ListArrayOfInteger(int tam) {
        if (tam <= 0) {
            tam = INITIAL_SIZE;
        }
        data = new Integer[tam];
        count = 0;
    }

    /**
     * Esvazia a lista.
     */
    public void clear() { // O(1)
        data = new Integer[INITIAL_SIZE];
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() { // O(1)
        return (count == 0);
    }

    /**
     * Retorna o numero de elementos armazenados na lista.
     * @return o numero de elementos da lista
     */
    public int size() { // O(1)
        return count;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void add(Integer element) { // O(n)
        // Primeiro verifica se tem espaco em data[]
        if (count == data.length) {
            setCapacity(data.length * 2);
        }
        data[count] = element;
        count++;
    }

    /**
     * Faz a posicao corrente apontar para o primeiro elemento da lista.
     */
    public void reset() { // O(1)
        current = 0;
    }
    
    /**
     * Retorna o elemento da posicao corrente e atualiza a posicao corrente 
     * para o proximo elemento da lista.
     * @return o elemento da posicao corrente. 
     */
    public Integer next() { // O(1)
        Integer numPosCurrent = null;
        if (current < count) { // se nao chegou no final da lista
            numPosCurrent = data[current]; // guarda o elemento da posicao corrente
            current++; // faz current apontar para proxima posicao
            return numPosCurrent; // retorna o elemento da posicao corrente
        }
        return null;
    } 
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posicao da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public int get(int index) { // O(1)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Index = " + index);
        }
        return data[index];
    }
    
    @Override
    public String toString() { // O(n)
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < count; i++) {
            s.append(data[i]);
            if (i != (count - 1)) {
                s.append(",");
            }
        }
        s.append("\n");
        return s.toString();
    }

    private void setCapacity(int newCapacity) {
        if (newCapacity != data.length) {
            int min = 0;
            Integer[] newData = new Integer[newCapacity];
            if (data.length < newCapacity) {
                min = data.length;
            } else {
                min = newCapacity;
            }
            for (int i = 0; i < min; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    ////////////////////////////////////////////
    // PARA FAZER JUNTO COM A PROFESSORA
    ////////////////////////////////////////////
    
    /**
     * Substitui o elemento armazenado em uma determinada posicao da lista pelo
     * elemento passado por parametro, retornando o elemento que foi substituido.
     * @param index a posicao da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Integer set(int index, Integer element) { // O(1)
        // Primeiro verifica se index eh valido
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index = " + index); // ERRO
        } 
        Integer aux = data[index]; // guarda o elemento "antigo" da posição index
        data[index] = element; // coloca o novo elemento
        return aux;
    }    

    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente
     *
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public boolean remove(Integer element) { // O(n)
        for(int i=0; i<count; i++) {
            if (data[i].equals(element)) {// (data[i]==element) { // se achou element
                // Faz a remocao
                for (int j=i; j<count-1; j++) {
                    data[j] = data[j+1];
                }
                data[count-1] = null; // opcional
                count--;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Procura pelo elemento passado por parametro na lista e retorna true se a 
     * lista contem este elemento.
     * @param element o elemento a ser procurado
     * @return true se a lista contem o elemento passado por parametro
     */
    public boolean contains(Integer element) { // O(n)
        for(int i=0; i<count; i++) {
            if (data[i].equals(element)) {// (data[i]==element) { // se achou element
                return true;
            }
        }
        return false;
    }
    
    /**
     * Insere um elemento em uma determinada posicao da lista
     *
     * @param index a posicao da lista onde o elemento sera inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(int index, Integer element) {
        // Primeiro verifica se index eh valido
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(); // ERRO
        
        // Segundo verifica se tem espaco em data[]
        if (count == data.length) {
            setCapacity(data.length * 2);
        }
        
        // Terceiro, "abre" espaço para o número
        for(int i=count; i>index; i--) {
            data[i] = data[i-1];
        }
        
        // Por fim, adiciona element e atualiza count
        data[index] = element;
        count++;
    }


    
    
    ////////////////////////////////////////////
    // EXERCICIOS: Implemente os metodos 
    // conforme a documentacao javadoc e
    // indique a notacao O().
    ////////////////////////////////////////////
    
    
    /**
     * Remove o elemento de uma determinada posicao da lista
     *
     * @param index a posição da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Integer removeByIndex(int index) {
         // Primeiro verifica se o indice eh valido
         if (index < 0 || index >= size())
             throw new IndexOutOfBoundsException(); // ERRO
         
         Integer aux = data[index]; // guarda o elemento que sera removido
         
         // Faz a remocao
         for (int j=index; j<count-1; j++) {
                    data[j] = data[j+1];
         }
         data[count-1] = null; // opcional
         
         count--; // atualiza o count
         
         return aux; // retorna o elemento removido
    }
   
    
    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     *
     * @param element o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public int indexOf(Integer element) {
        // Procura pelo elemento na lista (ou seja, no array data[]), 
        // se achar retorna o índice
        for (int i = 0; i < count; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        // Neste ponto, não achou: retorna -1
        return -1;
    }

    
    /**
     * Procura pelo elemento passado por parametro na lista e retorna true se a 
     * lista contem este elemento.
     * @param element o elemento a ser procurado
     * @return true se a lista contem o elemento passado por parametro
     */    
    public boolean containsRecursivo(Integer element) {
        return containsRecursivoAux(element, 0);
    }    
    private boolean containsRecursivoAux(Integer element, int i) {
        if (i>=count)  // chegou no final e nao achou element
            return false; 
        if (data[i].equals(element)) // achou element
            return true;
        return containsRecursivoAux(element, i+1);
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
            throw new IndexOutOfBoundsException(); // ERRO
        if (fromIndex > toIndex)
            throw new IllegalArgumentException(); // ERRO
        
        // Instancia o array que sera retornado
        Integer a[] = new Integer[toIndex-fromIndex];
        
        int j=0;
        for(int i=fromIndex; i<toIndex; i++) {
            a[j] = data[i];
            j++;
        }
      
        return a; // retorna o array com a sublista
    }
    
    /**
     * Inverte a ordem dos elementos da lista.
     */
    public void reverse() {
        // [1,2,3,4,,,,,,]
        // lista.reverse()
        // [4,3,2,1,,,,]

        if (count > 1) {
            int f=count-1;
            for(int i=0; i<count/2; i++) {
                Integer aux = data[i];
                data[i] = data[f];
                data[f] = aux;
                f--;
            }
        }
    }
    
    /**
     * Conta o numero de ocorrencias do elemento passado por 
     * parametro.
     * @param element
     * @return 
     */
    public int countOccurrences(Integer element) {
        // [1,2,2,4,5,6,2,8,,,,,]
        // lista.countOcccurrences(2)->3
        // lista.countOcccurrences(8]->1
        int c = 0;
        // Percorre data procurando por element,
        // quando encontrar incrementa "c"
        for(int i=0; i<count; i++) {
            if (data[i].equals(element)) {
                c++;
            }
        }
        return c;
    }    
}
