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

    public Truco(Lista<Jugador> jugadores, Jugador primerJugador, String paloDeTriunfo, Lista<String> historial) {
	this.primerJugador = primerJugador;
	this.jugadores = jugadores;
	this.paloTriunfo = paloDeTriunfo;
	this.paloLider = null;
	this.cartaGanadora = null;
	this.jugadorGanador = null;
	this.mesa = new Mesa();
	this.historial = historial;
    }

    public boolean jugar() {
	if (!pedirCartas()) {
	    return false;
	}
	calcularCartaGanadora();
	calcularGanador();
	System.out.println("Truco ganado por: " + getGanador().getNombre());
	System.out.println();
	historial.add("Truco ganado por: " + getGanador().getNombre() + "\n\n");
	jugadorGanador.incrementarTrucosGanados();
	return true;
    }

    public void calcularGanador() {
	jugadorGanador = cartaGanadora.getJugadoPor();
    }
    public Jugador getGanador() {
	return jugadorGanador;
    }

    public boolean pedirCartas() {
	Iterator<Jugador> it = jugadores.iterator();
	int contador = 0;
	while (it.hasNext()) {
	    Jugador jugador = it.next();
	    if (jugador.equals(primerJugador)) {
		while (contador < jugadores.size()) {
		    while (it.hasNext() && contador < jugadores.size()) {
			if (!jugarCarta(jugador)) {
			    return false;
			}
			jugador = it.next();
			contador++;
		    }
		    it = jugadores.iterator();
		}
		return true;
	    }
	}
	return true;
    }

    private boolean jugarCarta(Jugador jugador) {

	Lista<Carta> manoFiltrada = jugador.getMano();
	String mensaje = "";
	if (paloTriunfo == null) {
	    mensaje += "No hay palo de triunfo.\n";
	} else {
	    mensaje += "Palo de triunfo " + paloTriunfo + ".\n";
	}
	if (paloLider == null) {
	    mensaje += "No hay palo lider.\n\n";
	} else {
	    mensaje += "Palo lider " + paloLider + ".\n\n";
	}
	mensaje += "Es el turno de " + jugador.getNombre() + ", esta es su mano:\n";
	int contador = 1;
	Iterator<Carta> it = manoFiltrada.iterator();
	while (it.hasNext()) {
	    Carta carta = it.next();
	    mensaje += contador + ".\n" + carta.toString() + "\n";
	    contador++;
	}
	mensaje += "\nEscoja el índice de la carta que desea usar.\n";
	mensaje += "Escriba -1 si desea concluir la partida.\n";
	mensaje += "Escriba -2 si desea ver el historial.\n";

	while (true) {
	    int opcion = Interfaz.getInt(mensaje, "Ingrese una opción válida.", -2, manoFiltrada.size());
	    if (opcion == -1) {
		System.out.println(jugador.getNombre() + " terminó la partida.\n");
		historial.add(jugador.getNombre() + " terminó la partida.\n\n");
		return false;
	    } else if (opcion == -2) {
		System.out.println("Historial de la partida:");
		imprimirHistorial();
		System.out.println();		
	    } else {
		Interfaz.ignoreLine();
		Carta carta = tomarCartaIndice(manoFiltrada, opcion);

		if (tomarCartaValida(manoFiltrada, carta)) {
		    if (paloLider == null && !carta.getPalo().equals("especial")) {
			paloLider = carta.getPalo();
		    }
		    historial.add(jugador.getNombre() + " jugó:\n" + carta.toString() + "\n\n");
		    System.out.println(jugador.getNombre() + " jugó:\n" + carta.toString() + "\n");
		    carta.setJugadoPor(jugador);
		    mesa.meterCarta(carta);
		    jugador.tomarCarta(carta);
		    break;
		}
	    }
	}
	System.out.println("");
	return true;
    }

    private Carta tomarCartaIndice(Lista<Carta> mano, int indice) {
	Iterator<Carta> it = mano.iterator();
	int contador = 1;
	while(it.hasNext()){
	    Carta carta = it.next();
	    if (contador == indice) {
		return carta;
	    }
	    contador++;
	}

	return null;
    }
    private boolean tomarCartaValida(Lista<Carta> mano, Carta carta) {
	if (carta == null) {
	    return false;
	}
	if (paloLider == null) {
	    return true;
	}
	if (!contienePalo(mano, paloLider)) {
	    return true;
	}
	if (carta.getPalo().equals(paloLider)) {
	    return true;
	}
	if (carta.getPalo().equals("especial")) {
	    return true;
	}
	return false;
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
    public void imprimirHistorial() {
        Iterator<String> it = historial.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}
