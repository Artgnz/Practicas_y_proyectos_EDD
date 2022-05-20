package edd.src.Logica;

import edd.src.Estructuras.Lista;
import edd.src.Interfaz.*;

/**
 * Clase que representa el desarrollo de un juego de Encerrado.
 * @author Arturo González Peñaloza
 * @author Emilio Arsenio Raudry Rico
 */
public class Juego {

    /**
     * Tablero del juego.
     */
    private EncerradoTablero tablero;

    /**
     * Obtiene el movimiento del usuario.
     * @return Movimiento del usuario.
     */
    private String obtenerMovimientoUsuario() {

        // Se obtiene una lista con los posibles movimientos.
        Lista<String> movimientosPosibles = tablero.obtenerMovimientosLegales();

        String mensaje = "Introduzca su movimiento en el formato (A1-B2), donde indica que mueve de la posición A1 a la B2:";
        
        String movimientoUsuario = Interfaz.getString(mensaje, "Ingrese una opción valida.", movimientosPosibles);
        return movimientoUsuario;
    }

    /**
     * Obtiene la configuración inicial del tablero.
     * @return Configuración inicial de las fichas.
     */
    private EncerradoPieza[][] obtenerConfiguracionInicial() {
        // Piezas que el usuario escoge.
        String[] escogidas = new String[5];
        while (true) {
            // Posibles fichas.
            String[] fichas = new String[] {"rojo", "azul", "vacio"};
            escogidas[0] = Interfaz.getString("Ingrese que ficha desea en la posición A1 (rojo, azul o vacio)", "Ingrese una opción válida", fichas);
            escogidas[1] = Interfaz.getString("Ingrese que ficha desea en la posición A3 (rojo, azul o vacio)", "Ingrese una opción válida", fichas);
            escogidas[2] = Interfaz.getString("Ingrese que ficha desea en la posición B2 (rojo, azul o vacio)", "Ingrese una opción válida", fichas);
            escogidas[3] = Interfaz.getString("Ingrese que ficha desea en la posición C1 (rojo, azul o vacio)", "Ingrese una opción válida", fichas);
            escogidas[4] = Interfaz.getString("Ingrese que ficha desea en la posición C3 (rojo, azul o vacio)", "Ingrese una opción válida", fichas);
            if (sonFichasValidas(escogidas)) {
                break;
            } else {
                System.out.println("Por favor escoja configuraciones válidas.");
            }
        }
        return new EncerradoPieza[][] { 
            {deStringAEncerradoPieza(escogidas[0]) , null            , deStringAEncerradoPieza(escogidas[3])},
            {null             , deStringAEncerradoPieza(escogidas[2]), null},
            {deStringAEncerradoPieza(escogidas[1]) , null            , deStringAEncerradoPieza(escogidas[4])},};
    }

    /**
     * Función de ayuda de <code>obtenerconfiguracioninicial </code>. Indica si la cantidad de fichas
     * es la correcta de acuerdo a las reglas de Encerrado.
     * @param fichas[] Las fichas.
     * @return boolean Si la cantidad de fichas de cada color es correcta.
     */
    private boolean sonFichasValidas(String fichas[]) {
        int rojas = 0;
        int azules = 0;
        int vacias = 0;
        for (String s: fichas) {
            switch(s) {
            case "rojo":
                rojas++;
                break;
            case "azul":
                azules++;
                break;
            default:
                vacias++;
                break;
            }
        }
        return rojas == 2 && azules == 2 && vacias == 1;
    }
     

    
    /**
     * Convierte una cadena en una EncerradoPieza.
     * @param pieza String a convertir.
     * @return EncerradoPieza Conversión de la string.
     */
    private EncerradoPieza deStringAEncerradoPieza(String pieza) {
        switch(pieza) {
        case "rojo":
            return EncerradoPieza.R;
        case "azul":
            return EncerradoPieza.A;
        default:
            return EncerradoPieza.V;
        }
    }

    /**
     * Constructor del juego.
     * Crea un tablero.
     */
    public Juego() {
        tablero = new EncerradoTablero();
    }

