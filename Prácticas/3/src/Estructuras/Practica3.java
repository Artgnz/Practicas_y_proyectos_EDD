package edd.src.Estructuras;

import java.util.Iterator;
import java.util.Comparator;
import java.lang.Math;

public class Practica3 {

    /**
     * Encuentra la pareja de números en un alista tal que la suma de estos sea la más cercana
     * a un entero N.
     * @param lista Lista de números.
     * @param N Número al que la suma se debe acercar.
     */
    public static void sumaCercana(Lista<Integer> lista, int N){
        if (lista == null) {
            throw new IllegalArgumentException();
        }
        Lista<Integer> copia = lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

        int nums[] = new int[copia.size()];

        int i = 0;
        // Se copian los números de la lista al arreglo
        for (int num : copia) {
            nums[i] = num;
            i++;
        }

        // Indica un índice a la izquierda del arreglo.
        int izquierda = 0;
        // Indica un índice a la derecha del arreglo.
        int derecha = nums.length - 1;
        // Número en el índice izquierda del arreglo.
        int num1 = nums[izquierda];
        // Número en el índice izquierda del arreglo.
        int num2 = nums[derecha];
        // La diferencia más pequeña hasta el momento.
        int diferencia = Math.abs(nums[izquierda] + nums[derecha] - N);
        while (izquierda < derecha) {
            // Se consigue la diferencia de los números en los índices izquierda y derecha.
            int difActual = Math.abs(nums[izquierda] + nums[derecha] - N);
            // Si es menor, se actualizan valores.
            if (difActual < diferencia) {
                diferencia = difActual;
                num1 = nums[izquierda];
                num2 = nums[derecha];
                // Si es igual a 0, significa que se encontró la suma de que da N.
                if (diferencia == 0) {
                    break;
                }
            }
            // Se actualiza izquierda o derecha.
            if (nums[izquierda] + nums[derecha] > N) {
                derecha--;
            } else {
                izquierda++;
            }
        }

        System.out.println("La pareja es " + num1 + " y " + num2 + " y su suma es " + (num1 + num2));
    }

    public static void permutacionesCadena(String cadena){

    }

