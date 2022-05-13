package edd.src.Estructuras;

import edd.src.Estructuras.VerticeArbolBinario;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    protected class VerticeAVL extends Vertice {
        public int altura;

        public VerticeAVL(T elemento) {
            super(elemento);
            altura = 0;
        }

        @Override
        public String toString() {

            return null;
        }

        @Override
        public boolean equals(Object otro) {
            if (otro == null || getClass() != otro.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeAVL vertice = (VerticeAVL) otro;
            return altura == vertice.getAltura() && super.equals(otro);
        }

        public int getAltura() {
            return altura;
        }

        @Override
        public int altura() {
            return altura;
        }
    }

    public ArbolAVL() {
        super();
    }

    public ArbolAVL(Collection<T> coleccion) {
        super(coleccion);
    }

    private int altura(VerticeAVL v) {
        int alturaIzq, alturaDer;
        alturaDer = alturaIzq = -1;
        if (v.hayIzquierdo()) {
            alturaIzq = v.izquierdo.altura();
        }

        if (v.hayDerecho()) {
            alturaDer = v.derecho.altura();
        }

        return 1 + Math.max(alturaDer, alturaIzq);
    }

    private int getBalanceVertice(VerticeAVL v) {
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

    @Override
    public void add(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException("El elemento es null.");
        }
        Vertice v = nuevoVertice(elem);
        ultimoAgregado = v;
        raiz = insert((VerticeAVL)raiz, elem);
    }

    public Vertice insert(Vertice vertice, T elem) {

        VerticeAVL v = (VerticeAVL) vertice;

        if (elem == null) {
            throw new IllegalArgumentException("El elemento es null.");
        }

        if (v == null) {
            Vertice nuevo = nuevoVertice(elem);
            elementos++;
            return nuevo;
        }

        if (elem.compareTo(v.elemento) < 0) {
            v.izquierdo = insert(v.izquierdo, elem);
        } else if (elem.compareTo(v.elemento) > 0) {
            v.derecho = insert(v.derecho, elem);

        } else {
            return v;
        }

        v.altura = altura(v);

        int balance = getBalanceVertice(v);

        VerticeAVL vIzq = (VerticeAVL) v.izquierdo;
        VerticeAVL vDer = (VerticeAVL) v.derecho;

        if (balance >= 2) {
            if (getBalanceVertice(vIzq) >= 1) {
                return rotaDerecha(v);
            } else {
                v.izquierdo = rotaIzquierda(vIzq);
                return rotaDerecha(v);
            }
        }

        if (balance == -2) {
            if (getBalanceVertice(vDer) <= -1) {
                return rotaIzquierda(v);
            } else {
                v.derecho = rotaDerecha(vDer);
                return rotaIzquierda(v);
            }
        }
        return v;
    }

    public Vertice rotaDerecha(VerticeAVL v) {
        VerticeAVL hijoIzq = (VerticeAVL) v.izquierdo;
        VerticeAVL hijoDerhijoIzq = (VerticeAVL) hijoIzq.derecho;
        hijoIzq.derecho = v;
        v.izquierdo = hijoDerhijoIzq;

        v.altura = altura(v);
        hijoIzq.altura = altura(hijoIzq);
        return hijoIzq;
    }

    public Vertice rotaIzquierda(VerticeAVL v) {
        VerticeAVL hijoDer = (VerticeAVL) v.derecho;
        VerticeAVL hijoDerIzq = (VerticeAVL) hijoDer.izquierdo;
        hijoDer.izquierdo = v;
        v.derecho = hijoDerIzq;

        v.altura = altura(v);
        hijoDer.altura = altura(hijoDer);
        return hijoDer;
    }


    @Override
    /**
     * Elimina un elemento del Arbol AVL.
     * @param elemento Elemento a borra.
     * @return Si se pudo borrar el elemento.
     */
    public boolean delete(T elemento) {
	int elementosAnterior = elementos;
	raiz = delete(raiz, elemento);
	return elementos == elementosAnterior-1;
    }

    /**
     * Elimina un elemento del árbol.
     * @param vertice Raíz del árbol del que se elimina.
     * @param elem Elemento a eliminar.
     * @return raíz La raíz del árbol donde se eliminó.
     */
    public Vertice delete(Vertice vertice, T elem) {
	VerticeAVL v = (VerticeAVL) vertice;
	
	if(v == null){
	    return null;
	}

	if(elem == null){
	    throw new IllegalArgumentException("El elemento es null.");
	}

	 if (elem.compareTo(v.elemento) < 0) {
	     v.izquierdo = delete(v.izquierdo, elem);
        }

        else if (elem.compareTo(v.elemento) > 0) {
            v.derecho = delete(v.derecho, elem);
        }

         else {
     
            if (v.izquierdo == null) {
                return v.derecho;
            }
     
            if (v.derecho == null) {     
                return v.izquierdo;
            }
     
            Vertice mayorAnterior = sucesorInOrder(v.derecho);     
            v.elemento = mayorAnterior.elemento;    
            v.derecho = delete(v.derecho, v.elemento);
            elementos--;
        }

	 v.altura = altura(v);

	 int balance = getBalanceVertice(v);

	 VerticeAVL vIzq = (VerticeAVL) v.izquierdo;
	 VerticeAVL vDer = (VerticeAVL) v.derecho;

	 if(balance>=2){
	     if(getBalanceVertice(vIzq) >= 1){
		 return rotaDerecha(v);
	     } else {
		 v.izquierdo=rotaIzquierda(vIzq);
		 return rotaDerecha(v);
	     }
	 }

	 if(balance == -2){
	     if(getBalanceVertice(vDer) <= -1){
		 return rotaIzquierda(v);
	     } else {
		 v.derecho = rotaDerecha(vDer);
		 return rotaIzquierda(v);
	     }
	 }
        return v;
    }

    /**
     * Encuentra el sucesor inOrder.
     * @param raiz La raíz del árbol.
     * @return el sucesor inOrder.
     */
     private Vertice sucesorInOrder(Vertice raiz) {
        while (raiz != null && raiz.izquierdo != null) {
            raiz = raiz.izquierdo;
        }
        return raiz;
    }
    
    @Override
    protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }
}
