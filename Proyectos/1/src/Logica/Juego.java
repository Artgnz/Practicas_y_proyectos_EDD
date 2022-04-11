package edd.src.Logica;

import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
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
        ronda = 0;
        totalRondas = 0;
        numJugadores = 0;
        jugadores = new Lista<>();
        inicializarBaraja();
    }

    public void inicializarBaraja() {
        String[] palos = {"rojo", "amarillo", "verde", "azul"};

        for (int i = 0; i < palos.length; i++) {
            for (int j = 1; j <= 13; j++) {
                baraja.add(new Carta(i, palos[i]));
            }
        }
        for (int i = 0; i < 4; i++) {
            baraja.add(new Carta(0, "especial"));
        }
        for (int i = 0; i < 4; i++) {
            baraja.add(new Carta(14, "especial"));
        }
    }
    public void agregaJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
        numJugadores++;
    }

    public void jugar() {
        int totalRondas = 60 / numJugadores;
        for (int i = 0; i < 1; i++) {
            ronda++;
            jugarRonda();
        }
    }

    public void jugarRonda() {
        barajearMazo();
        Iterator<Jugador> it = jugadores.iterator();
        while (it.hasNext()) {
            Jugador jugador = it.next();
            for (int j = 0; j < ronda; j++) {
                jugador.recibeCarta(baraja.pop());
            }
            
        }
        while (it.hasNext()) {
            Jugador jugador = it.next();
            baraja.append(jugador.devuelveCartas());
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

