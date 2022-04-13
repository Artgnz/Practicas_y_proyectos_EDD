package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

/**
 * Clase que representa a una ronda del juego Wizard.
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */
public class Ronda {

    /**
     * Historial de la ronda
     */
    private Lista<String> historial;
    /**
     * Baraja para jugar.
     */
    private Baraja baraja;
    /**
     * Jugadores participando.
     */
    private Lista<Jugador> jugadores;
    /**
     * Jugador que barajea las cartas.
     */
    private Jugador barajeador;
    /**
     * Palo de triunfo.
     */
    private String paloDeTriunfo;
    /**
     * Número de la ronda
     */
    private int numRonda;

    /**
     * Constructor de la ronda.
     * @param baraja Baraja para jugar la ronda.
     * @param jugadores Jugadores de la ronda.
     * @param numRonda Número de la ronda
     * @param historial Historial del juego.
     */
    public Ronda(Baraja baraja, Lista<Jugador> jugadores, int numRonda, Lista<String> historial) {
        this.historial = historial;
        this.baraja = baraja;
        this.jugadores = jugadores;
        this.numRonda = numRonda;
    }

    /**
     * Escoge el palo de triunfo.
     * @return boolean Si no se forzó la terminación de la partida.
     */
    private boolean escogerPaloDeTriunfo() {
        if (baraja.tieneCartas()) {
            // Se toma una carta para escoger el palo.
            Carta cartaDeTriunfo = baraja.tomarCarta();

            // Si la carta es un mago.
            if (cartaDeTriunfo.getNumero() == 14) {
                String mensaje = "Salió un mago.\n";
                mensaje += "Escoge el palo del triunfo, " + barajeador.getNombre() + "\n";
                mensaje += "Posibles opciones: \n";
                mensaje += "\t rojo\n";
                mensaje += "\t amarillo\n";
                mensaje += "\t verde\n";
                mensaje += "\t azul\n";
                mensaje += "Escriba terminar si desea concluir la partida.";
                String [] opciones = {"rojo", "amarillo", "verde", "azul", "terminar"};
                String opcion = Interfaz.getString(mensaje, "Favor de introducir una opción válida", opciones);
                // Si se forzó la terminación del juego
                if (opcion.equals("terminar")) {
                    System.out.println(barajeador.getNombre() + " terminó la partida.\n");
                    historial.add(barajeador.getNombre() + " terminó la partida.\n");
                    return false;
                }
                paloDeTriunfo = opcion;
                // Si la carta es un bufón.
            } else if (cartaDeTriunfo.getNumero() == 0) {
                paloDeTriunfo = "no hay";
                // Si la carta tiene un número
            } else {
                paloDeTriunfo = cartaDeTriunfo.getPalo();
            }
            baraja.devolverCarta(cartaDeTriunfo);
            // Si no hay cartas en la baraja.
        } else {
            paloDeTriunfo = "no hay";
        }
        // Si no hay palo de triunfo
        if (paloDeTriunfo == "no hay") {
            historial.add("Ronda sin  palo de triunfo.\n\n");
            System.out.println("Ronda sin  palo de triunfo.");
            System.out.println();
        } else {
            historial.add("Palo de triunfo: " + paloDeTriunfo + ".\n\n");
            System.out.println("Palo de triunfo: " + paloDeTriunfo + ".\n");
        }
        // Se escogió el palo de triunfo con éxito.
        return true;
    }

