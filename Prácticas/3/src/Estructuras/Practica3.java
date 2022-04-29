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

        System.out.println("La pareja es " + num1 + " y " + num2 + " y suma es " + (num1 + num2));
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

        Lista<Lista<Integer>> soluciones = sumarPrimos(0, N, S, 0, primos, new Lista<Integer>(), new Lista<Lista<Integer>>());
        System.out.println("Salida: ");
        for (Lista<Integer> solucion : soluciones) {
            for (int primo : solucion) {
                System.out.print(primo + " ");
            }
            System.out.println();
        }
        Lista<Integer> primosSinRepetir = new Lista<>();
        for (Lista<Integer> solucion : soluciones) {
            it = solucion.iterator();
            while (it.hasNext()) {
                int p = it.next();
                if (!primosSinRepetir.contains(p)) {
                    primosSinRepetir.add(p);
                }

            }
        }
        primosSinRepetir = primosSinRepetir.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
        });
        System.out.println("porque...");
        it = primosSinRepetir.iterator();
        if (primosSinRepetir.size() == 2) {
            System.out.print(it.next() + " y " + it.next());
        } else {
            while (it.hasNext()) {
                System.out.print(it.next());
                if (it.hasNext()) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println(" > " + P);
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

    private static Lista<Lista<Integer>> sumarPrimos(int suma, int N, int S, int indice, int primos[], Lista<Integer> posiblesPrimos, Lista<Lista<Integer>> soluciones) {
        if (suma == S && posiblesPrimos.size() == N) {
            soluciones.add(posiblesPrimos.clone());
            return soluciones;
        }
        if (indice >= primos.length || suma > S || posiblesPrimos.size() >= N) {
            return null;
        }
        posiblesPrimos.add(primos[indice]);
        sumarPrimos(suma + primos[indice], N, S, indice + 1, primos, posiblesPrimos, soluciones);
        posiblesPrimos.pop();
        sumarPrimos(suma, N, S, indice + 1, primos, posiblesPrimos, soluciones);
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

    public static double sqrtBusqBin(int x) {
        double margenDeError = 1e-5;
        int izq = 0;
        int der = x;
        int mitad;
        double respuesta = 0;

        while (izq <= der) {
            mitad = izq + (der - izq) / 2;
            if (mitad * mitad == x) {
                respuesta = mitad;
                break;
            } else if (mitad * mitad < x) {
                izq = mitad + 1;
                respuesta = mitad;
            } else {
                der = mitad - 1;
            }
        }

        double cambio = .1;
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
    }
}
