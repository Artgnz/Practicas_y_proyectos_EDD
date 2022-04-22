package edd.src.Estructuras;

import java.util.Iterator;
import java.util.Comparator;
import java.lang.Math;

public class Practica3 {
    
    public static void sumaCercana(Lista lista, int N){
        // TODO ADD <Integer>
        Lista<Integer> copia = lista.clone();
        copia.mergeSort(new Comparator<Integer>() {
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

        int izq = 0;
        int der = nums.length - 1;
        int num1 = nums[izq];
        int num2 = nums[der];
        int diferencia = Math.abs(num1 + num2 - N);
        while (izq < der) {
            int difActual = Math.abs(nums[izq] + nums[der] - N);
            if (difActual < diferencia) {
                diferencia = difActual;
                num1 = nums[izq];
                num2 = nums[der];
            }
            if (nums[izq] + nums[der] > N) {
                der--;
            } else {
                izq++;
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

    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
        primosQueSuman(28, 7, 2);
        primosQueSuman(23, 2, 3);
        // int arr[] =  {10, 22, 28, 29, 30, 40}, x = 54;
        Lista<Integer> lista = new Lista<>();
        lista.add(10);
        lista.add(20);
        sumaCercana(lista, 20);
    }
}
