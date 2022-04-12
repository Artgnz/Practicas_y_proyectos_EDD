package edd.src.Elementos;

public class Carta {

    private int numero;
    private String palo;
    private Jugador jugadoPor;

    public Carta(int numero, String palo){
	this.numero = numero;
	this.palo = palo;
	this.jugadoPor = null;
    }

    public int getNumero(){
	return this.numero;
    }

    public String getPalo(){
	return this.palo;
    }

    public void setJugadoPor(Jugador jugadoPor){
	this.jugadoPor = jugadoPor;
    }

    public Jugador getJugadoPor(){
	return this.jugadoPor;
    }

    @Override
    public String toString(){
        if(this.numero == 0) {
            return "Tipo: Bufon.";
        }
        else if (this.numero == 14) {
            return "Tipo: Wizard.";
        }
        else {
            return "Numero: " + this.numero + "\n Palo: " + this.palo;
        }
    }
}
