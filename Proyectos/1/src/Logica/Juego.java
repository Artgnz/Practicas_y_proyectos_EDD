package edd.src.Logica;

import edd.src.Jugador.*;
import java.util.Scanner;
import edd.src.Estructuras.*;
/*
  Variables:
  Lista<Carta> baraja
  rondaActual
  totalRondas
  Ronda ronda
  Lista<Jugador> jugadores
  Métodos:
  juegaRonda()  
 */
public class Juego {
    private static Lista<Carta> baraja;
    private static int ronda;
    private static int totalRondas;
    private static int numJugadores;
    private static Lista<Jugador> jugadores;

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
}
