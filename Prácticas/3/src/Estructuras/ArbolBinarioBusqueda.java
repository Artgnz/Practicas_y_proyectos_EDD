package edd.src.Estructuras;

import java.util.NoSuchElementException;

import edd.src.Estructuras.Lista;

import java.util.Iterator;
import java.util.Comparator;

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
            Lista<T> copia = lista.mergeSort(new Comparator<T> () {
                    @Override
                    public int compare(T o1, T o2) {
                        return o1.compareTo(o2);
                    }
                });
        }

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
    // TODO: delete quizá deba ser private.
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
            Vertice mayorAnterior = menorQueActual(vertice.izquierdo);
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
    public void Balance() {
        Iterator<T> it = iterator();
        Lista<T> lista = new Lista<>();
        // Se hace una lista con los elementos.
        while (it.hasNext()) {
            lista.add(it.next());
        }
        // Se construye el árbol con buildSorted.
        raiz = buildSorted(lista);
    }

    private Vertice menorQueActual(Vertice raiz) {
        while (raiz != null && raiz.izquierdo != null) {
            raiz = raiz.izquierdo;
        }
        return raiz;
    }

    public void add(T elemento) {
        insert(raiz, elemento);
    }

    public void insert(Vertice root, T elem) {
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    public Vertice buildSorted(Lista<T> lista) {
        int tamano = lista.size();
        return buildSorted(lista.iterator(), tamano);
    }

    private Vertice buildSorted(Iterator<T> it, int n) {
        if (n <= 0) {
            return null;
        }

        Vertice izq = buildSorted(it, n / 2);
        Vertice raiz = nuevoVertice(it.next());
        raiz.izquierdo = izq;
        int hojasArbolDerecho = n - (n / 2) - 1;

        raiz.derecho = buildSorted(it, hojasArbolDerecho);
        return raiz;
    }

    public String toString() {
        Iterator<T> it = iterator();
        String str = "";
        while (it.hasNext()) {
            str += it.next();
            if (it.hasNext()) {
                str += " ";
            }
        }
        return str;
    }
}
