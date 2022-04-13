package edd.src.Elementos;

import edd.src.Estructuras.*;

import java.lang.Math;
import java.util.Iterator;

/**
 * Clase que representa a cada jugador que participe en Wizard.
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */
public class Jugador {

    /**
     * Nombre del jugador.
     */

    private String nombre;
    /**
     * Lista de cartas con la mano del jugador.
     */

    private Lista<Carta> mano;

    /**
     * Las apuestas, trucos ganados y puntaje actual del jugador.
     */
    private int apuesta, trucosGanados, puntaje;

    /**
     * Constructor de jugador.
     * @param nombre Nombre del jugador.
     */
    public Jugador(String nombre) {
	this.nombre = nombre;
	this.mano = new Lista<>();
	this.apuesta = 0;
	this.trucosGanados = 0;
	this.puntaje = 0;
    }

    /**
     * Agrega una carta a la mano del jugador.
     * @param carta Carta a agregar.
     */
    public void recibeCarta (Carta carta) {
        mano.add(carta);
    }
    /**
     * Devuelve el nombre del jugador.
     * @return String Nombre del jugador.
     */
    public String getNombre(){
	return this.nombre;
    }

    /**
     * Devuelve la mano del jugador.
     * @return Lista<Carta> Mano del jugador.
     */
    public Lista<Carta> getMano(){
        return this.mano;
    }

    /**
     * Elimina de la mano la carta seleccionada.
     * @param carta Carta seleccionada a quitar.
     */
    public void tomarCarta(Carta carta) {
        mano.delete(carta);
    }

    /**
     * Devuelve la apuesta del jugador.
     * @return int Apuesta del jugador
     */
    public int getApuesta(){
	return this.apuesta;
    }

    /**
     * Cambia la apuesta del jugador por la apuesta recibida.
     * @param apuesta Nueva apuesta del jugador.
     */
    public void setApuesta(int apuesta){
	this.apuesta = apuesta;
    }

    /**
     * Devuelve los trucos ganados por el jugador.
     * @return int Trucos ganados por el jugador.
     */
    public int getTrucosGanados(){
	return this.trucosGanados;
    }

    /**
     * Cambia los trucos ganados del jugador por los recibidos.
     * @param trucos Nuevos trucos ganados a cambiar.
     */
    public void setTrucosGanados(int trucos){
	this.trucosGanados = trucos;
    }

    /**
     * Le suma un truco ganado a jugador.
     */
    public void incrementarTrucosGanados(){
	this.trucosGanados = this.trucosGanados + 1;
    }

    /**
     * Calcula el puntaje del jugador a partir de su apuesta y trucos ganados.
     */
    public void calcularPuntaje(){
	if(this.apuesta == this.trucosGanados)
	    this.puntaje = this.puntaje + (20 + 10 * (this.trucosGanados));
	else{
	    this.puntaje = this.puntaje + (-10 * Math.abs(this.apuesta - this.trucosGanados));
	}
    }

    /**
     * Devuelve el puntaje del jugador.
     * @return int Puntaje del jugador.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Devuelve en una cadena los datos del jugador.
     * @return String Datos del jugador.
     */
    @Override
    public String toString(){
        return "Nombre: " + this.nombre + "\n Mano: " + this.mano.toString() + "\n Trucos ganados:" + this.trucosGanados + "\n Puntaje: " + this.puntaje ;
    }

    /**
     * Determina si el jugador es igual a otro objeto.
     * @param obj Objeto a comprobar si es igual.
     * @return boolean Si son o no iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Jugador)) {
            return false;
        }
        Jugador otro = (Jugador) obj;
        if (!otro.getNombre().equals(getNombre())) {
            return false;
        }
        if (!otro.getMano().equals(getMano())) {
            return false;
        }

        if (!(otro.getApuesta() == getApuesta())) {
            return false;
        }
        if (!(otro.getTrucosGanados() == getTrucosGanados())) {
            return false;
        }
        if (!(otro.getPuntaje() == getPuntaje())) {
            return false;
        }

        return true;
    }

    /**
     * Devuelve en un String la mano del jugador.
     * @return String Mano del jugador.
     */
    public String getManoToString() {
        String aRegresar = "";
        Iterator<Carta> it = mano.iterator();
        while (it.hasNext()) {
            Carta carta = it.next();
            aRegresar += carta.toString() + "\n";
        }
        return aRegresar;
    }

}
