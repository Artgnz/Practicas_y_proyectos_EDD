package edd.src.Elementos;

import edd.src.Estructuras.*;

import java.util.Random;
import java.util.Iterator;

/**
 * Clase que representa a la baraja de cartas del juego Wizard.
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */
public class Baraja {

    /**
     * Lista con las 60 cartas del juego wizard.
     */
    private Lista<Carta> baraja;

    /**
     * Constructor de la baraja, cuenta con las 60 cartas, compuestas por cuatro
     * palos numerados del 1 al 13, 4 wizards y 4 bufones.
     */
    public Baraja() {
        baraja = new Lista<>();

        String[] palos = {"rojo", "amarillo", "verde", "azul"};
        // Inicializa baraja con tarjetas que pertenezcan a algún palo.
        for (int i = 0; i < palos.length; i++) {
            for (int j = 1; j <= 13; j++) {
                baraja.add(new Carta(j, palos[i]));
            }
        }
        // Inicializa baraja con bufones.
        for (int i = 0; i < 4; i++) {
            baraja.add(new Carta(0, "especial"));
        }
        // Inicializa baraja con magos.
        for (int i = 0; i < 4; i++) {
            baraja.add(new Carta(14, "especial"));
        }
    }

    /**
     * Se encarga de desordenar el orden de las cartas en la lista, representando
     * la accion de barajear las 60 cartas.
     */
    public void barajear() {
        // Arreglo temporal para barajear cartas.
        Carta[] tmpBaraja = new Carta[baraja.size()];

        int indice = 0;

        while (!baraja.isEmpty()) {
            tmpBaraja[indice] = baraja.pop();
            indice++;
        }

        Random rand = new Random(System.currentTimeMillis());
        // For para intercambiar lugares de las cartas en el arreglo.
        for (int i = tmpBaraja.length - 1; i >= 0; i--) {
            int paraCambiar = rand.nextInt(i + 1);
            Carta tmp = tmpBaraja[paraCambiar];
            tmpBaraja[paraCambiar] = tmpBaraja[i];
            tmpBaraja[i] = tmp;
        }

        // La baraja recupera las cartas pero desordenadas.
        for (int i = 0; i < tmpBaraja.length; i++) {
            baraja.add(tmpBaraja[i]);
        }
    }

    /**
     * Toma la primera carta de la lista.
     * @return Carta Carta al inicio de la lista.
     */
    public Carta tomarCarta() {
        return baraja.pop();
    }

    /**
     * Determina si la baraja tiene cartas.
     * @return boolean Si tiene o no cartas.
     */
    public boolean tieneCartas() {
        return baraja.size() > 0;
    }

    /**
     * Devuelve la cantidad de cartas que hay en la baraja.
     * @return int Cantidad de cartas en la baraja.
     */
    public int tamano() {
        return baraja.size();
    }

    /**
     * Agrega una lista de cartas en la baraja.
     * @param cartas Lista de cartas a agregar.
     */
    public void devolverCartas(Lista<Carta> cartas) {
        baraja.append(cartas);
    }

    /**
     * Agrega una carta devuelta a la baraja.
     * @param carta Carta a agregar.
     */
    public void devolverCarta(Carta carta) {
        baraja.add(carta);
    }

    /**
     Imprime en la pantalla las cartas de la baraja.
     */
    public void imprimir() {
        Iterator<Carta> it = baraja.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
