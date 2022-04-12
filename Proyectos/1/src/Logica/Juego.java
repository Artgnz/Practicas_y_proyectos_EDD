package edd.src.Logica;

import java.util.Scanner;
import java.util.Iterator;
import java.util.Scanner;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;

public class Juego {

    private Baraja baraja;
    private int rondaActual;
    private int totalRondas;
    private Ronda ronda;
    private int numJugadores;
    private Lista<Jugador> jugadores;
    private Jugador barajeador;
    private Lista<String> historial;

    public Juego() {
        baraja = new Baraja();
        rondaActual = 0;
        totalRondas = 0;
        numJugadores = 0;
        jugadores = new Lista<>();
        ronda = new Ronda();
    }

    public void agregaJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
        numJugadores++;
    }

    public void jugar() {
        int totalRondas = 60 / numJugadores;
        for (int i = 0; i < 1; i++) { // TODO FOR MULTIPLE ROUNDS
            rondaActual++;
            jugarRonda();
        }
    }

    public void jugarRonda() {
        baraja.barajear();
        Iterator<Jugador> it = jugadores.iterator();
        int contador = 0;
        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (contador == (rondaActual % numJugadores)) {
                barajeador = jugador;
            }
            for (int j = 0; j < rondaActual; j++) {
                jugador.recibeCarta(baraja.tomarCarta());
            }
            contador++;
        }

        String paloDeTriunfo;
        if (baraja.tieneCartas()) {
            Carta cartaDeTriunfo = baraja.tomarCarta();
            if (cartaDeTriunfo.getNumero() == 14) {

                Scanner scn = new Scanner(System.in);
                String opcion = "";
                while (true) {
                    System.out.println("Escoge el palo del triunfo, " + barajeador.getNombre());
                    System.out.println("Posibles opciones: ");
                    System.out.println("\t rojo");
                    System.out.println("\t amarillo");
                    System.out.println("\t verde");
                    System.out.println("\t azul");
                    opcion = scn.nextLine();
                    if (opcion.equals("rojo") || opcion.equals("amarillo") || opcion.equals("verde")
                        || opcion.equals("azul")) {
                        paloDeTriunfo = opcion;
                        break;
                    }
                }
            } else if (cartaDeTriunfo.getNumero() == 0) {
                paloDeTriunfo = null;
            } else {
                paloDeTriunfo = cartaDeTriunfo.getPalo();
            }
        } else {
            paloDeTriunfo = null;
        }

        if (paloDeTriunfo == null) {
            historial.add("Ronda: " + rondaActual + " sin palo de triunfo.");
        } else {
            historial.add("Ronda: " + rondaActual + " palo de Triunfo " + paloDeTriunfo);
        }
        it = jugadores.iterator();
        while (it.hasNext()) {
            Jugador jugador = it.next();
            // TODO QUE APUESTEN
            historial.add("Jugador: " + jugador.getNombre() + " apostó " + " su vida.");
        }
        barajeador = null;
        for (int i = 0; i < rondaActual; i++) {
            Truco truco = new Truco();
        }
    }

    public void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

