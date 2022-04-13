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

    public Jugador getJugadoPor(){
	return this.jugadoPor;
    }

    public void setJugadoPor(Jugador jugadoPor){
	this.jugadoPor = jugadoPor;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
     G       return false;
        }
        if (!(obj instanceof Carta)) {
            return false;
        }
        Carta carta = (Carta) obj;
        if (carta.getNumero() != getNumero()) {
            return false;
        } 
        if (!carta.getPalo().equals(getPalo())) {
            return false;
        }
        if (!carta.getJugadoPor().equals(getJugadoPor())) {
            return false;
        }
        return true;
    }
}
