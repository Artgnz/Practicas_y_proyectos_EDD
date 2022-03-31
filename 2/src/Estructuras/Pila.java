package edd.src.Estructuras;

public class Pila<T> extends PushPop<T>{
    
    
    // Agregar al inicio.
    public void push(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("");
        }
        Nodo aux = new Nodo(elemento);
        if(isEmpty()){
            this.cabeza=ultimo=aux;
            longi++;
            return ;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi ++;

    }

    /**
     * Regresa un clon de la estructura.
     * 
     * @return un clon de la estructura.
     */
    public Pila<T> clone(){
        return clone(cabeza);
    }

    /**
     * Regresa un clon de la estructura.
     * 
     * @param n Nodo actual
     * @return un clon de la estructura.
     */
    private Pila<T> clone(Nodo n){
        if (n == null) {
            return new Pila<T>();
        }
        Pila<T> nueva = clone(n.siguiente);
        nueva.push(n.elemento);
        return nueva;
    }

    public String toString(){
        if (this.isEmpty()) {
            return "";
        }
        String regreso = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            regreso += " - " + n.siguiente.elemento.toString();
            n = n.siguiente;
        }
        return regreso;
    }

    
}
