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

    public void tomarCarta(Carta carta) {
        Iterator it = mano.iterador();
        while (it.hasNext()) {
            Carta tmp = it.next();
            if (tmp.equals(carta)) {
                mano.delete(carta);
            }
        }
    }
    
    public void setEstado(){

    }

    @Override
    public String toString(){
        return "Nombre: " + this.nombre + "\n Mano: " + this.mano.toString() +"\n Barajea : No. \n";
    }
}
