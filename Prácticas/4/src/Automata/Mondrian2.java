package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian2 extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    

    /*
     * Metodo que pinta una matriz inicial de Blanco y le da valores aleatorios a las
     * casillas.
     *
     */
    @Override
    public int[][] getAutomata() {
        int aux1; 
        //Inicializo dos matrices en blanco
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = 2;
                MauxCopia[i][j] = 2;
            }
        }
        // Modifico cada valor de la matriz Maux de forma aleatoria. Para empezar con un estado random de colores
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {

                aux1 = (int) (Math.random() * 6); // Random del 0 al 12

                if (aux1 < 3) {
                    Maux2[i][j] = 3; // Color negro
                } else {
                    Maux2[i][j] = 1; // Azul
                }
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
        //Cola<Integer> cola = new Cola<Integer>();
        
        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores
        
        // Aqui va tu codigo  

        for (int i = 0; i < Maux2.length; i++) {
			for (int j = 0; j < Maux2.length; j++) {
                
                // Revisamos la vecindad de cada casilla.
                for (int k = i - 1; k <= i + 1; k++) {
					for (int l = j - 1; l <= j + 1; l++) {

                        if (k >= 0 && l >= 0 && k < Maux2.length && l < Maux2.length && (k != i || l != j)) {
                            if (Maux2[k][l] == 1) {
                                pila.push(1);
                            }
                            else {
                                pila.push(3);
                            }
                        }

                    }
                }

                // Editar los nuevos valores de la matriz CopiaM. segun los valores de la pila.

    
                if (pila.pop() == 1) {
                    CopiaM[i][j] = 1;
                }
                else {
                    CopiaM[i][j] = 3;
                }
            


            }

        }
        



        for (int i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
        // System.out.println("Termine");//SOP que ayuda a saber cuando acaba una
        // evolucion.
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}