    /**
     * Dados 3 números, la suma S, el primo P, y un entero N, encuentra N primos
     * mayores que P, tal que su suma es igual a S.
     * @param S La suma.
     * @param P El número primo.
     * @param N Cantidad de primos mayores que P.
     */
    public static void primosQueSuman(int S, int P, int N){

        System.out.println("Dados N = " + N + ", P = " + P + ", S =" + S);

        // Conseguimos la lista con los primos en el rango (P,S)
        Lista<Integer> listaPrimos = cribaDeEratosthenes(P, S);
        int primos[] = new int[listaPrimos.size()];
        Iterator<Integer> it = listaPrimos.iterator();
        int i = 0;
        // Pasamos la lista de primos a un arreglo.
        while (it.hasNext()) {
            primos[i] = it.next();
            i++;
        }

        // Conseguimos una lista de listas de primos con las soluciones.
        Lista<Lista<Integer>> soluciones = sumarNPrimos(0, N, S, 0, primos, new Lista<Integer>(), new Lista<Lista<Integer>>());
        System.out.println("Salida: ");

        // Hacemos una lista de los primos ocupados
        Lista<Integer> primosSinRepetir = new Lista<>();

        // Recorremos todas las soluciones.
        for (Lista<Integer> solucion : soluciones) {
            for (int primo : solucion) {
                System.out.print(primo + " ");
                // Si el primo no está en la lista de primos sin repetir, lo agregamos.
                if (!primosSinRepetir.contains(primo)) {
                    primosSinRepetir.add(primo);
                }
            }
            System.out.println();
        }

        // Ordenamos la lista de los primos usados.
        primosSinRepetir = primosSinRepetir.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
        });
        System.out.println("porque...");
        
        it = primosSinRepetir.iterator();
        // Si la lista solo tiene dos primos, los imprimimos separados por un "y".
        if (primosSinRepetir.size() == 2) {
            System.out.print(it.next() + " y " + it.next());
            // Si no, los imprimimos separados por comas.
        } else {
            while (it.hasNext()) {
                System.out.print(it.next());
                // Si hay un primo más, imprimimos la coma.
                if (it.hasNext()) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println(" > " + P);
        // Imprimimos cada solución.
        for (Lista<Integer> solucion : soluciones) {
            it = solucion.iterator();
            while (it.hasNext()) {
                int p = it.next();
                System.out.print(p);
                if (it.hasNext()) {
                    System.out.print(" + ");
                }
            }
            System.out.println(" = " + S);
        }        
    }

    /**
     * Encuentra una lista de lista de N primos mayores a P cuya suma es
     * igual a S
     * @param suma La suma conseguida hasta el momento.
     * @param N Cantidad de primos mayores que N.
     * @param S La suma.
     * @param indice Índice del primo que se agregará.
     * @param primos[] Primos en el rango (P, S)
     * @param posiblesPrimos Lista con primos que es una posible respuesta.
     * @param soluciones Lista de listas de primos cuya suma es S y tiene N términos.
     */
    private static Lista<Lista<Integer>> sumarNPrimos(int suma, int N, int S, int indice, int primos[], Lista<Integer> posiblesPrimos, Lista<Lista<Integer>> soluciones) {
        // Si la suma de los primos en la lista es igual a S y son N primos.
        if (suma == S && posiblesPrimos.size() == N) {
            // Se agrega una copia de la lista de primos a las soluciones.
            soluciones.add(posiblesPrimos.clone());
            return soluciones;
        }
        // Si ya no hay primos, o la suma es mayor a S o la posible respuesta tiene más primos que N.
        if (indice >= primos.length || suma > S || posiblesPrimos.size() >= N) {
            return soluciones;
        }
        // Primo en el índice indicado por índice
        int primo = primos[indice];
        // Se agrega un primo a la posible respuesta.
        posiblesPrimos.add(primo);

        // Aumentamos el índice en uno.
        indice++;
        // Llamada recursiva donde se incluye un primo más. 
        sumarNPrimos(suma + primo, N, S, indice, primos, posiblesPrimos, soluciones);
        // Se quita el último primo de la lista.
        posiblesPrimos.pop();
        // Llamada recursiva sin incluir al primo.
        sumarNPrimos(suma, N, S, indice, primos, posiblesPrimos, soluciones);
        return soluciones;
    }

    /**
     * Dados dos números, devuelve los primos que se encuentran en el rango (desde, hasta).
     * @param desde Desde que número se buscan los primos.
     * @param hasta Hasta que número se buscan los primos.
     * @return La lista con los primos en el rango (desde, hasta).
     */
    private static Lista<Integer> cribaDeEratosthenes(int desde, int hasta) {
        // Lista con los primos
        Lista<Integer> primos = new Lista<>();
        // Indica qué números son primos.
        boolean esPrimo[] = new boolean[hasta];
        // Al inicio todos los números en el rango son considerados primos.
        for (int i = 0; i < hasta; i++) {
            esPrimo[i] = true;
        }
        // 0 y 1 no son primos
        esPrimo[0] = esPrimo[1] = false;

        for (int p = 2; p < hasta; p++) {
            // Si un número es primo
            if (esPrimo[p]) {
                // Si es mayor que desde, lo agregamos a la lista
                if (p > desde) {
                    primos.add(p);
                }
                // Todos los múltiplos de p se marcan como no primos
                for (int j = p * p; j < hasta; j += p) {
                    esPrimo[j] = false;
                }
            }
        }

        return primos;
    }

    public static void N_Reinas(int N){

    }

    /**
     * Calcula la raíz cuadrada de un número entero.
     * @param x Número al que se le calculará la raíz cuadrada.
     * @return La raíz cuadrada del número con un márgen de error de 1e-5.
     */
    public static double sqrtBusqBin(int x) {
        int izq = 0;
        int der = x;
        int mitad;
        double respuesta = 0;

        while (izq <= der) {
            // Consguimos la mitad entre izq y der.
            mitad = izq + (der - izq) / 2;
            // Si mitad por mitad es x, ya encontramos la raíz cuadrada.
            if (mitad * mitad == x) {
                respuesta = mitad;
                break;
                // Si mitad por mitad es menor que x, aumentamos izq.
            } else if (mitad * mitad < x) {
                izq = mitad + 1;
                respuesta = mitad;
                // Si mitad por mitad es mayor que x, disminuimos der.
            } else {
                der = mitad - 1;
            }
        }
        // Valor que se aumentará o restará a la respuesta.
        double cambio = .1;
        // Margen de error para la raíz cuadrada.
        double margenDeError = 1e-5;
        // Mientras la resta sea mayor que el margen de error.
        while (x - (respuesta * respuesta) > margenDeError)  {
            while (respuesta * respuesta < x) {
                respuesta += cambio;
            }
            respuesta -= cambio;
            cambio /= 10;
        }
        return respuesta;
    }

    public static void main(String[] args) {
        // Puedes hacer tus pruebas aqui
        Lista<Integer> lista = new Lista<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(5);
        // Pruebas de suma cercana
        System.out.println("De la lista " + lista + ", la pareja de números cuya suma es la más cercana a 6 es: ");
        sumaCercana(lista, 6);

        System.out.println("De la lista " + lista + ", la pareja de números cuya suma es la más cercana a 4 es: ");
        sumaCercana(lista, 4);
        System.out.println("De la lista " + lista + ", la pareja de números cuya suma es la más cercana a 1 es: ");
        sumaCercana(lista, 1);
        lista = lista.mergeSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // Pruebas de primosQueSuman
        primosQueSuman(28, 7, 2);
        primosQueSuman(23, 2, 3);
        primosQueSuman(15, 2, 3);
        // Prueba de buildSorted
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>(lista, true);
        System.out.println("Lista ordenada: " + lista);
        System.out.println("BST a partir de anterior lista: " + arbol);

        // Prueba de search
        System.out.println("¿Se encuentra el " + 6 + " en el árbol? " + arbol.search(6));
        System.out.println("¿Se encuentra el " + 3 + " en el árbol? " + arbol.search(3));
        System.out.println("¿Se encuentra el " + 1 + " en el árbol? " + arbol.search(1));

        // Prueba de delete
        arbol.delete(3);
        arbol.delete(1);
        System.out.println("Se eliminaron: 3, 1 del árbol");
        System.out.println(arbol);

        // Prueba de balance
        arbol.balance();

    }
}
