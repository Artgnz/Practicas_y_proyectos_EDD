package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    /*
     * Metodo que pinta una matriz inicial de rectángulos de un color con un cuadrado de otro
     * color en la esquina inferior derecha.
     *
     */
    @Override
    public int[][] getAutomata() {
        // Ancho del rectángulo
        int ancho = 6;
        // Altura del rectángulo
        int altura = 2 * ancho;
        for (int i = 0; i < Maux2.length; i += altura) {
            for (int j = 0; j < Maux2[i].length; j += ancho) {
                // Escoge un número del 0 al 3
                int num = (int)(Math.random() * 4);
                // Crea el rectángulo con dos colores, el primero tiene asignado un número par
                // y el segundo tiene asignado el valor del número par escogido, más uno.
                hacerRectangulo(i, j, altura, ancho, num * 2, num * 2 + 1);
            }
        }
        return Maux2;
    }

    /**
     * Crea un rectángulo de un color con un cuadrado de otro color en la esquina
     * inferior derecha.
     * @param x Posición x donde empezar el rectángulo.
     * @param y Posición y donde empezar el rectángulo.
     * @param ancho Ancho del rectángulo.
     * @param altura Altura del rectángulo.
     * @param color1 Color del rectángulo grande.
     * @param color2 Color del cuadrado de 1x1.
     *
     */
    public void hacerRectangulo(int x, int y, int ancho, int altura, int color1, int color2) {
        // Para saber hasta qué fila recorrer.
        int yFinal;
        // Si el rectángulo tiene o no una altura que excede el tamaño de la matrix.
        if (y + altura <= Maux2.length) {
            yFinal = y + altura;
        } else {
            yFinal = Maux2.length;
        }
        // Para saber hasta qué columna recorrer.
        int xFinal;
        // Si el rectángulo tiene o no un ancho que excede el tamaño de la matrix.
        if (x + ancho <= Maux2[0].length) {
            xFinal = x + ancho;
        } else {
            xFinal = Maux2[0].length;
        }
 
        for (int i = y; i < yFinal; i ++) {
            for (int j = x; j < xFinal; j++) {
                // Se asigna el color del rectángulo.
                Maux2[i][j] = color1;
                MauxCopia[i][j] = color1;
                // Si se llega a la esquina inferior derecha.
                if (i == yFinal - 1 && j == xFinal - 1) {
                    // Se colorea el cuadrado.
                    Maux2[i][j] = color2;
                    MauxCopia[i][j] = color2;
                }
            }
        }        
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
                // Para ver hasta 1 celda arriba y abajo.
                for (int k = i - 1; k<= i + 1; k++) {
                    // Para ver hasta dos celdas a los lados.
                    for (int l = j - 1; l <= j + 1; l++) {
                        //Ver si los índices son válidos
                        if (k >= 0 && l >=0 && k < Maux2.length
                            && l < Maux2.length && (k != i || l !=j)) {
                            // Solo agrega a la pila colores que al dividirlos entre dos,
                            // tienen el mismo valor.
                            if (Maux2[i][j] / 2 == Maux2[k][l] / 2) {
                                pila.push(Maux2[k][l]);
                            }
                        }
                    }
                }
                // Si la pila no está vacía.
                if (!pila.isEmpty()) {
                    // Asigna el valor de pila.pop()
                    CopiaM[i][j] = pila.pop();
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
