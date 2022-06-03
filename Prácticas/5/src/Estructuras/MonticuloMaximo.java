package edd.src.Estructuras;

import java.time.Year;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * 
 * Clase para monticulos maximos (Maxheaps)
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico 
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

    /**
     *Constructor sin parametros de MonticuloMaximo.
     */
    public MonticuloMaximo(){
        elementos = 0;
        arbol = nuevoArreglo(100);
    }


    /**
     *Constructor sin parametros de MonticuloMaximo.
     *@param iterable Estructura iterable.
     *@param n Cantidad de elementos que tendra el monticulo.
     */
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

    /**
     *Metodo que permite ordenar los elementos de la estructura de manera que este este ordenada como monticulo maximo.
     *@param i Indice
     */
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
    /**
     *Inserta un elemento al monticulo.
     *@param elemento Elemento a insertar.
     */
    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            duplicaSize();
        }
        elemento.setIndice(elementos);
        arbol[elementos] = elemento;
        elementos++;
        recorreArriba(elementos - 1);
    }

    /**
     *Duplica el tamanio del arreglo del monticulo.
     */
    private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }

    /**
     *Recamoda los elementos que estan arriba del elemento actual.
     *@param i Indice.
     */
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

    /**
     *Intercambia los elementos dados dos indices del arreglo del monticulo.
     *@param i Primer indice.
     *@param j Segundo indice.
     */
    private void swap(T i, T j) {
        int aux = j.getIndice();
        arbol[i.getIndice()] = j;
        arbol[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }
    /**
     *Recamoda los elementos que estan abajo del elemento actual.
     *@param i Indice.
     */
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


    /**
     *ToString dle monticulo.
     @return String Cadena que representa al monticulo.
    */
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

    /**
     * Determina si un arreglo es Monticulo Maximo.
     * @param arr Arreglo a evaluar.
     * @return boolean Si es o no Monticulo Maximo.
     */
    public static <T extends Comparable<T>> boolean esMontMax(T[] arr) {
        return esMontMax(arr, 0);
    }

    /**
     * Determina si un arreglo es Monticulo Maximo dado el indice de un elemento.
     * @param arr Arreglo a evaluar.
     * @param i Indice.
     * @return boolean Si es o no Monticulo Maximo.
     */
    private static <T extends Comparable<T>> boolean esMontMax(T[] arr, int i) {
        // Si el índice que se revisa es índice de una hoja del árbol.
        if (2 * i + 2 > arr.length) {
            return true;
        }
        // Si el hijo izquierdo del elemento al que apunta el índice es mayor que el padre.
        if (arr[i].compareTo(arr[2 * i + 1]) < 0) {
            return false;
        }
        // Si el hijo derecho del elemento al que apunta el índice es mayor que el padre.
        if (2 * i + 2 != arr.length && arr[i].compareTo(arr[2 * i + 2]) < 0) {
            return false;
        }
        // Llamada recursiva sobre los hijos.
        return esMontMax(arr, 2 * i + 1) && esMontMax(arr, 2 * i + 2);
    }

    /**
     * Convierte un montículo mínimo en uno máximo.
     * Complejidad O(n) porque el método empleado para la construcción del
     * montículo máximo tiene complejidad de O(n).
     * @param <T> Tipo del que puede ser el arreglo.
     * @param mon Montículo mínimo que se convertirá en montículo máximo.
     * @return montículo máximo a partir del montículo mínimo.
     */
    public static <T extends ComparableIndexable<T>> MonticuloMaximo<T> MontMin_MontMax(MonticuloMinimo<T> mon) {
        return new MonticuloMaximo<T>(mon, mon.size());
    }
}
