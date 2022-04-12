package edd.src.Elementos;

import edd.src.Estructuras.*;
import java.lang.Math.*;

public class Jugador {

    private String nombre;
    private Lista<Carta> mano;
    private int apuesta, trucosGanados, puntaje;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.mano = new Lista<>();
      	this.apuesta = 0;
      	this.trucosGanados = 0;
      	this.puntaje = 0;
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
    

    public int getApuesta(){
	return this.apuesta;
    }

    public void setApuesta(int apuesta){
	this.apuesta = apuesta;
    }

    public int getTrucosGanados(){
	return this.trucosGanados;
    }

    public void setTrucosGanados(int trucos){
	this.trucosGanados = trucos;
    }

    public void calcularPuntaje(){
	if(this.apuesta == this.trucosGanados)
	    this.puntaje = this.puntaje + (20 + 10(this.trucosGanados));
	else{
	    this.puntaje = this.puntaje + (-10 * Math.abs(this.apuesta - this.trucosGanados));
	}
    }

    @Override
    public String toString(){
	   return "Nombre: " + this.nombre + "\n Mano: " + this.mano.toString() + "\n Trucos ganados:" + this.trucosGanados + "\n Puntaje: " + this.puntaje ;
    }
}
