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
     *
     */
    public Ronda(Baraja baraja, Lista<Jugador> jugadores, int numRonda) {
        historial = new Lista<>();
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
            // if (cartaDeTriunfo.getNumero() == 14) {
            if (cartaDeTriunfo.getNumero() == 14) {
                String mensaje = "Escoge el palo del triunfo, " + barajeador.getNombre() + "\n";
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
                    historial.add("\t" + barajeador.getNombre() + " terminó la partida.\n");
                    return false;
                }
                paloDeTriunfo = opcion;
                // Si la carta es un bufón.
            } else if (cartaDeTriunfo.getNumero() == 0) {
                paloDeTriunfo = null;
                // Si la carta tiene un número
            } else {
                paloDeTriunfo = cartaDeTriunfo.getPalo();
            }
            baraja.devolverCarta(cartaDeTriunfo);
            // Si no hay cartas en la baraja.
        } else {
            paloDeTriunfo = null;
        }
        // Si no hay palo de triunfo
        if (paloDeTriunfo == null) {
            historial.add("\tRonda sin  palo de triunfo.\n");
            System.out.println("Ronda sin  palo de triunfo.\n");
        } else {
            historial.add("\tPalo de triunfo: " + paloDeTriunfo + ".\n");
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

        historial.add("\nInicio de ronda: " + numRonda + "\n");
        System.out.println("Inicio de ronda: " + numRonda + "\n");
        baraja.barajear();
        repartirCartas();
        // Si se forzó la terminación de la partida
        if (!escogerPaloDeTriunfo()) {
            agregarPuntajesHistorial();
            return false;
        }
        // Si se forzó la terminación de la partida
        if (!pedirApuestas()) {
            agregarPuntajesHistorial();
            return false;
        }
        if (!jugarTrucos()) {
            agregarPuntajesHistorial();
            return false;
        }
        calcularPuntajes();
        barajeador = null;
        agregarPuntajesHistorial();
        historial.add("Fin de ronda: " + numRonda + "\n");
        System.out.println("Fin de ronda: " + numRonda + "\n");
        return true;
    }

    private void agregarPuntajesHistorial() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            historial.add(jugador.getNombre() + " tiene un puntaje de: " +             jugador.getPuntaje() + "\n");
        }
    }

    public Lista<Jugador> getGanadores() {
        Lista<Jugador> ganadores = new Lista<>();
        int max = Integer.MIN_VALUE;
        Iterator<Jugador> it = jugadores.iterator();
        while (it.hasNext()) {
            Jugador jugador = it.next();
            System.out.println(jugador.getNombre() + " tiene el puntaje de " + jugador.getPuntaje());

            if (jugador.getPuntaje() > max) {
                max = jugador.getPuntaje();
            }

        }
        it = jugadores.iterator();
        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (jugador.getPuntaje() == max) {
                ganadores.add(jugador);
            }
        }
        return ganadores;
    }
    private void calcularPuntajes() {
        Iterator<Jugador> it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            jugador.calcularPuntaje();
        }
    }
    private boolean jugarTrucos() {
        Jugador primerJugador = barajeador;
        for (int i = 0; i < numRonda; i++) {
            Truco truco = new Truco(jugadores, primerJugador, paloDeTriunfo);
            if (!truco.jugar()) {
                return false;
            }
            // truco.getGanador();
            System.out.println(baraja.tamano());
            baraja.devolverCartas(truco.getCartasUsadas());
            historial.append(truco.getHistorial());
        }
        return true;
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
            String mensaje = jugador.getNombre() + ", su mano es la siguiente:\n" + jugador.getManoToString() + "\n\n";

            mensaje += jugador.getNombre() + " introduzca su apuesta (0-" + numRonda + ")\n";
            // Mensaje por si el usuario desea concluir la partida
            mensaje += "Escriba -1 si desea concluir la partida.\n";
            int apuesta = Interfaz.getInt(mensaje, "Introduzca un valor válido.", -1, numRonda);
            // Para no tener un salto de línea de más.
            Interfaz.ignoreLine();
            // Si se desea terminar la partida
            if (apuesta == -1) {
                historial.add("\t" + jugador.getNombre() + " terminó la partida.\n");
                System.out.println(jugador.getNombre() + " terminó la partida.\n");

                return false;
            }
            jugador.setApuesta(apuesta);
            historial.add("\t" + jugador.getNombre() + " apostó " + apuesta + ".\n");
            System.out.println(jugador.getNombre() + " apostó " + apuesta + ".\n");
        }
        return true;
    }

    /**
     * Reparte las cartas a los usuarios.
     */
    private void repartirCartas() {
        Iterator<Jugador> it = jugadores.iterator();
        // Contador para escoger al barajeador.
        int contador = 0;

        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (contador == (numRonda % jugadores.size())) {
                barajeador = jugador;
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
