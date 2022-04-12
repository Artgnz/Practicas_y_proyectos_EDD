package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

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
        historial = new Lista<>();
    }

    public void agregaJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
        numJugadores++;
    }

    public void jugar() {
        int totalRondas = 60 / numJugadores;
        for (int i = 0; i < totalRondas; i++) {
            rondaActual++;
            jugarRonda();
        }
    }

    public void jugarRonda() {
        historial.add("Inicio de ronda: " + rondaActual);

        baraja.barajear();
        Iterator<Jugador> it = jugadores.iterator();
        int contador = 1;
        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (contador == (rondaActual % numJugadores) + 1) {
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
            if (cartaDeTriunfo.getNumero() <= 14) {
                String mensaje = "Escoge el palo del triunfo, " + barajeador.getNombre() + "\n";
                mensaje += "Posibles opciones: \n";
                mensaje += "\t rojo\n";
                mensaje += "\t amarillo\n";
                mensaje += "\t verde\n";
                mensaje += "\t azul\n";
                String [] opciones = {"rojo", "amarillo", "verde", "azul"};
                String opcion = Interfaz.getString(mensaje, "Favor de introducir una opción válida", opciones);

                paloDeTriunfo = opcion;
            } else if (cartaDeTriunfo.getNumero() == 0) {
                paloDeTriunfo = null;
            } else {
                paloDeTriunfo = cartaDeTriunfo.getPalo();
            }
        } else {
            paloDeTriunfo = null;
        }

        if (paloDeTriunfo == null) {
            historial.add("Ronda sin  palo de triunfo.");
        } else {
            historial.add("Palo de triunfo: " + paloDeTriunfo);
        }

        it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            String mensaje = jugador.getNombre() + " introduzca su apuesta (0-" + rondaActual + "): ";
            int apuesta = Interfaz.getInt(mensaje, "Introduzca un valor válido.", 0, rondaActual);
            Interfaz.ignoreLine();
            jugador.setApuesta(apuesta);
            historial.add(jugador.getNombre() + " apostó " + apuesta + ".");
        }

        for (int i = 0; i < rondaActual; i++) {
            Truco truco = new Truco();
        }
        barajeador = null;
        historial.add("Fin de ronda: " + rondaActual);
    }

    public void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

