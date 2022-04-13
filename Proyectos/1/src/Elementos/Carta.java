package edd.src.Elementos;

/**
 * Clase que representa a una carta del juego Wizard.
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */
public class Carta {

    /**
     * Número de la carta.
     */
    private int numero;
    /**
     * Color del palo.
     */
    private String palo;
    /**
     * Último jugador que la jugó.
     */
    private Jugador jugadoPor;

    /**
     * Constructor de la carta.
     * @param numero Número de la carta
     * @param palo Palo de la carta
     */
    public Carta(int numero, String palo){
	this.numero = numero;
	this.palo = palo;
	this.jugadoPor = null;
    }

    /**
     * Devuelve el número de la carta.
     * @return int Número de la carta.
     */
    public int getNumero(){
	return numero;
    }

    /**
     * Devuelve el palo de la carta.
     * @return String Palo de la carta.
     */
    public String getPalo(){
	return this.palo;
    }

    /**
     * Devuelve el jugador de la carta.
     * @return Jugador Jugador de la carta.
     */
    public Jugador getJugadoPor(){
	return this.jugadoPor;
    }
    /**
     * Asigna el jugador de la carta.
     * @param jugadorPor Jugador de la carta.
     */
    public void setJugadoPor(Jugador jugadoPor){
	this.jugadoPor = jugadoPor;
    }

    /**
     * Devuelve una representación en cadena de la carta.
     * @return String Representación en cadena de la carta.
     */
    @Override
    public String toString(){

        if(this.numero == 0) {
            return "Tipo: Bufon.";
        }
        else if (this.numero == 14) {
            return "Tipo: Wizard.";
        }
        else {
            return "Numero: " + this.numero + "\nPalo: " + this.palo;
        }
    }

    /**
     * Compara la carta con otro objeto y dice si son los mismos.
     * @param obj Objeto con el que se compara.
     * @return boolean Si son el mismo o no.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Carta)) {
            return false;
        }
        Carta otro = (Carta) obj;
        if (otro.getNumero() != getNumero()) {
            return false;
        }
        if (!otro.getPalo().equals(getPalo())) {
            return false;
        }
        return true;
    }
}
