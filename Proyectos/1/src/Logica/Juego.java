package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

public class Juego {

    private Baraja baraja;
    private int totalRondas;
    private Ronda ronda;
    private int numJugadores;
    private Lista<Jugador> jugadores;
    private Lista<String> historial;

    public Juego() {
        baraja = new Baraja();
        totalRondas = 0;
        numJugadores = 0;
        jugadores = new Lista<>();
        historial = new Lista<>();
    }

    public void agregaJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
        numJugadores++;
    }

    public void jugar() {
        boolean jugar = true;
        int totalRondas = 60 / numJugadores;
        for (int i = 1; i <= totalRondas; i++) {
            ronda = new Ronda(baraja, jugadores, i);
            jugar = ronda.jugar();
            historial.append(ronda.getHistorial());

            if (!jugar) {
                Lista<Jugador> ganadores = ronda.getGanadores();
                if (ganadores.size() == 1) {
                    System.out.println("El jugador que iba ganando es:");
                } else{
                    System.out.println("Los jugadores que iban ganando son:");
                }
                imprimirGanadores(ganadores);
                System.out.println("Historial de la partida:");
                imprimirHistorial();
                return;
            }
            if (i == totalRondas) {
                Lista<Jugador> ganadores = ronda.getGanadores();
                if (ganadores.size() == 1) {
                    System.out.println("El ganador es:");
                } else{
                    System.out.println("Hubo un empate. Los ganadores son:");
                }
                imprimirGanadores(ganadores);
            }
        }
    }

    private void imprimirGanadores(Lista<Jugador> ganadores) {
        Iterator<Jugador> it = ganadores.iterator();
        while (it.hasNext()) {
            Jugador jugador = it.next();
            System.out.println(jugador.getNombre() + " con un puntaje de: " + jugador.getPuntaje());
        }
    }
    public void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}

