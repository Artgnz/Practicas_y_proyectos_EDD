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
                System.out.println("Historial de la partida:");
                imprimirHistorial();
                return;
            }
        }
    }

    public void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}

