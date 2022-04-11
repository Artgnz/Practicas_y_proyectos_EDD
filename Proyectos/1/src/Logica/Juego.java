package edd.src.Logica;

import java.util.Scanner;
import java.util.Random;

import edd.src.Jugador.*;
import edd.src.Estructuras.*;

/*
  Variables:
  Lista<Carta> baraja HECHO
  totalRondas
  Ronda ronda
  Lista<Jugador> jugadores
  Métodos:
  juegaRonda()  
  rondaActual
 */
public class Juego {
    private Lista<Carta> baraja;
    private int ronda;
    private int totalRondas;
    private int numJugadores;
    private Lista<Jugador> jugadores;

    public Juego() {
        baraja = new Lista<>();
        ronda = 1;
        totalRondas = 1;
        numJugadores = 0;
        jugadores = new Lista<>();
    }

    public void agregaJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
        numJugadores++;
    }

    public void jugar() {
        int totalRondas = 60 / numJugadores;
        for (int i = 0; i < totalRondas; i++) {
            barajearMazo();
        }
    }

    public void barajearMazo() {
        // Arreglo temporal para barajear cartas.
        Carta[] tmpBaraja = new Carta[baraja.size()];

        int indice = 0;
        while (!baraja.isEmpty()) {
            tmpBaraja[indice] = baraja.pop();
            indice++;
        }

        Random rand = new Random();

        for (int i = baraja.size() - 1; i >= 0; i--) {
            int paraCambiar = rand.nextInt(tmpBaraja.length);
            Carta tmp = tmpBaraja[paraCambiar];
            tmpBaraja[paraCambiar] = tmpBaraja[i];
            tmpBaraja[i] = tmp;
        }

        for (int i = 0; i < tmpBaraja.length; i++) {
            baraja.add(tmpBaraja[i]);
        }
    }
}

