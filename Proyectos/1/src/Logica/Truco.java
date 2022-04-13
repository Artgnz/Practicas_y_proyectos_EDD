package edd.src.Logica;

import java.util.Iterator;

import edd.src.Estructuras.*;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

/**
  * Clase que representa un truco del juego Wizard.
  * @author Arturo González Peñaloza
  * @author Arsenio Raudry Rico
 */
public class Truco {
    /**
      *Clase que representa la mesa en la que se juegan las cartas durante un truco.
      * @author Arturo González Peñaloza
      * @author Arsenio Raudry Rico
     */
    private class Mesa{

	/**
	 *Lista de cartas jugadas en la mesa.
	 */
        public Lista<Carta> mesa;

	/**
	 *Constructor de la mesa.
	 */
	public Mesa(){
	    this.mesa = new Lista<>();
	}

	/**
	 *Mete cartas en la mesa. 
	 *@param carta Carta a ingresar.
	 */
	public void meterCarta(Carta carta){
	    this.mesa.add(carta);
	}

	/**
	 *Determina si hay o no un carta en la mesa con cierto palo.
	 *@param palo Palo del cual se busca si alguna carta de la mesa tiene.
	 *@return boolean Si esta o no dicha carta con el palo.
	 */
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

	/**
	 *Determina si hay o no un carta en la mesa con cierto numero.
	 *@param numero Numero del cual se busca si alguna carta de la mesa tiene.
	 *@return boolean Si esta o no dicha carta con ese numero.
	 */
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

	/**
	 *Devuelve la carta con mayor numero dado cierto palo.
	 *@param palo Palo del cual se busca encontrar la carta con mayor numero en la mesa.
	 *@return Carta Carta con el mayor numero.
	 */
	public Carta obtenerCartaMayor(String palo){
	    Iterator<Carta> it = this.mesa.iterator();
	    Carta cartaMayor = this.obtenerPrimerPalo(palo); //La cartaMayor actual es la primera carta en tener el palo deseado.
	    while(it.hasNext()){
		Carta aux = it.next();
		if(aux.getPalo().equals(palo) && aux.getNumero()>= cartaMayor.getNumero()) //Si la carta tiene el mismo palo y un numero mayor a la cartaMayor actual
		    cartaMayor = aux;
	    }
	    return cartaMayor;
	}

	/**
	 *Devuelve la primera carta de la mesa con cierto numero.
	 *@param numero Numero del cual se busca la primera carta.
	 *@return Carta Primera carta de la mesa con dicho numero.
	 */
	public Carta obtenerPrimeraNumerada(int numero){
	    Iterator<Carta> it = this.mesa.iterator();
	    while(it.hasNext()){
		Carta primeraNumerada = it.next();
		if(primeraNumerada.getNumero() == numero)
		    return primeraNumerada;
	    }
	    return null;
	}

	/**
	 *Devuelve la primera carta de la mesa con cierto palo.
	 *@param palo Palo del cual se busca la primera carta.
	 *@return Carta Primera carta de la mesa con dicho palo.
	 */
	public Carta obtenerPrimerPalo(String palo){
	    Iterator<Carta> it = this.mesa.iterator();
	    while(it.hasNext()){
		Carta primerPalo = it.next();
		if(primerPalo.getPalo().equals(palo))
		    return primerPalo;
	    }
	    return null;
	}

	/**
	 *Devuelve las cartas de la mesa.
	 *@return Lista<Carta> Lista de cartas de la mesa.
	 */
	public Lista<Carta> getCartasMesa() {
	    return mesa;
	}
    }
    /**
     *Lista de los jugadores a jugar el truco.
     */
    private Lista<Jugador> jugadores;
    /**
     *Palo lider del truco y el palo de triunfo de la ronda.
     */
    private String paloLider, paloTriunfo;
    /**
     *Carta ganadora del truco.
     */
    private Carta cartaGanadora;
    /**
     *Jugador que gano el truco y el primer jugador en jugar una carta.
     */
    private Jugador jugadorGanador, primerJugador;
    /**
     * Mesa del truco.
     */
    private Mesa mesa;
    /**
     * Historial de los sucesos dados durante el truco.
     */
    private Lista<String> historial;

