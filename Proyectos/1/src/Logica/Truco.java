package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;

public class Truco {

    private class Mesa{

        public Lista<Carta> mesa;

	public Mesa(){
	    this.mesa = new Lista<>();
	}

	public void meterCarta(Carta carta){
	    this.mesa.add(carta);
	}

	public boolean contienePalo(String palo){
	    Iterator<Carta> it = this.mesa.iterator();
	    while(it.hasNext()){
		String paloCarta = it.next().getPalo();
		if(paloCarta.equals(palo)){
		    return true;
		}
	    }
	    return false;   
	}

	public boolean contieneNumero(int numero){
	    Iterator<Carta> it = this.mesa.iterator();
	    while(it.hasNext()){
		int numeroCarta = it.next().getNumero();
		if(numeroCarta == numero){
		    return true;
		}
	    }
	    return false;   
	}

	public Carta obtenerCartaMayor(String palo){
	    Iterator<Carta> it = this.mesa.iterator();
	    Carta cartaMayor = this.obtenerPrimerPalo(palo);
	    while(it.hasNext()){
		Carta aux = it.next();
		if(aux.getPalo().equals(palo) && aux.getNumero()>= cartaMayor.getNumero())
		    cartaMayor = aux;
	    }
	    return cartaMayor;
	}

	public Carta obtenerPrimeraNumerada(int numero){
	    Iterator<Carta> it = this.mesa.iterator();
	    while(it.hasNext()){
		Carta primeraNumerada = it.next();
		if(primeraNumerada.getNumero() == numero)
		    return primeraNumerada;
	    }
	    return null;
	}

	public Carta obtenerPrimerPalo(String palo){
	    Iterator<Carta> it = this.mesa.iterator();
	    while(it.hasNext()){
		Carta primerPalo = it.next();
		if(primerPalo.getPalo().equals(palo))
		    return primerPalo;
	    }
	    return null;
	}
	
    }

    private Lista<Jugador> jugadores;
    private String paloLider, paloTriunfo;
    private Carta cartaGanadora;
    private Jugador jugadorGanador;
    private Mesa mesa;
    
    /* lista de cartas en mesa
     * palo lider
     * carta ganadora
     * Lista de jugadores
     */
    public Truco() {
	this.jugadores = null;
	this.paloLider = null;
	this.cartaGanadora = null;
	this.jugadorGanador = null;
	this.mesa = new Mesa();
    }

    public void jugarTruco() {
        // Iterator it = jugadores.iterador();
        // while(it.hasNext()) {
        //     Jugador jugador = it.next();
        //     Lista<Carta> mano = jugador.getMano();
        //     Iterator iterador = mano.iterador();
        //     while (iterador.hasNext()) {
        //         Carta carta = iterador.next();

        //     }
        // }
	this.calcularCartaGanadora();
	this.jugadorGanador = this.cartaGanadora.getJugadoPor();
	this.jugadorGanador.incrementarTrucosGanados();
    }

    public void calcularCartaGanadora(){
	
	if(this.mesa.contieneNumero(14))
	    this.cartaGanadora = this.mesa.obtenerPrimeraNumerada(14);
	else{
	    if(this.mesa.contienePalo(this.paloTriunfo))
		this.cartaGanadora = this.mesa.obtenerCartaMayor(this.paloTriunfo);
	    else{
		if(this.mesa.contienePalo(this.paloLider))
		this.cartaGanadora = this.mesa.obtenerCartaMayor(this.paloLider);
		else{
		    this.cartaGanadora = this.mesa.obtenerPrimeraNumerada(0);
		}
	    }
	}
    }
    
    /*
    * Arreglo de n elementos cada uno con una carta del mazo
     * 1.. n escoge carta
     * 
     */
}
