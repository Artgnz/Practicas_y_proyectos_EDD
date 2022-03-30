package edd.src.Estructuras;

public class Practica2 {

    /**
     Metodo que imprime las movidas en un juego de Torres de Hanoi con N cantidad de discos.
     @param cantidadDiscos Entero con la cantidad de discos a jugar.
     @param origen Pila que representa la torre de Hanoi de inicio.
     @param auxiliar Pila que representa la torre de Hanoi auxiliar.
     @param destino Pila que representa la torre de Hanoi a la que se quieren pasar los discos.
     */
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){

	if(cantidadDiscos<=0)	//Considerar el caso en el que n <=0.
	    throw new IllegalArgumentException("La cantidad de discos debe de ser postiva.");
	
	int n = cantidadDiscos; //Asignamos cantidaDiscos a la variable n.
	origen.empty();
	auxiliar.empty(); //Vaciamos las pilas  en caso de que tengan elementos.
	destino.empty();

	for(int i=n; i>0; i--){ //Llenamos la pila.
	    origen.push(i);
	}

	imprimir(n,origen, auxiliar, destino); // Imprimimos el estado inicial de las torres de Hanoi.

	if(n % 2 == 0){ //Si cantidadDiscos es par ciclamos los siguientes movimientos hasta que destino tenga todos los discos.

	    while(destino.longi < n){ 
		mover(origen,auxiliar); //Hacemos un movimiento de origen a auxiliar o de auxiliar a origen.
		imprimir(n,origen, auxiliar, destino);
		mover(origen, destino); //Hacemos un movimiento de origen a destino o de destino a origen.
		imprimir(n,origen, auxiliar, destino);
		mover(auxiliar, destino); //Hacemos un movimiento de auxiliar a destino o de destino a auxiliar.
		imprimir(n,origen, auxiliar, destino);
	    }
	}

	else{ 	//Si cantidadDiscos es non ciclamos los siguientes movimientos hasta que destino tenga todos los discos.

	    while(destino.longi < n){
		mover(origen,destino); //Hacemos un movimiento de origen a destino o de destino a origen.
		imprimir(n,origen, auxiliar, destino);
		mover(origen,auxiliar); //Hacemos un movimiento de origen a auxiliar o de auxiliar a origen.
		imprimir(n,origen, auxiliar, destino);
		mover(auxiliar, destino);  //Hacemos un movimiento de auxiliar a destino o de destino a auxiliar.
		imprimir(n,origen, auxiliar, destino);
		mover(origen,destino); //Hacemos un movimiento de origen a destino o de destino a origen.
		imprimir(n,origen, auxiliar, destino);
	    }
	}
    }

    /**
       Metodo que compara el tamanio del disco cabeza de dos pilas, para luego tomar el mas chico y meterlo en la otra pila.
       @param p1 Primer pila a comparar.
       @param p2 Segunda pila a comparar.
     */
    private static void mover(Pila<Integer> p1, Pila<Integer> p2){

	if((!p1.isEmpty()) && ((p2.isEmpty()) || (p1.peek() < p2.peek())) ) //Si p1 es no vacia y p2 es vacia o la cabeza de p1 es menor que la de p2, quitamos este disco de p1 y se lo apilamos a p2.
	    p2.push(p1.pop()); 

	else{ //Si p1 es vacia o p2 es no vacia y la cabeza de p2 es menor que la de p1, quitamos este disco de p2 y se lo apilamos a p1.
	        p1.push(p2.pop());
	}
	
    }
    /**
       Imprime el estado de las tres torres de hanoi.
       @param n Cantidad de discos que tiene el juego.
       @param origen Pilar inicial.
       @param auxiliar Pilar auxiliar.
       @param destino Pilar final.
     */
    private static void imprimir(int n, Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
	System.out.println("*************************");
	System.out.println("Dado N = " + n); //Cantidad de discos en el juego.
	System.out.println("pilarInicial < " + origen); //Estado de la pila origen.
	System.out.println("pilarAuxiliar < " + auxiliar); //Estado de la pila auxiliar.
	System.out.println("pilarFinal < " + destino); //Estado de la pila final.
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

	//Prueba unitaria de torresHanoi
	Pila<Integer> auxiliar = new Pila<>();
	Pila<Integer> destino = new Pila<>();

	torresHanoi(cantidadDiscos, origen, auxiliar, destino);
	
        for (int i = 1; i <= 10; i++) {
            System.out.println("Binario de N: " + i + " números:");
            binarioColas(i);
        }
    }
}
