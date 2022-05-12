
package edd.src.Estructuras;

import java.util.NoSuchElementException;

import edd.src.Estructuras.Lista;

import java.util.Iterator;
import java.util.Comparator;

/**
 * Implementación de árbol binario de búsqueda.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {
    private class Iterador implements Iterator<T> {
        private Pila<Vertice> pila;

        public Iterador() {
            pila = new Pila<Vertice>();
            Vertice p = raiz;
            while (p != null) {
                pila.push(p);
                p = p.izquierdo;
            }
        }

        public boolean hasNext() {
            return !pila.isEmpty();
        }

        public T next() {
            if (pila.isEmpty()) {
                throw new NoSuchElementException("vacio");
            }
            Vertice v = pila.pop();
            if (v.derecho != null) {
                Vertice u = v.derecho;
                while (u != null) {
                    pila.push(u);
                    u = u.izquierdo;
                }
            }

            return v.elemento;
        }
    }

    public ArbolBinarioBusqueda(Lista<T> lista, boolean isSorted ){
        if (isSorted) {
            raiz = buildSorted(lista);
        }
        else{
	    raiz = buildUnsorted(lista);
        }

    }

    protected Vertice ultimoAgregado;
    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {
        super();
    }

    public ArbolBinarioBusqueda(Collection<T> coleccion) {
        super(coleccion);
    }
    /**
     * Busca un elemento en el árbol.
     * @param elem Elemento que se busca.
     * @return boolean Si se encuentra el elemento.
     */
    public boolean search(T elem) {
        return search(raiz, elem);
    }

    /**
     * Busca un elemento en el árbol binario de búsqueda.
     * @param raíz Raíz del árbol de búsqueda en el que se busca.
     * @param elem Elemento que se busca.
     * @return boolean Si se encuentra el elemento.
     */
    public boolean search(Vertice raiz, T elem) {
        if (elem == null) {
            throw new IllegalArgumentException("El elemento es null.");
        }
        // Si se llegó a null, es porque el elemento no está en el árbol.
        if (raiz == null) {
            return false;
        }
        // Si el elemento es igual al de la raíz.
        if (raiz.elemento.equals(elem)) {
            return true;
        }

        if (raiz.elemento.compareTo(elem) < 0) {
            // Buscamos en el subárbol derecho.
            return search(raiz.derecho, elem);
        } else {
            // Buscamos en el subárbol izquierdo.
            return search(raiz.izquierdo, elem);
        }
    }
    /**
     * Elimina un elemento del BST.
     * @param elemento Elemento a borra.
     * @return Si se pudo borrar el elemento.
     */
    @Override
    public boolean delete(T elemento) {
        int elementosAnterior = elementos;
        raiz = delete(raiz, elemento);
        return elementos == elementosAnterior - 1;
    }

    /**
     * Elimina un elemento del árbol.
     * @param vertice Raíz del árbol del que se elimina.
     * @param elem Elemento a eliminar.
     * @return raíz La raíz del árbol donde se eliminó.
     */
    public Vertice delete(Vertice vertice, T elem) {

        if (vertice == null) {
            return null;
        }

        if (elem == null) {
            throw new IllegalArgumentException("El elemento es null.");
        }
        // Si el elemento a borrar es menor que el elemento en el vértice.
        if (elem.compareTo(vertice.elemento) < 0) {
            // Eliminamos el elemento del subárbol izquierdo.
            vertice.izquierdo = delete(vertice.izquierdo, elem);
            // Si el elemento a borrar es mayor que el elemento en el vértice.
        } else if (elem.compareTo(vertice.elemento) > 0) {
            // Eliminamos el elemento del subárbol derecho.
            vertice.derecho = delete(vertice.derecho, elem);
            // Si el elemento a borrar es igual al del vértice.
        } else {
            // Si el vértice izquierdo es null.
            if (vertice.izquierdo == null) {
                // La raíz de el subárbol será el vértice derecho.
                return vertice.derecho;
            }
            // Si el vértice derecho es null.
            if (vertice.derecho == null) {
                // La raíz de el subárbol será el vértice izquierdo.
                return vertice.izquierdo;
            }
            // Buscamos el sucesor inOrder.
            Vertice mayorAnterior = sucesorInOrder(vertice.izquierdo);
            // El elemento del vértice será el del sucesor inOrder.
            vertice.elemento = mayorAnterior.elemento;
            // Se elimna el sucesorinOrder.
            vertice.derecho = delete(vertice.izquierdo, vertice.elemento);
            elementos--;
        }
        return vertice;
    }

    /**
     * Balancea el árbol BST.
     */
    public void balance() {
        Iterator<T> it = iterator();
        Lista<T> lista = new Lista<>();
        // Se hace una lista con los elementos.
        while (it.hasNext()) {
            lista.add(it.next());
        }
        // Se construye el árbol con buildSorted.
        raiz = buildSorted(lista);
    }

    /**
     * Encuentra el sucesor inOrder.
     * @param raiz La raíz del árbol.
     * @return el sucesor inOrder.
     */
    public Vertice sucesorInOrder(Vertice raiz) {
        while (raiz != null && raiz.izquierdo != null) {
            raiz = raiz.izquierdo;
        }
        return raiz;
    }


    /**
     * Agrega un elemento a un arbol.
     * @param elemento Elemento del vertice a agregar.
     */
    public void add(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("elemento nulo");
        }
        Vertice v = nuevoVertice(elemento);
        ultimoAgregado = v;
        raiz = insert(raiz, elemento);
    }
    /**
     * Inserta un vertice al arbol.
     * @param root Raiz del arbol a insertar un elemento.
     * @param elem Elemento del vertice a insertar.
     */
    public Vertice insert(Vertice root, T elem) {
        if(elem == null)
            throw new IllegalArgumentException("El elemento es null");

        if(root == null){
            Vertice nuevo = nuevoVertice(elem);
            elementos++;
            return nuevo;
        }

        if(elem.compareTo(root.elemento) < 0){
            root.izquierdo = insert(root.izquierdo, elem);
        }

        if(elem.compareTo(root.elemento) > 0){
            root.derecho = insert(root.derecho, elem);

        }

        return root;
    }
	
  
    /**
     * Convierte un arbol binario a un arbol binario de busqueda.
     * @param arbolBinario Arbol binario a convertir.
     * @param Vertice Raiz del arbol convertido.
     */
    public Vertice convertBST(ArbolBinario<T> arbolBinario){
	if(arbolBinario.raiz == null)
	    throw new IllegalArgumentException("El arbol es null");
	else{

	    Iterator<T> it = iterator();
	    Lista<T> lista = new Lista<>();
	    // Se hace una lista con los elementos.
	    while (it.hasNext()) {
		lista.add(it.next());
	    }
        // Se construye el árbol con buildUnsorted.
	    raiz = buildUnsorted(lista);
	    return raiz;
	}
	
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     *Crea un arbol binario ordenado a partir de una lista desordenada
     *@param lista Lista desordenada a convertir en arbol binario de busqueda.
     *@return Vertice Raiz del BST construido.
     */
    public Vertice buildUnsorted(Lista<T> lista){
	Lista<T> copia = lista.mergeSort(new Comparator<T> () {
                    @Override
                    public int compare(T o1, T o2) {
                        return o1.compareTo(o2);
                    }
                });
	return buildSorted(copia);
    }
    

    /**
     * Construye un árbol BST a partir de una lista ordenada.
     * @param lista Lista ordenada.
     * @return la raíz del árbol.
     */
    public Vertice buildSorted(Lista<T> lista) {
        int tamano = lista.size();
        return buildSorted(lista.iterator(), 0, tamano - 1);
    }

    /**
     * Construye un BST a partir de un iterador de una lista ordenada.
     * @param it Iterador de la lista ordenada.
     * @param izquierda Desde que índice de la lista construir el árbol.
     * @param derecha Hasta que índice de la lista construir el árbol.
     * @return La raíz del árbol.
     */
    private Vertice buildSorted(Iterator<T> it, int izquierda, int derecha) {
        // Si izquierda es mayor que derecha, se regresa null.
        if (izquierda > derecha) {
            return null;
        }

        // El número que se encuentra a la mitad de izquierda y derecha.
        int mitad = izquierda + (derecha - izquierda) / 2;
        // Se construye el subárbol izquierdo.
        Vertice vIzq = buildSorted(it, izquierda, mitad - 1);
        // Se crea la raíz del árbol.
        Vertice vRaiz = nuevoVertice(it.next());
        // Se asigna el subárbol izquierdo como vértice izquierdo de este árbol.
        vRaiz.izquierdo = vIzq;
        // Se construye el subárbol derecho.
        vRaiz.derecho = buildSorted(it, mitad + 1, derecha);
        // Se regresa la raíz del árbol
        return vRaiz;
    }

    /**
     * Regresa una representación en cadena del árbol en in-OrderDFS.
     * @return String Representación en cadena del árbol en in-OrderDFS.
     */
    public String toString() {
        return toString(raiz);
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @param raiz Vértice para la cadena.
     * @return Representación en cadena del árbol.
     */
    private String toString(Vertice raiz) {
        if (raiz == null) {
            return "";
        }
        String str = toString(raiz.izquierdo);
        str +=  raiz.elemento.toString() + " ";
        str += toString(raiz.derecho);
        return str;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agrefgado un elemento, el comportamiento de este
     * método es indefinido.
     * 
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }
}
