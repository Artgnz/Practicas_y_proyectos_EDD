package edd.src.Interfaz;

import java.util.Scanner;
import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Logica.*;

public class Interfaz {
    private static Scanner scn;
    private static Juego juego;

    /**
     * Método que pide al usuario un número entero.
     * @param scn Scanner que lee la entrada de datos.
     * @param mensaje Un mensaje que le indica al usuario que opciones puede ingresar.
     * @param error Mensaje que indica que se introdujo una opción inválida.
     * @param min El valor mínimo que se puede introducir.
     * @param max El valor máximo que se puede introducir.
     * @return int El número que el usuario introduzca.
     */
    public static int getInt(String mensaje, String error, int min, int max) {
        int val;

        while (true) {
            System.out.println(mensaje);
            if (scn.hasNextInt()) {
                val = scn.nextInt();
                if (val < min || max < val) {
                    System.out.println(error);
                } else {
                    return val;
                }
            } else {
                scn.next();
                System.out.println(error);
            }
        }
    }


    public static String getString(String mensaje, String error, String[] opciones) {
        String entrada;

        while (true) {
            System.out.println(mensaje);
            if (scn.hasNextLine()) {
                entrada = scn.nextLine();
                for (int i = 0; i < opciones.length; i++) {
                    if (entrada.equals(opciones[i])) {
                        return entrada;
                    }
                }
                System.out.println(error);
            } else {
                scn.nextLine();
                System.out.println(error);
            }
        }
    }

    public static void ignoreLine() {
        scn.nextLine();
    }

    public static void main(String[] args) {
        scn = new Scanner(System.in);

        juego = new Juego();
        System.out.println("Bienvenido al juego Wizard.");
        int numJugadores= getInt("Ingrese la cantidad de jugadores (3-6).", "Ingrese una opción válida.", 3, 6);
        scn.nextLine();
        for (int i = 1; i <= numJugadores; i++) {
            String nombre = "";
            while (nombre.isEmpty()) {
                System.out.println("Ingrese el nombre del jugador " + i + ":");
                nombre = scn.nextLine();
            }
            juego.agregaJugador(nombre);
        }
        System.out.println();
        juego.jugar();
    }
}
