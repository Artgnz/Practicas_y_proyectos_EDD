package edd.src.Logica;

import edd.src.Estructuras.Lista;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

import java.util.Iterator;
import java.util.Scanner;
/**
 * Clase que representa el desarrollo de un juego de Encerrado.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class Juego {

    private EncerradoTablero tablero;

    private String obtenerMovimientoUsuario() {

        Lista<String> movimientosPosibles = tablero.obtenerMovimientosLegales();

        String mensaje = "Introduzca su movimiento en el formato (A1-B2), donde indica que mueve de la posición A1 a la B2:";

        String movimientoUsuario = Interfaz.getString(mensaje, "Ingrese una opción valida.", movimientosPosibles);
        return movimientoUsuario;
    }
    
    public Juego() {
        tablero = new EncerradoTablero();
    }

    public void jugar() {
        while (true) {
            System.out.println(tablero);
            String movimientoUsuario = obtenerMovimientoUsuario();
            tablero = tablero.mover(movimientoUsuario);
            if (tablero.esVictoria()) {
                System.out.println("++++++++++++++++++++");
                System.out.println("Has ganado!");
                System.out.println("Tablero final:");
                System.out.println(tablero);
                System.out.println("++++++++++++++++++++");
                break;
            }
            System.out.println(tablero);
            String movimientoComputadora;
            // Aquí es donde la computadora debe jugar
            // movimientoComputadora = minimaz.obtenerMejorMovimiento(tablero, 10);
            // System.out.println("Movimiento de la computadora: " + movimientoComputadora);

            // tablero = tablero.mover(movimientoComputadora);
            if (tablero.esVictoria()) {
                System.out.println("++++++++++++++++++++");
                System.out.println("La computadora gana!");
                System.out.println("Tablero final:");
                System.out.println(tablero);
                System.out.println("++++++++++++++++++++");
                break;
            }
        }
    }
}

