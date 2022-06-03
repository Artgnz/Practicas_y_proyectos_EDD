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

	//Pruebas MaxHeap

        //Pruebas esMontMax
	System.out.println("¿Es montículo máximo: {5,4,3,2,1}?");
	System.out.println(MonticuloMaximo.esMontMax(new Integer[] {5,4,3,2,1}));
        System.out.println("¿Es montículo máximo: {5,3,4,3,1}?");
	System.out.println(MonticuloMaximo.esMontMax(new Integer[] {5,3,4,3,1}));
	System.out.println("¿Es montículo máximo: {3, 2, 2, 3, 2, 1}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {3, 2, 2, 3, 2, 1}));
	System.out.println("¿Es montículo máximo: {9,7,4,5,1,6}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {9,7,4,5,1,6}));
        System.out.println("¿Es montículo máximo: {2,3,4,5,2,15}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {15,14,13,12,15,3}));
        System.out.println("¿Es montículo máxínimo: {2,3,4,5,10,3}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {10,9,8,7,1,9}));
        System.out.println("¿Es montículo máximo: {2,3,4,5,10,15}?");
        System.out.println(MonticuloMaximo.esMontMax(new Integer[] {15,14,13,12,7,2}));
	
	Lista<CustomInteger> lista = new Lista<>();
	for(int i = 25; i>= 0; i--){
	    lista.add(new CustomInteger(i));
	}

	MonticuloMaximo<CustomInteger> m = new MonticuloMaximo<>(lista, lista.size());

	System.out.println("Monticulo Maximo: " + m);

	//Prueba Insert

	m.add(new CustomInteger(30));
	System.out.println("Monticulo con 30 insertado: " + m.toString());
	
	//Prueba Delete
	m.delete();
	System.out.println("Monticulo Maximo con 30 eliminado: " + m.toString());
	
	//Prueba MontMax_MontMin

	MonticuloMinimo<CustomInteger> min = MonticuloMinimo.MontMax_MontMin(m);
	System.out.println("Monticulo Maximo a Monticulo minimo: " + min.toString());

	MonticuloMaximo<CustomInteger> max = MonticuloMaximo.MontMin_MontMax(min);
	System.out.println("Monticulo Minimo a Monticulo Maximo: " + max.toString());
    }
}
