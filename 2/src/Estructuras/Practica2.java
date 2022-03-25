package edd.src.Estructuras;

public class Practica2 {
    
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        // No olvides imprimir cada paso de la solución. 
    }

    public static void binarioColas(int N){

    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme.
        Pila<Integer> pila = new Pila<>();
        // pila.push(1);
        // pila.push(2);
        // pila.push(3);
        // pila.push(4);
        // pila.push(5);
        pila.push(1);
        // pila.push(2);
        // pila.push(3);
        pila.push(4);
        // pila.push(5);
        System.out.println(pila);
        Pila<Integer> nueva = pila.clone();
        System.out.println(nueva);
        System.out.println(nueva.size());
        System.out.println(pila.size());
    }
}
