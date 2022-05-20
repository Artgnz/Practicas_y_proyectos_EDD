package edd.src.Logica;
import edd.src.Estructuras.Lista;
import edd.src.Estructuras.ArbolBinario;
import edd.src.Estructuras.Cola;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase que representa al arbol de decisiones minimax.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class ArbolMinimax extends ArbolBinario<Jugada>{

    /**
     * Clase privada para iteradores de arboles binarios completos.
     */
    private class Iterador implements Iterator<Jugada>{
	private Cola<Vertice> cola;
	/**
	 *Cola para recorrer los vertices en BFS.
	 */
	public Iterador(){
	    cola = new Cola<Vertice>();
	    if(isEmpty())
                return;
            cola.push(raiz);
        }
	/**
	 *Si hay un elemento siguiente.
	 @return boolean Si hay o no siguiente.
	 */
        public boolean hasNext() {
            return !cola.isEmpty();
        }

        @Override
        public Jugada next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Vertice v = cola.pop();
            if (v.izquierdo != null)
                cola.push(v.izquierdo);
            if (v.derecho != null)
                cola.push(v.derecho);
            return v.elemento;
        }
    }

    public ArbolMinimax(EncerradoTablero tablero, int limProfundidad){
	this.raiz = new Vertice(new Jugada(tablero));
	this.raiz = construirArbol(raiz, limProfundidad);
    }

    public Vertice raiz(){
	return this.raiz;
    }
    
    public Vertice construirArbol(Vertice raiz, int limProfundidad){
	
	EncerradoTablero tablero = raiz.elemento.getTablero();
	
	if(raiz.profundidad() <=limProfundidad || tablero.evaluar(tablero.getTurno())!=0){

	    Lista<String> movidas = tablero.obtenerMovimientosLegales();
	    
	    if(movidas.size()==1){

	    String movimiento = movidas.pop();
	    EncerradoTablero tableroIzquierdo = tablero.mover(movimiento);
	    Jugada jugadaIzquierda = new Jugada(tableroIzquierdo,movimiento);
	    raiz.izquierdo = new Vertice(jugadaIzquierda);
	    raiz.izquierdo = construirArbol(raiz.izquierdo, limProfundidad);
	    return raiz;
	    }
	    else{

	    }
	if(movidas.size()==2){

	    String movimientoDerecho = movidas.pop();
	    String movimientoIzquierdo = movidas.pop();
	    
	    EncerradoTablero tableroDerecho = tablero.mover(movimientoDerecho);
	    Jugada jugadaDerecha = new Jugada(tableroDerecho,movimientoDerecho);

	    EncerradoTablero tableroIzquierdo = tablero.mover(movimientoIzquierdo);
	    Jugada jugadaIzquierda = new Jugada(tableroIzquierdo,movimientoIzquierdo);

	    raiz.derecho = new Vertice(jugadaDerecha);
	    raiz.derecho = construirArbol(raiz.derecho, limProfundidad);
	    raiz.izquierdo = new Vertice(jugadaIzquierda);
	    raiz.izquierdo = construirArbol(raiz.izquierdo, limProfundidad);
	    return raiz;
	    }
	    
        }
	    return raiz;
    }

    public double obtenerTriunfo(Vertice raiz, int i){

	EncerradoTablero tablero = raiz.elemento.getTablero();

	if(raiz.hayIzquierdo() && !raiz.hayDerecho()){
	    return obtenerTriunfo(raiz.izquierdo, raiz.izquierdo.profundidad());
	}

	if(raiz.hayDerecho() && !raiz.hayIzquierdo()){
	    return obtenerTriunfo(raiz.derecho, raiz.derecho.profundidad());
	}

	if(raiz.hayIzquierdo() && raiz.hayDerecho()){
	    double izq,der;
	    raiz.izquierdo.elemento.setTriunfo(obtenerTriunfo(raiz.izquierdo,raiz.izquierdo.profundidad()));
	    izq = raiz.izquierdo.elemento.getTriunfo();

    	    raiz.derecho.elemento.setTriunfo(obtenerTriunfo(raiz.derecho,raiz.derecho.profundidad()));
	    der = raiz.derecho.elemento.getTriunfo();

	    if(i % 2 == 0)
		return max(izq,der);
	    else{
		return min(izq,der);
	    }
	}

	    return tablero.evaluar(tablero.getTurno());
	
    }

    public String obtenerMejorMovimiento(Vertice raiz){

	if(raiz.hayIzquierdo() && raiz.hayDerecho()){

        Vertice izq = raiz.izquierdo;
	Vertice der = raiz.derecho;

	izq.elemento.setTriunfo(obtenerTriunfo(izq, izq.profundidad()));
        der.elemento.setTriunfo(obtenerTriunfo(der, der.profundidad()));

        double izqTriunfo = izq.elemento.getTriunfo();
        double derTriunfo = der.elemento.getTriunfo();

	if(izqTriunfo < derTriunfo)
	    return izq.elemento.getMovimiento();
	else{
	    return der.elemento.getMovimiento();
	    }
	}

	if(raiz.hayIzquierdo() && !raiz.hayDerecho()){
	    return raiz.izquierdo.elemento.getMovimiento();
	}

	if(raiz.hayDerecho() && !raiz.hayIzquierdo()){
	    return raiz.derecho.elemento.getMovimiento();
	}

	    return raiz.elemento.getMovimiento();
	
    }
    

    private double max(double a, double b) {
        if (a > b) {
            return a;
        }
	else{
            return b;
        }
    }

    private double min(double a, double b) {
        if (a < b) {
            return a;
        }
	else{
            return b;
        }
    }

    @Override
    public boolean delete(Jugada elemento) {
        return false;
    }

    @Override
    public void add(Jugada elemento) {
    }
    
      /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden BFS.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<Jugada> iterator() {
        return new Iterador();
    }
}
