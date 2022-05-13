package edd.src.Estructuras;

import java.lang.Math;

import edd.src.Estructuras.ArbolBinario;

public class Practica4 {
    
    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui

        // Prueba de agrega
        ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();
        for (int i = 2; i <= 100; i += 2) {
            arbolAVL.add(i);
            if (!estaBalanceadoArbol(arbolAVL)) {
                System.out.println("El método add falla.");
            }            
        }

        // Prueba de agrega
        for (int i = 99; i >= 0; i -= 2) {
            arbolAVL.add(i);
            if (!estaBalanceadoArbol(arbolAVL)) {
                System.out.println("El método add falla.");
                System.out.println(arbolAVL.altura());

            }            

        }

    }

    /**
     * Indica si un árbol tiene una altura casi igual a log_{2} de la cantidad
     * de elementos.
     * @param arbol Árbol que se revisa.
     * @return boolean Indica si la altura es la esperada.
     */
    private static boolean estaBalanceadoArbol(ArbolBinario arbol) {
        int alturaEsperada = (int)(Math.log(arbol.size()) / Math.log(2));
        return Math.abs(alturaEsperada - arbol.altura()) <= 1;
    }
}
