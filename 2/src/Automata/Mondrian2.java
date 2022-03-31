package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian2 extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    /*
     * Metodo que pinta una matriz inicial de Blanco y de rectángulos de dos colores.
     *
     */
    @Override
    public int[][] getAutomata() {
        // Cantidad de colores
        int colores = 2;
        // Escoge un color entre 0 y 1
        int color = (int)(Math.random() * colores);
        for (int i = 0; i < Maux2.length; i ++) {
            for (int j = 0; j < Maux2[i].length; j ++) {
                // Para hacer rectángulos de altura 2
                if (j % 2 == 0) {
                    // Para no tomar un color mayor al valor de colores
                    color = (color + 1) % colores;
                }
                Maux2[i][j] = color;
                MauxCopia[i][j] = color;
            }
            // Para hacer rectángulos de ancho 2
            if (i % 3 == 0){
                color = (color + 1) % colores;
            }
        }
        return Maux2;
    }

    /*
     * Metodo para evolucionar el automata.
     *
     */
    @Override
    public void evoluciona() {

        // Se crea una matriz copia para reemplazar los Valores.
        int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
        
        // Aqui empieza una evolucion.

        //Creamos una pila y una cola para que te diviertas joven Artista. 
        Pila<Integer> pila = new Pila<Integer>();
        Cola<Integer> cola = new Cola<Integer>();
        
        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores
        // Aqui va tu codigo  
        for (int i = 0; i < Maux2.length; i++) { 
            for (int j = 0; j < Maux2.length; j++){
                // Para ver hasta cuatro celdas arriba y abajo
                for (int k = i - 4; k<= i + 4; k++) {
                    // Para ver hasta dos celdas a los lados
                    for (int l = j - 1; l <= j + 1; l++) {
                        // Agrega a los vecinos de Maux2[i][j]. Si k o l es menor que 0, considera como vecinos a
                        // las celdad del otro lado de la matriz. Ocurre lo mismo si k o j es mayor o igual al
                        // tamaño del arreglo.
                        cola.push(Maux2[(k + Maux2.length) % Maux2.length][(k + Maux2.length) % Maux2.length]);
                    }
                }
                if (!cola.isEmpty()) {
                    CopiaM[i][j] = cola.pop();
                }
            }

        }
        // Fors que copian los valores de la copia a la matriz principal.
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}
