package edd.src.Elementos;
import edd.src.Estructuras.*;

import edd.src.Estructuras.*;
import java.util.Random;

public class Baraja {

    private Lista<Carta> baraja;

    public Baraja() {
        baraja = new Lista<>();

        String[] palos = {"rojo", "amarillo", "verde", "azul"};

        for (int i = 0; i < palos.length; i++) {
            for (int j = 1; j <= 13; j++) {
                baraja.add(new Carta(i, palos[i]));
            }
        }
        for (int i = 0; i < 4; i++) {
            baraja.add(new Carta(0, "especial"));
        }
        for (int i = 0; i < 4; i++) {
            baraja.add(new Carta(14, "especial"));
        }
    }

    public void barajear() {
        // Arreglo temporal para barajear cartas.
        Carta[] tmpBaraja = new Carta[baraja.size()];

        int indice = 0;

        while (!baraja.isEmpty()) {
            tmpBaraja[indice] = baraja.pop();
            indice++;
        }

        Random rand = new Random(System.currentTimeMillis());

        for (int i = tmpBaraja.length - 1; i >= 0; i--) {
            int paraCambiar = rand.nextInt(tmpBaraja.length - i);
            Carta tmp = tmpBaraja[paraCambiar];
            tmpBaraja[paraCambiar] = tmpBaraja[i];
            tmpBaraja[i] = tmp;
        }

        for (int i = 0; i < tmpBaraja.length; i++) {
            baraja.add(tmpBaraja[i]);
        }
    }

    public Carta tomarCarta() {
        return baraja.pop();
    }

    public boolean tieneCartas() {
        return baraja.size() > 0;
    }

    public void devolverCartas(Lista<Carta> cartas) {
        baraja.append(cartas);
    }
}
