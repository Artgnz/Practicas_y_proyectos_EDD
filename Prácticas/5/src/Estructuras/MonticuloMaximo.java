package edd.src.Estructuras;

import java.time.Year;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * 
 * Clase para monticulos maximos (Maxheaps)
*/
public class MonticuloMaximo<T extends ComparableIndexable<T>> implements Collection<T>{
    
    
    private class Iterador implements Iterator<T>{
        
        private int indice;

        @Override public boolean hasNext(){
            return indice < elementos;
        }

        @Override public T next(){
            if (hasNext()) {
                return arbol[indice++];
            }
            throw new NoSuchElementException("No hay, no existe");
        }

    }


    private static class Adaptador<T extends Comparable<T>>
    implements ComparableIndexable<Adaptador<T>>{

        private T elemento;

        private int indice;


        public Adaptador(T elemento) {
            this.elemento = elemento;
            this.indice = -1;
        }


        @Override
        public int getIndice() {
            return this.indice;
        }


        @Override
        public void setIndice(int indice) {
            this.indice = indice;
        }


        @Override
        public int compareTo(Adaptador<T> adaptador) {
            return this.elemento.compareTo(adaptador.elemento);
        }
    }

    private int elementos;

    private T[] arbol;


    @SuppressWarnings("unchecked") private T[] nuevoArreglo(int n){
        return (T[])(new ComparableIndexable[n]);
    }

    public MonticuloMaximo(){
        elementos = 0;
        arbol = nuevoArreglo(100);
    }



    public MonticuloMaximo(Iterable<T> iterable, int n ){
        elementos = n;
        arbol = nuevoArreglo(n);
        int i = 0;
        for (T e : iterable) {
           arbol[i] = e;
           arbol[i].setIndice(i);
           i ++;
        }
        for(int j = (elementos-1) /2; j >= 0; j--){
            monticuloMax(j);
            
        }
    }

    private void monticuloMax(int i){
        int izq = i * 2 +1 ;
        int der = i * 2 + 2;
        int maximo = i;

        if (elementos <= i) {
            return;
        }
        if(izq < elementos && arbol[izq].compareTo(arbol[i]) > 0){
            maximo = izq;
        }
        if(der < elementos && arbol[der].compareTo(arbol[maximo]) > 0){
            maximo = der;
        }
        if(maximo == i){
            return;
        }
        else{
            swap(arbol[maximo],arbol[i]);
            monticuloMax(maximo);
        }
    }

    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            duplicaSize();
        }
        elemento.setIndice(elementos);
        arbol[elementos] = elemento;
        elementos++;
        recorreArriba(elementos - 1);
    }

    private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }

    private void recorreArriba(int i){
        int padre = (i-1) / 2;
        int m = i;
        if(padre >= 0 && arbol[padre].compareTo(arbol[i]) < 0){
            m = padre;
        }
        if (m!= i) {
            this.swap(arbol[i],arbol[m]);
            recorreArriba(m);
        }
    }
    
    /**
     * Elimina el elemento maximo del monticulo
     * 
     */
    public T delete(){
        if(elementos == 0){
            throw new IllegalStateException("Monticulo vacio");
        }
        T e = arbol[0];
        boolean bool = delete(e);
        if(bool){
            return e;
        }
        else{
            return null;
        }

    }

    /**
     * Elimina un elemento del monticulo
     * 
     */

    @Override public boolean delete(T elemento){
        if(elemento ==null || isEmpty() ){
            return false;
        }
        if(!contains(elemento)){
            return false;
        }
        int i = elemento.getIndice();
        if(i <0 || elementos <=i )
            return false;
        swap(arbol[i], arbol[elementos -1]);
        arbol[elementos -1] = null;
        elementos --;
        recorreAbajo(i);
        return true;
    }
    
    private void swap(T i, T j) {
        int aux = j.getIndice();
        arbol[i.getIndice()] = j;
        arbol[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }

    private void recorreAbajo(int i){
        if(i < 0){
            return;
        }
        int izq = 2*i +1;
        int der = 2*i +2;
        int max = der;

        if(izq >= elementos && der >= elementos){
            return;
        }
        if(izq < elementos){
            if (der < elementos) {
                if (arbol[izq].compareTo(arbol[der]) < 0 ) {
                    max = der;
                }
            }
            else{
                max = izq;
            }
        }
        if(arbol[max].compareTo(arbol[i])>0){
            swap(arbol[i], arbol[max]);
            recorreAbajo(max);   
        }
    }


    @Override public boolean contains(T elemento){
        for(T e: arbol){
            if(elemento.equals(e))
                return true;
        }
        return false;
    }

    @Override public boolean isEmpty(){
        return elementos == 0;
    }
    
    @Override
    public void empty() {
        for (int i = 0; i < elementos; i++) {
            arbol[i] = null;
        }
        elementos = 0;
    }

    @Override
    public int size(){
        return elementos;
    }

    public T get(int i){
        if (i< 0 || i>= elementos) {
            throw new NoSuchElementException("Indice no valido");
        }
        return arbol[i];
    }


    @Override public String toString(){
        String resultado ="";
        for (int i = 0; i <elementos; i++) {
            resultado += arbol[i].toString() + ",";
        }
        return resultado;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || getClass() != obj.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") MonticuloMaximo<T> monticulo = (MonticuloMaximo<T>)obj;
        if (elementos != monticulo.elementos) {
            return false;
        }
        for (int i = 0; i < elementos; i++) {
            if(!arbol[i].equals(monticulo.arbol[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Regresa un iterador para iterar el montículo mínimo. El montículo se
     * itera en orden BFS.
     * 
     * @return un iterador para iterar el montículo mínimo.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    public static <T extends Comparable<T>> boolean esMontMax(T[] arr) {
        return esMontMax(arr, 0);
    }
    private static <T extends Comparable<T>> boolean esMontMax(T[] arr, int i) {
        if (2 * i + 2 > arr.length) {
            return true;
        }
        if (arr[i].compareTo(arr[2 * i + 1]) < 0) {
            return false;
        }
        if (2 * i + 2 != arr.length && arr[i].compareTo(arr[2 * i + 2]) < 0) {
            return false;
        }
        return esMontMax(arr, 2 * i + 1) && esMontMax(arr, 2 * i + 2);
    }

    public static <T extends ComparableIndexable<T>> T[] MontMin_MontMax(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].setIndice(i);
        }

        for(int j = (arr.length-1) /2; j >= 0; j--){
            heapify(arr, j);
        }
        return arr;
    }

    private static <T extends ComparableIndexable<T>> void heapify(T[] arr, int i) {
        int izq = i * 2 + 1;
        int der = i * 2 + 1;
        int maximo = i;

        if (izq < arr.length && arr[izq].compareTo(arr[i]) > 0) {
            maximo = izq;
        }
        if (der < arr.length && arr[der].compareTo(arr[minimo]) > 0) {
            maximo = der;
        }
        if (maximo == i) {
            return;
        } else {
            swapExterno(arr[maximo], arr[i], arr);
            heapify(arr, maximo);
        }
    }
    private static <T extends ComparableIndexable<T>> void swapExterno(T i, T j, T[] arr) {
        int aux = j.getIndice();
        arr[i.getIndice()] = j;
        arr[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }
}
