package edd.src.Estructuras;

import java.lang.Math;

import edd.src.Estructuras.ArbolBinario;
import edd.src.Estructuras.CustomInteger;
import edd.src.Estructuras.Lista;
import edd.src.Estructuras.MonticuloMinimo;

public class Practica5 {

    public static void main(String[] args) {
        // Prueba de heapSort
        Lista<Integer> l1 = new Lista<>();
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        ArbolBinarioCompleto<Integer> arbolCompleto = new ArbolBinarioCompleto<>();
        
        for (int i = 25; i >= 0; i--) {
            l1.add(i);
            arbol.add(i);
            arbolCompleto.add(i);
        }
        System.out.println("Lista: " + l1);
        System.out.println("Lista ordenada: " + MonticuloMinimo.heapSort(l1));
        System.out.println("Arbol: " + arbol);
        System.out.println("Arbol ordenado con heapSort: " + MonticuloMinimo.heapSort(arbol));

        System.out.println("Arbol binario completo: \n" + arbolCompleto);
        System.out.println("Arbol binario completo ordenado con heapSort: " + MonticuloMinimo.heapSort(arbolCompleto));

        // Prueba de esMontMin
        System.out.println("¿Es montículo mínimo: {0,1,2,3,4,5}?");

        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {0,1,2,3,4,5}));
        System.out.println("¿Es montículo mínimo: {1,3,2,3,4,5}?");
        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {1,3,2,3,4,5}));
        System.out.println("¿Es montículo mínimo: {1,2,2,1,2,3}?");
        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {1, 2, 2, 1, 2, 3}));
        System.out.println("¿Es montículo mínimo: {1,3,6,5,9,8}?");
        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {1,3,6,5,9,8}));
        System.out.println("¿Es montículo mínimo: {2,3,4,5,2,15}?");
        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {2,3,4,5,2,15}));
        System.out.println("¿Es montículo mínimo: {2,3,4,5,10,3}?");
        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {2,3,4,5,10,3}));
        System.out.println("¿Es montículo mínimo: {2,3,4,5,10,15}?");
        System.out.println(MonticuloMinimo.esMontMin(new Integer[] {2,3,4,5,10,15}));
    }
}
