package edd.src.Estructuras;

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
