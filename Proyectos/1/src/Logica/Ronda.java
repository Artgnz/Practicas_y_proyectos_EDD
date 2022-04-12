package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

public class Ronda {
    private Lista<String> historial;
    private Baraja baraja;
    private Lista<Jugador> jugadores;
    private Jugador barajeador;

    public Ronda(Baraja baraja, Lista<Jugador> jugadores) {
        historial = new Lista<>();
        this.baraja = baraja;
        this.jugadores = jugadores;
    }

    public boolean jugar(int rondaActual) {
        historial.add("\nInicio de ronda: " + rondaActual + "\n");

        baraja.barajear();
        Iterator<Jugador> it = jugadores.iterator();
        int contador = 1;
        while (it.hasNext()) {
            Jugador jugador = it.next();
            if (contador == (rondaActual % jugadores.size())) {
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
                String mensaje = "Escoge el palo del triunfo, " + barajeador.getNombre() + "\n";
                mensaje += "Posibles opciones: \n";
                mensaje += "\t rojo\n";
                mensaje += "\t amarillo\n";
                mensaje += "\t verde\n";
                mensaje += "\t azul\n";
                mensaje += "Escriba terminar si desea concluir la partida.";
                String [] opciones = {"rojo", "amarillo", "verde", "azul", "terminar"};
                String opcion = Interfaz.getString(mensaje, "Favor de introducir una opción válida", opciones);
                if (opcion.equals("terminar")) {
                    return false;
                }
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
            historial.add("\tRonda sin  palo de triunfo.\n");
        } else {
            historial.add("\tPalo de triunfo: " + paloDeTriunfo + ".\n");
        }

        it = jugadores.iterator();

        while (it.hasNext()) {
            Jugador jugador = it.next();
            String mensaje = jugador.getNombre() + " introduzca su apuesta (0-" + rondaActual + ")\n";
            mensaje += "Escriba -1 si desea concluir la partida.";
            int apuesta = Interfaz.getInt(mensaje, "Introduzca un valor válido.", -1, rondaActual);
            Interfaz.ignoreLine();
            if (apuesta == -1) {
                return false;
            }
            jugador.setApuesta(apuesta);
            historial.add("\t" + jugador.getNombre() + " apostó " + apuesta + ".\n");
        }

        for (int i = 0; i < rondaActual; i++) {
            Truco truco = new Truco();
        }
        barajeador = null;
        historial.add("Fin de ronda: " + rondaActual + "\n");
        return true;
    }

    public Lista<String> getHistorial() {
        return historial;
    }
}
