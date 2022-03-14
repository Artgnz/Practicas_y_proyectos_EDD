package Clases;

import java.util.Iterator;

/**
 * 
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class Practica1 {
    

    /**
     * Agrega un número entero en una lista ordenada.
     * @param lista Lista de números ordenados donde se agrega el elemento.
     * @param nuevo Número entero que se agrega a la lista.
     */
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {
        Iterator<Integer> iterador = lista.iterator();
        int indice = 0;
        // Busca el primer elemento mayor o igual al elemento nuevo.
        while (iterador.hasNext()) {
            if (iterador.next() >= nuevo) {
                break;
            }
            indice++;
        }
        lista.insert(indice, nuevo);
        return lista;
    }

    /**
     * Obtiene la unión de dos listas.
     * El tiempo de ejecución del método se puede mejorar usando la clase HashSet de
     * java.util.HashSet, que, de acuerdo a su documentación (https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html),
     * ofrece rendimiento constante para operaciones como agregar y ver si un elemento ya está
     * en el HashSet.
     * El algoritmo sería el siguiente:
     * 1. Iterar sobre la lista 1 y guardar cada elemento en el HashSet
     *    con el método add de la clase HashSet.
     * 2. Iterar sobre la segunda lista, guardar el siguiente elemento en una variable
     *    invocar el método contains del hashSet y, si el elemento no está, se agrega
     *    a la lista y al hashSet (para evitar agregar duplicados).
     * El tiempo de ejecución de esta alternativa es O(N + M) porque se itera una vez
     * sobre las dos listas y todos los métodos que se ejecutan son de tiempo constante.
     * El almacenamiento es O(N + M) porque se almacenan todos los elementos de ambas listas.
     * @param lista1 Primer lista con la que se realiza la unión, guardará los elementos de la
     *               unión.
     * @param lista2 Segunda lista con la que se realiza la unión.
     */
    public static void Union(Lista<Integer> lista1,Lista<Integer> lista2) {
        IteradorLista<Integer> iterador = lista2.iteradorLista();

        while (iterador.hasNext()) {
            Integer elemento = iterador.next();
            if (!lista1.contains(elemento)) {
                lista1.add(elemento);
            }
        }
        return;
    }

    /**
     * Como mejorar el metodo? Podriamos incluir metodos de la clase HashSet de Java, 
     * proveyendo rendimientos de orden constante para los metodos contains y remove (para sustituir delete).
     * Dados dos ejemplares de nuestra clase Lista queremos obtener la intersección de estos.
     * @param lista Primer lista con la que se quiere hacer la interseccion, esta solo contendra a los elementos 
     * compartidos terminará despues de ejecutar el metodo.
     * @param lista2 Segunda lista con la que se quiere hacer la interseccion.
     */
    public static void Interseccion(Lista<Integer> lista,Lista<Integer> lista2) {
	IteradorLista<Integer> iterador = lista.iteradorLista();

	while(iterador.hasNext()){
	    int i = iterador.next();
	    if(!lista2.contains(i))
		lista.delete(i);
	}
        return ;
    }



    public static void main(String[] args) {
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();
        
        
        // Tests toString
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }
        
        String test = "0 -> 1 -> 2 -> 3 -> 4 -> 5";
        if (!primera.toString().equals(test)) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }
            
        // Tests Reverse
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();

        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }
      
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");    
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        // Tests Append
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());

        
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        // Tests IndexOf
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        // Tests Insert
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            
        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);

        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);

        }

        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        // Tests Mezcla Alternada
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }   
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }

        }
        for (int i = 0; i <= 11; i++) {
            
                tercera.add(i);

        }


        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }

        // Tests Agrega Ordenado
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);
        
        
        tercera = AgregaOrdenado(primera,9);
        if (!tercera.toString().equals(segunda.toString())) {
            System.out.println("1 el agregaOrdenado no funciona!");
        }
        
        // Tests Union
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);

        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union no funciona!");
        }

        // Tests interseccion
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Interseccion(primera, segunda);

        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }
        
        



    }   
   

    


}
