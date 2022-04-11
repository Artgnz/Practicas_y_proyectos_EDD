package edd.src.Jugador;

import edd.src.Estructuras.*;

public class Jugador {
    private String nombre;
    private Lista<Carta> mano;
    private boolean esBarajeador;
    private Datos estado;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = mano = new Lista<>();
	this.esBarajeador = false;
	this.estado = estado = new Datos();
    }

    public String getNombre(){
	return this.nombre;
    }

    public Lista<Carta> getMano(){
	return this.mano;
    }
    
    public boolean getEsBarajeador(){
	return this.esBarajeador;
    }

    public Datos getEstado(){
	return this.estado;
    }

    public void setEsBarajeador(boolean esBarajeador){
	this.esBarajeador = esBarajeador
    }

    public void setEstado(){

    }

    @Override
    public String toString(){
       if(this.esBarajeador == true)
	return "Nombre: " + this.nombre + "\n Mano: " + this.mano.toString() +"\n Barajea : Si. \n Estado:" + this.estado.toString();

       if(this.esBarajeador == false)
	return "Nombre: " + this.nombre + "\n Mano: " + this.mano.toString() +"\n Barajea : No. \n Estado:" + this.estado.toString(); 
    }
}
