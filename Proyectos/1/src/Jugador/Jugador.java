package edd.src.Jugador;

import Clases.Lista;

public class Jugador {
    private String nombre;
    private Lista<Carta> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new Lista<>();
    }
}
