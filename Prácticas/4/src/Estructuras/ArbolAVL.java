package edd.src.Estructuras;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    protected class VerticeAVL extends Vertice {

        public VerticeAVL(T elemento) {
            super(elemento);
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
            //TODO COMPLETARLO 
            return super.equals(otro);
        }

        public int getAltura() {
            return -1;
        }
    }


    public ArbolAVL() {
        super();
    }

    public ArbolAVL(Collection<T> coleccion) {
        super(coleccion);
    }
    public void add() {
    }

    public void delete() {
    }
}