    /**
     *Constructor de truco.
     *@param jugadores Lista con los jugadores que jugaran el truco.
     *@param primerJugador Jugador que sera el primero en jugar una carta.
     *@param paloDeTriunfo Palo de triunfo de la ronda.
     */
    public Truco(Lista<Jugador> jugadores, Jugador primerJugador, String paloDeTriunfo) {
	this.primerJugador = primerJugador;
	this.jugadores = jugadores;
	this.paloTriunfo = paloDeTriunfo;
	this.paloLider = null;
	this.paloTriunfo = null;
	this.cartaGanadora = null;
	this.jugadorGanador = null;
        this.primerJugador = null;
	this.mesa = new Mesa();
	historial = new Lista<>();
    }

    /**
     *Juega un truco.
     @return boolean Si no forzo el cierre del truco.
     */
   public boolean jugar() {
       if (!pedirCartas()) { //Si se interrumpe pedirCartas, no hay mesa completa para poder calcular la carta mayor.
	    return false;
	}
	calcularCartaGanadora(); //Determina la carta ganadora de la mesa.
	calcularGanador(); //Busca quien jugo la carta ganadora.
	System.out.println("Truco ganado por: " + getGanador().getNombre());
	System.out.println();
	historial.add("Truco ganado por: " + getGanador().getNombre() + "\n\n");
	jugadorGanador.incrementarTrucosGanados();//Incrementa un truco a jugador.
	return true;
    }

    /**
     *Determina quien es el ganador del truco.
     */
    public void calcularGanador() {
	jugadorGanador = cartaGanadora.getJugadoPor();
    }
    /**
     *Regresa el ganador del truco.
     *@return Jugador Ganador del truco.
     */
    public Jugador getGanador() {
	return jugadorGanador;
    }

    /**
     *Pide que cada jugador juege una carta, empezando desde primerJugador.
     *@return boolean Si no se forozo el cierre de pediCartas.
     */
    public boolean pedirCartas() {
	Iterator<Jugador> it = jugadores.iterator();
	int contador = 0; //Contador para ver que hayan tirado todos los jugadores.
	while (it.hasNext()) {
	    Jugador jugador = it.next();
	    if (jugador.equals(primerJugador)) { //Si el jugador actual es el primero en tirar, este juega una carta.
		while (contador < jugadores.size()) {
		    while (it.hasNext() && contador < jugadores.size()) { // Si todavia no recorre toda la lista, tira el jugador siguiente.
			if (!jugarCarta(jugador)) {
			    return false;
			}
			jugador = it.next();
			contador++;
		    }
		    it = jugadores.iterator(); //Si ya llego al final de la lista, pasan los jugadores anteriores al primero en tirar hasta que ya hayan pasado todos.
		}
	    }
	}

	return true;
    }
    /**
     *Hace que un jugador juege una carta, imprimiendo sus opciones, datos del palo de triunfo y lider.
     *@param Jugador Jugador que va a jugar una carta.
     *@return boolean Si no se formo el cierre de jugarCarta.
     */
    private boolean jugarCarta(Jugador jugador) {

	Lista<Carta> manoFiltrada = jugador.getMano();
	String mensaje = "";
	if (paloTriunfo == null) {
	    mensaje += "No hay palo de triunfo\n";
	} else {
	    mensaje += "Palo de triunfo " + paloTriunfo + "\n";
	}
	if (paloLider == null) {
	    mensaje += "No hay palo lider\n";
	} else {
	    mensaje += "Palo lider " + paloLider + "\n";

	}
	mensaje += "Es el turno de " + jugador.getNombre() + ", esta es su mano:\n";
	int contador = 1;
	Iterator<Carta> it = manoFiltrada.iterator();
	while (it.hasNext()) {
	    Carta carta = it.next();
	    mensaje += contador + ".\n" + carta.toString() + "\n";
	    contador++;
	}
	mensaje += "Escoja una carta válida:";
	mensaje += "Escriba -1 si desea concluir la partida:";
	while (true) {
	    int opcion = Interfaz.getInt(mensaje, "Ingrese una opción válida.", -1, manoFiltrada.size());
	    if (opcion == -1) {
		System.out.println(jugador.getNombre() + " terminó la partida.\n");
		historial.add("\t" + jugador.getNombre() + " terminó la partida.\n");
		return false;
	    }
	    Interfaz.ignoreLine();
	    Carta carta = tomarCartaIndice(manoFiltrada, opcion);
	    System.out.println(carta);

	    if (tomarCartaValida(manoFiltrada, carta)) {
		if (paloLider == null && !carta.getPalo().equals("especial")) {
		    paloLider = carta.getPalo();
		}
		historial.add(jugador.getNombre() + " jugó:\n" + carta.toString() + "\n");
		carta.setJugadoPor(jugador);
		mesa.meterCarta(carta);
		jugador.tomarCarta(carta);
		break;
	    }
	}
	return true;
    }
    /**
     *Devuelve una carta de la mano dado un indice.
     *@param mano Lista con las cartas a querer tirar.
     *@param indice Posicion de la carta en la lista que se quiere tomar.
     *@return Carta Carta que se busca tomar de la lista.
     */
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

