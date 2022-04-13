package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

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

	public Lista<Carta> getCartasMesa() {
	    return mesa;
	}
    }

    private Lista<Jugador> jugadores;
    private String paloLider, paloTriunfo;
    private Carta cartaGanadora;
    private Jugador jugadorGanador;
    private Mesa mesa;
    private Jugador primerJugador;
    private Lista<String> historial;

    public Truco(Lista<Jugador> jugadores, Jugador primerJugador, String paloDeTriunfo) {
	this.primerJugador = primerJugador;
	this.jugadores = jugadores;
	this.paloTriunfo = paloDeTriunfo;
	this.paloLider = null;
	this.cartaGanadora = null;
	this.jugadorGanador = null;
	this.mesa = new Mesa();
	historial = new Lista<>();
    }

    public void jugar() {
	pedirCartas();
	calcularCartaGanadora();
	calcularGanador();
	System.out.println("Truco ganado por: " + getGanador().getNombre());
	historial.add("Truco ganado por: " + getGanador().getNombre() + "\n");
	jugadorGanador.incrementarTrucosGanados();
    }

    public void calcularGanador() {
	jugadorGanador = cartaGanadora.getJugadoPor();
    }
    public Jugador getGanador() {
	return jugadorGanador;
    }

    public void pedirCartas() {
	Iterator<Jugador> it = jugadores.iterator();
	int contador = 0;
	while (it.hasNext()) {
	    Jugador jugador = it.next();
	    if (jugador.equals(primerJugador)) {
		while (contador < jugadores.size()) {
		    while (it.hasNext() && contador < jugadores.size()) {
			jugarCarta(jugador);
			jugador = it.next();
			contador++;
		    }
		    it = jugadores.iterator();
		}
	    }
	}
    }

    private void jugarCarta(Jugador jugador) {

	Lista<Carta> manoFiltrada = filrarMano(jugador.getMano());
	System.out.println("----------");

	String mensaje = "Es el turno de " + jugador.getNombre() + ", escoja una carta:\n";
	int contador = 1;
	Iterator<Carta> it = manoFiltrada.iterator();
	while (it.hasNext()) {
	    Carta carta = it.next();
	    mensaje += contador + ".\n" + carta.toString() + "\n";
	    contador++;
	}
	int opcion = Interfaz.getInt(mensaje, "Ingrese una opción válida", 1, manoFiltrada.size());
	Interfaz.ignoreLine();
	contador = 1;
	it = manoFiltrada.iterator();
	while (it.hasNext()) {
	    Carta carta = it.next();
	    if (contador == opcion) {
		if (paloLider == null && !carta.getPalo().equals("especial")) {
		    paloLider = carta.getPalo();
		}
		historial.add(jugador.getNombre() + " jugó:\n" + carta.toString() + "\n");
		carta.setJugadoPor(jugador);
		mesa.meterCarta(carta);
		jugador.tomarCarta(carta);
		break;
	    }
	    contador++;
	}
	System.out.println("----------");

    }

    private boolean contienePalo(Lista<Carta> mano, String palo){
	Iterator<Carta> it = mano.iterator();
	while(it.hasNext()){
	    String paloCarta = it.next().getPalo();
	    if(paloCarta.equals(palo)){
		return true;
	    }
	}
	return false;   
    }

    private Lista<Carta> filrarMano(Lista<Carta> mano) {
	if (paloLider == null || !contienePalo(mano, paloLider)) {
	    return mano;
	}

	Lista<Carta> manoFiltrada = new Lista<>();
	Iterator<Carta> it = mano.iterator();
	while (it.hasNext()) {
	    Carta carta = it.next();
	    if (carta.getPalo().equals(paloLider) || carta.getPalo().equals("especial")) {
		manoFiltrada.add(carta);
	    }
	}
	return manoFiltrada;
    }

    public void calcularCartaGanadora(){
	if(mesa.contieneNumero(14))
	    cartaGanadora = mesa.obtenerPrimeraNumerada(14);
	else{
	    if(mesa.contienePalo(paloTriunfo))
		cartaGanadora = mesa.obtenerCartaMayor(paloTriunfo);
	    else{
		if(mesa.contienePalo(paloLider))
		    cartaGanadora = mesa.obtenerCartaMayor(paloLider);
		else{
		    cartaGanadora = mesa.obtenerPrimeraNumerada(0);
		}
	    }
	}
    }

    public Lista<Carta> getCartasUsadas() {
	return mesa.getCartasMesa();
    }
    public Lista<String> getHistorial() {
	return historial;
    }
}
