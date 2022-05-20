package edd.src.Logica;

import edd.src.Estructuras.Lista;
import edd.src.Interfaz.*;

/**
 * Clase que representa el desarrollo de un juego de Encerrado.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class Juego {

    /**
     * Tablero del juego.
     */
    private EncerradoTablero tablero;

    /**
     * Obtiene el movimiento del usuario.
     * @return Movimiento del usuario.
     */
    private String obtenerMovimientoUsuario() {

        // Se obtiene una lista con los posibles movimientos.
        Lista<String> movimientosPosibles = tablero.obtenerMovimientosLegales();

        String mensaje = "Introduzca su movimiento en el formato (A1-B2), donde indica que mueve de la posición A1 a la B2:";
        
        String movimientoUsuario = Interfaz.getString(mensaje, "Ingrese una opción valida.", movimientosPosibles);
        return movimientoUsuario;
    }

    /**
     * Constructor del Juego.
     * Crea un tablero.
     */
    public Juego() {
        tablero = new EncerradoTablero();
    }

    /**
     * Método que se realiza las partidas del juego.
     */
    public void jugar() {
        
        System.out.println("¡Hola, bienvenido al juego de encerrado!");
        String mensaje = "Si desea ver el tutorial, presione 1. Si no, presione 2.";

        int opcion = Interfaz.getInt(mensaje, "Ingrese una opción valida.",1 , 2);
        if (opcion == 1) {
            mostrarTutorial();
        }
        Interfaz.ignoreLine();
        System.out.println("----------Inicio del juego----------");

        while (true) {
            System.out.println(tablero);
            System.out.println("Turno del usuario:");
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
            System.out.println("Turno de la computadora:");
            String movimientoComputadora;
            // Aquí es donde la computadora debe jugar
            // movimientoComputadora = minimaz.obtenerMejorMovimiento(tablero, 10);
            // System.out.println("Movimiento de la computadora: " + movimientoComputadora);
            // System.out.println(tablero);

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

    public void mostrarTutorial() {

        System.out.println("El juego consiste en lograr que las piezas del oponente ya no puedan moverse.");
        System.out.println("Se juega con un tablero como el siguiente:");

        EncerradoTablero tmp = new EncerradoTablero();

        System.out.println(tmp);

        System.out.println("Los jugadores por turnos se alternan para mover una de sus fichas cumpliendo las siguientes reglas:");
        System.out.println("1. Una ficha no puede moverse a espacios ocupados por otras fichas.");
        System.out.print("2. Una ficha solo puede moverse a un espacio si hay una arista que lo conecte con el espacio en el que");
        System.out.println(" se encuentra actualmente dicha ficha.");

        System.out.println("Se moverán las fichas escribiendo el movimiento en el formato:");

        System.out.println("\t [ColumnaDeOrigen][FilaDeOrigen]-[ColumnaDestino][FilaDestino]");

        System.out.println("Por ejemplo, con el siguiente tablero");
        System.out.println(tmp);
        System.out.println("Si es el turno de las fichas rojas, puede mover las fichas de la siguiente forma:");
        System.out.println();
        System.out.println("\tA1-B2");
        System.out.println();
        tmp.mover("A1-B2");
        System.out.println(tmp);
        System.out.println("o");
        tmp = new EncerradoTablero();
        tmp.mover("C3-B2");
        System.out.println("\tC3-B2");
        System.out.println(tmp);
        System.out.println("El juego concluye cuando algún jugador no pueda mover ninguna de sus fichas.");
    }
}
