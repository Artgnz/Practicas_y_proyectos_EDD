package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

/**
 *Clase que representa el desarrollo de un juego de Wizard.
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */
public class Juego {

    /**
     * Baraja con la 60 cartas del juego.
     */
    private Baraja baraja;

    /**
     *Cantidad de rondas a jugar.
    */
    private int totalRondas;

    /**
     *Ronda actual.
     */
    private Ronda ronda;

    /**
     *Cantidad de jugadores a participar.
     */
    private int numJugadores;

    /**
     * Lista con los jugadores a participar.
     */
    private Lista<Jugador> jugadores;

    /**
     *Historial del juego.
     */
    private Lista<String> historial;

    /**
     *Constructor sin parametros de Juego.
     */
    public Juego() {
        baraja = new Baraja();
        totalRondas = 0;
        numJugadores = 0;
        jugadores = new Lista<>();
        historial = new Lista<>();
    }

    /**
     *Agrega jugadores al juego.
     *@param nombre Nombre del jugador a ingresar.
     */
    public void agregaJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
        numJugadores++;
    }

    /**
     *Comienza un juego .
     */
    public void jugar() {
        boolean jugar = true;
        int totalRondas = 60 / numJugadores; //Determina la cantidad de rondas a partir del numero de jugadores.
        for (int i = 1; i <= totalRondas; i++) {

            ronda = new Ronda(baraja, jugadores, i, historial);//Crea una nueva ronda.
            jugar = ronda.jugar();//Juega la ronda.

            if (!jugar) {//Si se interrumpe la ronda, se obtiene el/los ganador(es) hasta ese momento.
                Lista<Jugador> ganadores = ronda.getGanadores();
                if (ganadores.size() == 1) {
                    System.out.println("El jugador que iba ganando es:");
                    historial.add("El jugador que iba ganando es:\n");
                } else{
                    System.out.println("Los jugadores que iban ganando son:");
                    historial.add("Los jugadores que iban ganando son:\n");
                }
                imprimirGanadores(ganadores);
                System.out.println("\nHistorial de la partida:");
                imprimirHistorial();
                imprimirGanadores(ganadores);
                return;
            }
            if (i == totalRondas) { //Tras jugar todas las rondas se obtienen el/los ganador(es) finales.
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

    /**
     *Imprime a los ganadores del juego.
     *@param ganadores Lista de ganadores a imprimir.
     */
    private void imprimirGanadores(Lista<Jugador> ganadores) {
        Iterator<Jugador> it = ganadores.iterator();
        while (it.hasNext()) {
            Jugador jugador = it.next();
            System.out.println(jugador.getNombre() + " con un puntaje de: " + jugador.getPuntaje());
        }
    }

    /**
     Imprime el historial hasta el momento del juego.
     */
    public void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}