    /**
     * Método que realiza las partidas del juego.
     */
    public void jugar() {
        
        System.out.println("¡Hola, bienvenido al juego de encerrado!");
        String mensaje = "Si desea ver el tutorial, presione 1. Si no, presione 2.";

        int opcion = Interfaz.getInt(mensaje, "Ingrese una opción valida.",1 , 2);
        if (opcion == 1) {
            mostrarTutorial();
            System.out.println();
        }

        Interfaz.ignoreLine();

        mensaje = "Si desea jugar con una configuración inicial específica, presione 1. Si no, presione 2.";
        opcion = Interfaz.getInt(mensaje, "Ingrese una opción valida.",1 , 2);
        Interfaz.ignoreLine();
        EncerradoPieza[][] posiciones = null;
        if (opcion == 1) {
            posiciones = obtenerConfiguracionInicial();
        }

        mensaje = "Indique quien es el jugador inicial. Escriba 1 si la computadora, 2 si usted.";
        int jugador = Interfaz.getInt(mensaje, "Ingrese una opción válida.", 1, 2);
        Interfaz.ignoreLine();
        
        if (posiciones != null) {
            tablero = new EncerradoTablero(posiciones);
        }
        System.out.println("----------Inicio del juego----------");

        if (tablero.esVictoria()) {
            System.out.println("++++++++++++++++++++");
            if (jugador % 2 != 0) {
                System.out.println("Has ganado!");

            } else {
                System.out.println("La computadora gana!");
            }
            System.out.println("Tablero final:");
            System.out.println(tablero);
            System.out.println("++++++++++++++++++++");
            return;
        }
        while (true) {
            System.out.println(tablero);

            if (jugador % 2 == 0){
                System.out.println("Turno del usuario (" + tablero.getTurno() + "): ");
                String movimientoUsuario = obtenerMovimientoUsuario();
                tablero = tablero.mover(movimientoUsuario);
                if (tablero.esVictoria()) {
                    System.out.println("++++++++++++++++++++");
                    System.out.println("Has ganado!");
                    System.out.println("Tablero final:");
                    System.out.println(tablero);
                    System.out.println("++++++++++++++++++++");
                    break;
                }
            } else {
                System.out.println("Turno de la computadora (" + tablero.getTurno() + "): ");
                String movimientoComputadora;
                // Aquí es donde la computadora debe jugar
                movimientoComputadora = tablero.obtenerMovimientosLegales().pop();
                System.out.println("Movimiento de la computadora: " + movimientoComputadora);
        tablero = tablero.mover(movimientoComputadora);

                // tablero = tablero.mover(movimientoComputadora);
                if (tablero.esVictoria()) {
                    System.out.println("++++++++++++++++++++");
                    System.out.println("La computadora gana!");
                    System.out.println("Tablero final:");
                    System.out.println(tablero);
                    System.out.println("++++++++++++++++++++");
                    break;
                }
            }
        jugador++;
        }
    }

    /**
     * Muestra un tutorial de cómo jugar.
     */
    public void mostrarTutorial() {

        System.out.println("El juego consiste en lograr que las piezas del oponente ya no puedan moverse.");
        System.out.println("Se juega con un tablero como el siguiente:");

        EncerradoTablero tmp = new EncerradoTablero();

        System.out.println(tmp);

        System.out.println("Los jugadores por turnos se alternan para mover una de sus fichas cumpliendo las siguientes reglas:");
        System.out.println("1. Una ficha no puede moverse a espacios ocupados por otras fichas.");
        System.out.print("2. Una ficha solo puede moverse a un espacio si hay una arista que lo conecte con el espacio en el que");
        System.out.println(" se encuentra actualmente dicha ficha.");

        System.out.println("Se moverán las fichas escribiendo el movimiento en el formato:");

        System.out.println("\t [ColumnaDeOrigen][FilaDeOrigen]-[ColumnaDestino][FilaDestino]");

        System.out.println("Por ejemplo, con el siguiente tablero");
        System.out.println(tmp);
        System.out.println("Si es el turno de las fichas rojas, puede mover las fichas de la siguiente forma:");
        System.out.println();
        System.out.println("\tA1-B2");
        System.out.println();
        tmp.mover("A1-B2");
        System.out.println(tmp);
        System.out.println("o");
        tmp = new EncerradoTablero();
        tmp.mover("C3-B2");
        System.out.println("\tC3-B2");
        System.out.println(tmp);
        System.out.println("El juego concluye cuando algún jugador no pueda mover ninguna de sus fichas.");
    }
}
