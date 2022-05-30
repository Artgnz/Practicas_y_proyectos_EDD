package edd.src.Estructuras;

public class CustomInteger implements ComparableIndexable<CustomInteger>{
    int i;
    int indice;
    

    public CustomInteger(int pI) {
        i = pI;
    }

    public String toString() {
        return "i: " + i ;
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