    /**
     * Juega la ronda.
     * @return boolean Si no se forzó la terminación de la partida.
     */
    public boolean jugar() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            jugador.setTrucosGanados(0);
        }
        historial.add("--------------------");
        System.out.println("--------------------");
        historial.add("\nInicio de ronda: " + numRonda + "\n\n");
        System.out.println("Inicio de ronda: " + numRonda + "\n");

        baraja.barajear();
        repartirCartas();
        // Si se forzó la terminación de la partida
        if (!escogerPaloDeTriunfo()) {
            agregarPuntajesHistorial();
            imprimirPuntajes();
            return false;
        }
        // Si se forzó la terminación de la partida
        if (!pedirApuestas()) {
            agregarPuntajesHistorial();
            imprimirPuntajes();
            return false;
        }
        // Si se forzó la terminación de la partida
        if (!jugarTrucos()) {
            agregarPuntajesHistorial();
            imprimirPuntajes();
            return false;
        }
        calcularPuntajes();
        imprimirPuntajes();
        barajeador = null;
        agregarPuntajesHistorial();
        historial.add("Fin de ronda: " + numRonda + "\n");
        System.out.println("Fin de ronda: " + numRonda + "\n");
        historial.add("--------------------");
        System.out.println("--------------------");
        return true;
    }

    /**
     *  Agrega los puntajes de cada jugador al historial en la ronda.
     */
    private void agregarPuntajesHistorial() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            historial.add(jugador.getNombre() + " tiene un puntaje de: " + jugador.getPuntaje() + "\n");
        }
        historial.add("\n");
    }

    /**
     * Devuelve en una lista los jugadores ganadores de la ronda.
     * @return Lista<Jugador> Los ganadores de la ronda.
     */
    public Lista<Jugador> getGanadores() {
        Lista<Jugador> ganadores = new Lista<>();
        // Puntaje máximo de la partida.
        int max = Integer.MIN_VALUE;
        Iterator<Jugador> it = jugadores.iterator();
        // While para conseguir el puntaje máximo
        while (it.hasNext()) {
            Jugador jugador = it.next();
            // Si el puntaje es mayor al máximo conseguido hasta el momento.
            if (jugador.getPuntaje() > max) {
                max = jugador.getPuntaje();
            }

        }
        it = jugadores.iterator();
        // While para conseguir a los jugadores con el puntaje máximo.
        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (jugador.getPuntaje() == max) {
                ganadores.add(jugador);
            }
        }
        return ganadores;
    }

    /**
     * Imprime los puntajes de los jugadores.
     */
    private void imprimirPuntajes() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            System.out.println(jugador.getNombre() + " tiene un puntaje de: " + jugador.getPuntaje());
        }
        System.out.println();
    }

    /**
     * Calcula el puntaje de cada jugador.
     */
    private void calcularPuntajes() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            jugador.calcularPuntaje();
        }
    }

    /**
     * Juega los trucos correspondientes a la ronda.
     * @return boolean Si no se forzo la terminacion de la partida.
     */
    private boolean jugarTrucos() {
        // El primer jugador del truco.
        Jugador primerJugador = obtenerJugadorDespuesDeBarajeador();

        for (int i = 1; i <= numRonda; i++) {
            Truco truco = new Truco(jugadores, primerJugador, paloDeTriunfo, historial);

            historial.add("********************\n");
            System.out.println("********************\n");
            historial.add("Inicio de truco: " + i + ".\n\n");
            System.out.println("Inicio de truco: " + i + ".\n");
            // Si se forzó la terminación del juego.
            if (!truco.jugar()) {
                return false;
            }
            // El ganador será el primer jugador del siguiente truco.
            primerJugador = truco.getGanador();
            baraja.devolverCartas(truco.getCartasUsadas());
            historial.add("Fin de truco: " + i + "\n\n");
            System.out.println("Fin de truco: " + i + "\n");
            historial.add("********************\n");
            System.out.println("********************\n");
        }
        return true;
    }

    /**
     * Obtiene al primer jugador después del que barajeó.
     * @return Jugador Primer jugador después del que barajeó.
     */
    private Jugador obtenerJugadorDespuesDeBarajeador() {
        Iterator<Jugador> it = jugadores.iterator();
	while (it.hasNext()) {
	    Jugador jugador = it.next();
	    if (jugador.equals(barajeador)) {
                // Si no es el último jugador de la lista, se escoge al siguiente jugador.
                if (it.hasNext()) {
                    jugador = it.next();
                    return jugador;
                    // Si es el último jugador de la lista, se escoge al primer
                    // jugador de la lista.
                } else {
                    it = jugadores.iterator();
                    jugador = it.next();
                    return jugador;
                }
	    }
	}
        return null;
    }

    /**
     * Pide a los usuarios sus apuestas.
     * @return boolean Si no se forzó la terminación de la partida.
     */
    private boolean pedirApuestas() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            // Mensaje para pedir la apuesta
            String mensaje = "El palo de triunfo es: " + paloDeTriunfo + ".\n\n";
            mensaje += jugador.getNombre() + ", su mano es la siguiente:\n" + jugador.getManoToString() + "\n";

            mensaje += jugador.getNombre() + ", introduzca su apuesta (0-" + numRonda + ")\n";
            // Mensaje por si el usuario desea concluir la partida
            mensaje += "Escriba -1 si desea concluir la partida.\n";
            mensaje += "Escriba -2 si desea ver el historial.\n";
            while (true) {
                int apuesta = Interfaz.getInt(mensaje, "Introduzca un valor válido.", -2, numRonda);
                // Para no tener un salto de línea de más.
                Interfaz.ignoreLine();
                // Si se desea terminar la partida
                if (apuesta == -1) {
                    historial.add(jugador.getNombre() + " terminó la partida.\n");
                    System.out.println(jugador.getNombre() + " terminó la partida.\n");

                    return false;
                    // Si se desea imprimir el historial.
                } else if (apuesta == -2) {
                    System.out.println("Historial de la partida:");
                    imprimirHistorial();
                    System.out.println();
                    // Si el usuario apostó.
                } else {
                    jugador.setApuesta(apuesta);
                    historial.add(jugador.getNombre() + " apostó " + apuesta + ".\n");
                    System.out.println(jugador.getNombre() + " apostó " + apuesta + ".\n");
                    break;
                }
            }

        }
        historial.add("\n");
        return true;
    }

    /**
     * Imprime el historial del juego.
     */
    private void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }

    /**
     * Reparte las cartas a los usuarios.
     */
    private void repartirCartas() {
        Iterator<Jugador> it = jugadores.iterator();
        // Contador para escoger al barajeador.
        int contador = 1;

        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (contador == (numRonda % jugadores.size()) + 1) {
                barajeador = jugador;
                System.out.println(barajeador.getNombre() + " barajeó las cartas.");
                System.out.println();
                historial.add(barajeador.getNombre() + " barajeó las cartas.\n\n");
            }

            for (int j = 0; j < numRonda; j++) {
                jugador.recibeCarta(baraja.tomarCarta());
            }
            contador++;
        }
    }

    /**
     * Devuelve el historial de la ronda.
     * @return Lista<String> El historial de la ronda.
     */
    public Lista<String> getHistorial() {
        return historial;
    }
}
