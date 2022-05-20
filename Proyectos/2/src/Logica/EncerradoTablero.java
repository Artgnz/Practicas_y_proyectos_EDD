package edd.src.Logica;

import edd.src.Estructuras.Lista;

import java.util.Arrays;

/**
 * Clase que representa a un tablero del juego "encerrado".
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */
public class EncerradoTablero {
    /**
     * Tablero.
     */
    private EncerradoPieza[][] posicion;
    /**
     * Pieza de la que es turno
     */
    private EncerradoPieza turno;

    /**
     * Constructor del tablero. Recibe un tablero y un turno.
     * @param posicion Arreglo de piezas en una posición.
     * @param turno Turno del jugador.
     */
    public EncerradoTablero(EncerradoPieza[][] posicion, EncerradoPieza turno) {
        this.posicion = posicion;
        this.turno = turno;
    }
    /**
     * Constructor del tablero. Establece un arreglo de piezas con posiciones predeterminadas y un jugador
     * predeterminado.
     */
    public EncerradoTablero() {
        posicion = new EncerradoPieza[][] { 
            {EncerradoPieza.R , null            , EncerradoPieza.A},
            {null             , EncerradoPieza.V, null},
            {EncerradoPieza.A , null            , EncerradoPieza.R},};
        turno = EncerradoPieza.R;
    }

    /**
     * Regresa la pieza del turno actual.
     * @return turno Turno actual.
     *
     */
    public EncerradoPieza getTurno() {
        return turno;
    }

    /**
     * Evalúa el escenario actual de desde la perspectiva de un jugador.
     * Regresa un número indicando si el jugador actual perdió, ganó o si aun no hay algun ganador.
     * @param jugador Jugador actual
     * @return double Evaluación del escenario.
     */
    public double evaluar(EncerradoPieza jugador) {
        if (esVictoria() && turno == jugador) {
            return -1;
        } else if (esVictoria() && turno != jugador) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Mueve una pieza del tablero regresando un nuevo tablero con el movimiento.
     * @param movimiento Cadena que indica el movimiento.
     * @return EncerradoTablero El nuevo tablero con el movimiento.
     */
    public EncerradoTablero mover(String movimiento) {
        // Crea una copia del tablero
        EncerradoPieza[][] tempPosicion = Arrays.copyOf(posicion, posicion.length);
        for (int col = 0; col < posicion.length; col++) {
            tempPosicion[col] = Arrays.copyOf(posicion[col], posicion[col].length);
        }
        // Indica la posición de origen
        String de = movimiento.split("-")[0];
        // Indica la posición de destino
        String hacia = movimiento.split("-")[1];
        // Marca la posición de origen con vacío.
        colocar(tempPosicion, de, EncerradoPieza.V);
        // Marca la posición de destino con la pieza del turno.
        colocar(tempPosicion, hacia, turno);
        // Asigna el tablero al nuevo
        posicion = tempPosicion;

        return new EncerradoTablero(tempPosicion, turno.opuesto());
    }

    /**
     * Coloca una pieza en una posición específica en el tablero dado.
     * @param tablero Tablero donde se realiza el movimiento.
     * @param donde Indica donde se coloca la pieza.
     * @param pieza Pieza que se coloca.
     */
    private void colocar(EncerradoPieza[][] tablero, String donde, EncerradoPieza pieza) {
        int fila = donde.charAt(1) - '0' - 1;
        int columna = donde.charAt(0) - 'A';
        tablero[fila][columna] = pieza;
    }

    /**
     * Indica si el tablero actual ya tiene una victoria.
     * @return Si ya hay una victoria.
     */
    public boolean esVictoria() {
        // Si ya no tiene movimientos legales el oponente
        return obtenerMovimientosLegales().isEmpty();
    }

    /**
     * Obtiene los posibles movimientos que puede realizar el
     * jugador del turno actual.
     * @return Lista de movimientos posibles.
     */
    public Lista<String> obtenerMovimientosLegales() {
        Lista<String> movimientosLegales = new Lista<>();

        for (int i = 0; i < posicion.length; i++) {
            for (int j = 0; j < posicion.length; j++) {
                if(posicion[i][j] == turno) {
                    // Obtiene el movimiento posible en la posición actual.
                    String movimiento = movimiento(i, j);
                    // Si sí hay un movimiento posible
                    if (movimiento != "") {
                        movimientosLegales.add(movimiento);
                    }
                }
            }
        }
        return movimientosLegales;
    }

    /**
     * Regresa un movimiento posible en la posición indicada.
     * @param i Fila donde revisar.
     * @para j Columna donde revisar.
     * @return String con el movimiento, si no hay movimiento regresa " ".
     */
    private String movimiento(int i, int j) {
        String movimiento = "";
        String[] columnas = { "A", "B", "C" };
        String[] filas = { "1", "2", "3" };
        if (esPosicionValida(i - 2, j)) {
            movimiento = columnas[j] + filas[i] + "-" + columnas[j] + filas[i - 2];
        }

        if (esPosicionValida(i + 2, j)) {
            movimiento = columnas[j] + filas[i] + "-" + columnas[j] + filas[i + 2];
        }

        // Solo las fichas de la primera fila se pueden mover horizontalmente.
        if (i == 0) {
            if (esPosicionValida(i, j + 2)) {
                movimiento = columnas[j] + filas[i] + "-" + columnas[j + 2] + filas[i];
            }

            if (esPosicionValida(i, j - 2)) {
                movimiento = columnas[j] + filas[i] + "-" + columnas[j - 2] + filas[i];
            }
        }

        if (esPosicionValida(i + 1, j + 1)) {
            movimiento = columnas[j] + filas[i] + "-" + columnas[j + 1] + filas[i + 1];
        }
        if (esPosicionValida(i - 1, j - 1)) {
            movimiento = columnas[j] + filas[i] + "-" + columnas[j - 1] + filas[i - 1];
        }

        if (esPosicionValida(i - 1, j + 1)) {
            movimiento = columnas[j] + filas[i] + "-" + columnas[j + 1] + filas[i - 1];
        }
        if (esPosicionValida(i +  1, j - 1)) {
            movimiento = columnas[j] + filas[i] + "-" + columnas[j - 1] + filas[i + 1];
        }

        return movimiento;
    }

    /**
     * Indica si es posible colocar fichas en una posición espefífica.
     * @param i Fila donde se revisa.
     * @param j Columna donde se revisa.
     * @return boolean Si es posible colocar una ficha en la posición dada.
     */
    private boolean esPosicionValida(int i, int j) {
        return i >= 0 && i < posicion.length && j >= 0 && j < posicion.length && posicion[i][j] == EncerradoPieza.V ;
    }

    /**
     * Regresa una representación en cadena del tablero.
     * @return representación en cadena del tablero.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1  " + posicion[0][0] + "---" + posicion[0][2] + "\n");
        sb.append("   " + "|\\ /|" + "\n");
        sb.append("2  " + "| " + posicion[1][1] + " |\n");
        sb.append("   " + "|/ \\|\n");
        sb.append("3  " + posicion[2][0] + "   " + posicion[2][2] + "\n");
        sb.append("   A B C");
        return sb.toString();
    }
}
