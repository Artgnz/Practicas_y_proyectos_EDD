package edd.src.Estructuras;

import java.util.Iterator;
import java.util.Comparator;
import java.lang.Math;

public class Practica3 {
    
    public static void sumaCercana(Lista<Integer> lista, int N){

        Lista<Integer> copia = lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

        int nums[] = new int[copia.size()];

        int i = 0;
        for (int num : copia) {
            nums[i] = num;
            i++;
        }

        int izquierda = 0;
        int derecha = nums.length - 1;
        int num1 = nums[izquierda];
        int num2 = nums[derecha];
        int diferencia = Integer.MAX_VALUE;
        while (izquierda < derecha) {
            int difActual = Math.abs(nums[izquierda] + nums[derecha] - N);
            if (difActual < diferencia) {
                diferencia = difActual;
                num1 = nums[izquierda];
                num2 = nums[derecha];
                if (diferencia == 0) {
                    break;
                }
            }
            if (nums[izquierda] + nums[derecha] > N) {
                derecha--;
            } else {
                izquierda++;
            }
        }

        System.out.println("La pareja es: " + num1 + " y " + num2);
    }

    public static void permutacionesCadena(String cadena){

    }
    public static void primosQueSuman(int S, int P, int N){
        System.out.println("Dados N = " + N + ", P = " + P + ", S =" + S);

        Lista<Integer> listaPrimos = cribaDeEratosthenes(P, S);
        int primos[] = new int[listaPrimos.size()];
        Iterator<Integer> it = listaPrimos.iterator();
        int i = 0;
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
        System.out.println("porque...");
        for (Lista<Integer> solucion : soluciones) {
            it = solucion.iterator();
            while (it.hasNext()) {
                int p = it.next();
                System.out.print(p);
                if (it.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        // TODO: QUE IMPRIMA BIEN EL ORDEN DE LOS NÚMEROS
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
    private static Lista<Integer> cribaDeEratosthenes(int desde, int n) {

        Lista<Integer> primos = new Lista<>();
        boolean esPrimo[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            esPrimo[i] = true;
        }
        esPrimo[0] = esPrimo[1] = false;
        for (int p = 2; p < n; p++) {
            if (esPrimo[p]) {
                if (p > desde) {
                    primos.add(p);
                }
                for (int j = p * p; j < n; j += p) {
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
        primosQueSuman(28, 7, 2);
        primosQueSuman(23, 2, 3);
        Lista<Integer> lista = new Lista<>();
        lista.add(10);
        lista.add(20);
        System.out.println("Suma cercana de " + lista);

        sumaCercana(lista, 20);
        lista.add(10);
        System.out.println("Suma cercana de " + lista);
        sumaCercana(lista, 20);

        System.out.println(sqrtBusqBin(4));
        System.out.println(sqrtBusqBin(3));
        System.out.println(sqrtBusqBin(3));
        System.out.println(sqrtBusqBin(12069));
        lista = new Lista<>();
        for (int i = 10; i >= 0; i--) {
            lista.add(i);
        }
        System.out.println("Lista desordenada:");

        System.out.println(lista);
        lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
        System.out.println("Lista ordenada: ");
        
        System.out.println(lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            }));

        System.out.println(lista);
        lista = new Lista<>();
        lista = lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
        });
        System.out.println(lista);
        lista.add(1);
        lista = lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
        });
        System.out.println(lista);

        lista = new Lista<>();
        lista.add(2);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(1);
        lista = lista.mergeSort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
        });
        System.out.println(lista);
        
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>(lista, true);
        System.out.println("arbol:");
        System.out.println(arbol);
        lista.add(12);
        lista.add(14);
        lista.add(16);
        lista.add(31);
        arbol = new ArbolBinarioBusqueda<>(lista, true);
        System.out.println(arbol);
    }
}