    /**
     *Determina si la carta seleccionada es valida para ser jugada.
     *@param mano Mano de cartas del jugador.
     *@param carta Carta a validar.
     *@return boolean Si es o no valida la carta.
     */
    private boolean tomarCartaValida(Lista<Carta> mano, Carta carta) {
	if (carta == null) { //Si la carta es vacía.
	    return false;
	}
	if (paloLider == null) { //Si no hay palo Lider.
	    return true;
	}
	if (!contienePalo(mano, paloLider)) {
	    return true; //Si la mano del jugador no tiene cartas con el palo lider.
	}
	if (carta.getPalo().equals(paloLider)) {
	    return true; //Si el palo de la carta es el mismo que el del palo lider.
	}
	if (carta.getPalo().equals("especiale")) { //Si la carta es un wizard o un bufon.
	    return true;
	}
	return false;
    }

    /**
     *Determina si una lista de cartas contiene cartas con cierto palo.
     *@param mano Lista de cartas a verificar.
     *@param palo Palo a buscar en las cartas.
     *@return boolean Si tiene o no una carta con ese palo la mano.
     */
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
    /**
     *Determina la carta ganadora del truco.
     */
    public void calcularCartaGanadora(){
	if(mesa.contieneNumero(14))//La carta ganadora es la primera en tirar un Wizard.
	    cartaGanadora = mesa.obtenerPrimeraNumerada(14);
	else{
	    if(mesa.contienePalo(paloTriunfo))//La carta ganadora es la carta mayor con el palo de triunfo.
		cartaGanadora = mesa.obtenerCartaMayor(paloTriunfo);
	    else{
		if(mesa.contienePalo(paloLider))//La carta ganadora es la cartta mayor con el palo lider.
		    cartaGanadora = mesa.obtenerCartaMayor(paloLider);
		else{
		    cartaGanadora = mesa.obtenerPrimeraNumerada(0);//La carta ganadora es la primera en tirar un bufon.
		}
	    }
	}
    }

    /**
     *Devuelve las cartas tiradas a la mesa.
     *@return Lista<Carta> Lista de cartas tiradas a la mesa.
     */
    public Lista<Carta> getCartasUsadas() {
	return mesa.getCartasMesa();
    }

    /**
     *Regresa el historial del truco.
     *@return Lista<String> Historial del truco.
     */
    public Lista<String> getHistorial() {
	return historial;
    }
}
