package edd.src.Elementos;

import edd.src.Estructuras.*;

public class Jugador {
    private String nombre;
    private Lista<Carta> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = mano = new Lista<>();
    }

    public void recibeCarta (Carta carta) {
        mano.add(carta);
    }
    
    public String getNombre(){
	return this.nombre;
    }

    public Lista<Carta> getMano(){
        return this.mano;
    }

    public Carta tomarCarta() {
        /* Escoga su carta
         * 1. Mago
         * 2. Mago
         * 3. rojo 6
         */
        return null;
    }
    
    public void setEstado(){

    }

    @Override
    public String toString(){
        return "Nombre: " + this.nombre + "\n Mano: " + this.mano.toString() +"\n Barajea : No. \n";
    }
}
