package edd.src.Logica;

import edd.src.Estructuras.Lista;
import edd.src.Estructuras.ArbolBinarioCompleto;
import edd.src.Elementos.*;
import edd.src.Interfaz.*;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase que representa el una jugada en el juego Encerrado.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class Jugada{
    private EncerradoTablero tablero;
    private String movimiento;
    private double triunfo;

    /**
     *Constructor de la jugada.
     *@param EncerradoTablero
     */
    public Jugada(EncerradoTablero tablero){
	this.tablero = tablero;
	this.movimiento = null;
	this.triunfo = tablero.evaluar(tablero.getTurno());
    }

    public Jugada(EncerradoTablero tablero, String movimiento){
	this.tablero = tablero.mover(movimiento);
	this.movimiento = movimiento;
	this.triunfo = tablero.evaluar(tablero.getTurno());
    }

    public void setTriunfo(double triunfo){
	this.triunfo = triunfo;
    }

    public double getTriunfo(){
	return this.triunfo;
    }

    public String getMovimiento(){
	return this.movimiento;
    }

    public EncerradoTablero getTablero(){
	return this.tablero;
    }
}
