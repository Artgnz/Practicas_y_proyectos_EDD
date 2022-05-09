package edd.src.Estructuras;

import edd.src.Estructuras.ArbolAVL;

public class Practica4 {
    
    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
        ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();
        arbolAVL.add(3);
        arbolAVL.add(2);
        arbolAVL.add(1);
        arbolAVL.add(0);
        System.out.println(arbolAVL);
        System.out.println("tamaño: " + arbolAVL.size());
        System.out.println("altura: " + arbolAVL.altura());
        arbolAVL.add(12);
        arbolAVL.add(9);
        arbolAVL.add(10);
        arbolAVL.add(11);
        arbolAVL.add(23);
        System.out.println(arbolAVL);
        System.out.println("tamaño: " + arbolAVL.size());
        System.out.println("altura: " + arbolAVL.altura());
        for (int x = 50; x <= 100; x++) {
            arbolAVL.add(x);
        }
        System.out.println(arbolAVL);
        System.out.println("tamaño: " + arbolAVL.size());
        System.out.println("altura: " + arbolAVL.altura());
    }

}
