package edd.src.Estructuras;

import edd.src.Estructuras.VerticeArbolBinario;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    /**
     * Clase para vértices de árboles AVL.
     */
    protected class VerticeAVL extends Vertice {
        public int altura;

        /**
         * Constructor de VerticeAVL
         */
        public VerticeAVL(T elemento) {
            super(elemento);
            altura = 0;
        }

        /**
         * Regresa una representación en cadena del vértice
         * @return Representación en cadena del vértice.
         *
         */
        @Override
        public String toString() {
            return elemento.toString() + ", altura: " + altura;
        }

        /**
         * Indica si dos vértices son iguales.
         * @param otro Objeto con el que se compara.
         * @return boolean Si son iguales.
         */
        @Override
        public boolean equals(Object otro) {
            if (otro == null || getClass() != otro.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeAVL vertice = (VerticeAVL) otro;
            return altura == vertice.getAltura() && super.equals(otro);
        }

        /**
         * Regresa la altura del vértice.
         * @return int Altura del vértice.
         */
        public int getAltura() {
            return altura;
        }

        /**
         * Regresa la altura del vértice.
         * @return int Altura del vértice.
         */
        @Override
        public int altura() {
            return altura;
        }
    }

    /**
     * Construye un árbol avl vacío.
     *
     */
    public ArbolAVL() {
        super();
    }

    /**
     * Construye un árbol avl a partir de una colección. El árbol
     * avl tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  avl.
     */
    public ArbolAVL(Collection<T> coleccion) {
        super(coleccion);
    }

    /**
     * Calcula la altura de un vértice.
     * @param v Vértice al que se le calcula la altura.
     * @return int La altura dle vértice.
     */
    private int altura(VerticeAVL v) {
        int alturaIzq, alturaDer;
        // Los vértices null tienen altura -1
        alturaDer = alturaIzq = -1;
        if (v.hayIzquierdo()) {
            alturaIzq = v.izquierdo.altura();
        }

        if (v.hayDerecho()) {
            alturaDer = v.derecho.altura();
        }
        
        return 1 + Math.max(alturaDer, alturaIzq);
    }

    /**
     * Nos indica el balance del vértice.
     * Se calcula restando a la altura del vértice izquierdo
     * la altura del vértice derecho.
     * @param v Vértice al que se le calcula el balance.
     * @return int Balance del vértice.
     */
    private int getBalanceVertice(VerticeAVL v) {
        // Los vértices null tienen altura -1
        int alturaIzq = -1;
        int alturaDer = -1;

        if (v.hayIzquierdo()) {
            alturaIzq = v.izquierdo.altura();
        }

        if (v.hayDerecho()) {
            alturaDer = v.derecho.altura();
        }
        return alturaIzq - alturaDer;
    }

    /**
     * Agrega un elemento al árbol AVL.
     * @param eleme Elemento a agregar.
     */
    @Override
    public void add(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException("El elemento es null.");
        }
        Vertice v = nuevoVertice(elem);
        ultimoAgregado = v;
        raiz = insert((VerticeAVL)raiz, elem);
    }

    /**
     * Inserta un elemento en el árbol avl.
     * @param vertice Vértice del subárbol donde se inserta.
     * @param elem Elemento a agregar.
     * @return Vertice La raíz del árbol donde se agregó.
     */
    public Vertice insert(Vertice vertice, T elem) {
        // Se hace la conversión a vértice avl
        VerticeAVL v = (VerticeAVL) vertice;
        // No podemos agregar elementos null
        if (elem == null) {
            throw new IllegalArgumentException("El elemento es null.");
        }

        // Se agrega el elemento.
        if (v == null) {
            Vertice nuevo = nuevoVertice(elem);
            elementos++;
            return nuevo;
        }

        // Entonces el elemento debe ir en el subárbol izquierdo.
        if (elem.compareTo(v.elemento) < 0) {
            v.izquierdo = insert(v.izquierdo, elem);
        // Entonces el elemento debe ir en el subárbol derecho.
        } else if (elem.compareTo(v.elemento) > 0) {
            v.derecho = insert(v.derecho, elem);
            // Si el elemento ya está, no se agrega.
        } else {
            return v;
        }
        // Se actualiza la altura del vértice.
        v.altura = altura(v);
        // Se consigue el balance del vértice.
        int balance = getBalanceVertice(v);

        VerticeAVL vIzq = (VerticeAVL) v.izquierdo;
        VerticeAVL vDer = (VerticeAVL) v.derecho;
        // Si hay más vértices en el subárbol izquierdo.
        if (balance >= 2) {
            if (getBalanceVertice(vIzq) >= 1) {
                // Se realiza una rotación
                return rotaDerecha(v);
            } else {
                // Se realizan dos rotaciones
                v.izquierdo = rotaIzquierda(vIzq);
                return rotaDerecha(v);
            }
        }
        // Si hay más vértices en el subárbol derecho.
        if (balance == -2) {
            if (getBalanceVertice(vDer) <= -1) {
                // Se realiza una rotación
                return rotaIzquierda(v);
            } else {
                // Se realizan dos rotaciones
                v.derecho = rotaDerecha(vDer);
                return rotaIzquierda(v);
            }
        }
        return v;
    }

    /**
     * Rota un vértice a la derecha.
     * @param v vértice a rotar.
     * @return Vertice Raíz del subárbol.
     */
    public Vertice rotaDerecha(VerticeAVL v) {
        VerticeAVL hijoIzq = (VerticeAVL) v.izquierdo;
        VerticeAVL hijoDerhijoIzq = (VerticeAVL) hijoIzq.derecho;
        hijoIzq.derecho = v;
        v.izquierdo = hijoDerhijoIzq;

        // Se actualizan las álturas.
        v.altura = altura(v);
        hijoIzq.altura = altura(hijoIzq);
        return hijoIzq;
    }

    /**
     * Rota un vértice a la izquierda.
     * @param v vértice a rotar.
     * @return Vertice Raíz del subárbol.
     */
    public Vertice rotaIzquierda(VerticeAVL v) {
        VerticeAVL hijoDer = (VerticeAVL) v.derecho;
        VerticeAVL hijoDerIzq = (VerticeAVL) hijoDer.izquierdo;
        hijoDer.izquierdo = v;
        v.derecho = hijoDerIzq;

        // Se actualizan las álturas.
        v.altura = altura(v);
        hijoDer.altura = altura(hijoDer);
        return hijoDer;
    }

    public void delete() {

    }

    /**
     * Crea un nuevo vértice de árbol AVL
     * @param elemento Elemento con el que se creará el vértice.
     * @return Vertice El vértice AVL creado.
     */
    @Override
    protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }
}
