package edd.src.Estructuras;

public class Practica2 {
    
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        // No olvides imprimir cada paso de la solución.
    }

    public static void binarioColas(int N){
        Cola<String> aux = new Cola<>();
        aux.push("1");
        for (int i = 0; i < N; i++) {
            String current = aux.pop();
            aux.push(current + "0");
            aux.push(current + "1");
            System.out.println(current);
        }
    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme.
        Pila<Integer> pila = new Pila<>();
        pila.push(1);
        pila.push(2);
        pila.push(3);
        Pila<Integer> nueva = pila.clone();
        if (!pila.equals(nueva)) {
            System.out.println("El método clone de Pila no sirve.");
        }
        Cola<Integer> cola = new Cola<>();
        cola.push(1);
        cola.push(2);
        cola.push(3);
        if (!cola.toString().equals("1, 2, 3")) {
            System.out.println("El método toString no sirve.");
        }
        cola.pop();
        cola.push(5);
        if (!cola.toString().equals("2, 3, 5")) {
            System.out.println("El método toString no sirve.");
        }
        Cola<Integer> copiaCola = cola.clone();
        if (!copiaCola.equals(cola)) {
            System.out.println("El método clone de Cola no sirve.");
        }

        Pila<Integer> origen = new Pila<>();
        int cantidadDiscos = 4;
        for (int i = 1; i <= cantidadDiscos; i++) {
            origen.push(i);
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println("Binario de N: " + i + " números:");
            binarioColas(i);
        }
    }
}
