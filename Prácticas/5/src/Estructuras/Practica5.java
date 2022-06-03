package edd.src.Estructuras;

import java.lang.Math;

import edd.src.Estructuras.ArbolBinario;
import edd.src.Estructuras.CustomInteger;
import edd.src.Estructuras.Lista;
import edd.src.Estructuras.MonticuloMinimo;
import edd.src.Estructuras.MonticuloMaximo;

public class Practica5 {

    public static void main(String[] args) {
        // Prueba de heapSort
        Lista<Integer> l1 = new Lista<>();
        for (int i = 3; i >= 0; i--) {
            l1.add(i);
        }
        System.out.println("Lista: " + l1);
        System.out.println("Lista ordenada: " + MonticuloMinimo.heapSort(l1));

        // Pruebe de esMinHeap
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

	//Pruebas MaxHeap

        //Pruebas esMaxHeap
	System.out.println("¿Es montículo máximo: {5,4,3,2,1}?");
	System.out.println(MonticuloMaximo.esMontMax(new Integer[] {5,4,3,2,1}));
        System.out.println("¿Es montículo máximo: {5,3,4,3,1}?");
	System.out.println(MonticuloMaximo.esMontMax(new Integer[] {5,3,4,3,1}));
	System.out.println("¿Es montículo mínimo: {3, 2, 2, 3, 2, 1}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {3, 2, 2, 3, 2, 1}));
	System.out.println("¿Es montículo mínimo: {9,7,4,5,1,6}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {9,7,4,5,1,6}));
        System.out.println("¿Es montículo mínimo: {2,3,4,5,2,15}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {15,14,13,12,15,3}));
        System.out.println("¿Es montículo mínimo: {2,3,4,5,10,3}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {10,9,8,7,1,9}));
        System.out.println("¿Es montículo mínimo: {2,3,4,5,10,15}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {15,14,13,12,7,2}));
	
	//Prueba Insert
	
	//Prueba Delete

	//Prueba MontMin_MontMax

	//
    }
}
