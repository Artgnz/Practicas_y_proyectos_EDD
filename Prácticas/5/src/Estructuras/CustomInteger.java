package edd.src.Estructuras;

/**
 * Clase que sirve como Integer que implementa a ComparableIndexable.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class CustomInteger implements ComparableIndexable<CustomInteger>{
    Integer i;
    int indice;

    public CustomInteger(Integer pI) {
        i = pI;
    }

    public String toString() {
        return i.toString();
    }
    
    @Override
    public int compareTo(CustomInteger otro) {
        return i - otro.i;
    }
    
    @Override
    public int getIndice() {
        return this.indice;
    }

    @Override
    public void setIndice(int indice) {
        this.indice = indice;
    }


}
