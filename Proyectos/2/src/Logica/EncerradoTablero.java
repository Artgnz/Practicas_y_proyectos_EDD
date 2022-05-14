package edd.src.Logica;

import edd.src.Estructuras.Lista;
import edd.src.Logica.EncerradoPieza;

import java.util.Arrays;

public class EncerradoTablero {
    private EncerradoPieza[][] posicion;
    private EncerradoPieza turno;

    public EncerradoTablero(EncerradoPieza[][] posicion, EncerradoPieza turno) {
        this.posicion = posicion;
        this.turno = turno;
    }

    public EncerradoTablero() {
        // posicion = new EncerradoPieza[][] { 
        //     {EncerradoPieza.A , null            , EncerradoPieza.V},
        //     {null             , EncerradoPieza.R, null},
        //     {EncerradoPieza.A , null            , EncerradoPieza.R},};
        posicion = new EncerradoPieza[][] { 
            {EncerradoPieza.R , null            , EncerradoPieza.A},
            {null             , EncerradoPieza.V, null},
            {EncerradoPieza.A , null            , EncerradoPieza.R},};
        turno = EncerradoPieza.R;
    }

    public EncerradoPieza getTurno() {
        return turno;
    }

    public double evaluar(EncerradoPieza jugador) {
        if (esVictoria() && turno == jugador) {
            return -1;
        } else if (esVictoria() && turno != jugador) {
            return 1;
        } else {
            return 0;
        }
    }

    public EncerradoTablero mover(String movimiento) {
        EncerradoPieza[][] tempPosicion = Arrays.copyOf(posicion, posicion.length);
        // C4Piece[][] tempPosition = Arrays.copyOf(position, position.length);
        for (int col = 0; col < posicion.length; col++) {
            tempPosicion[col] = Arrays.copyOf(posicion[col], posicion[col].length);
        }
        String de = movimiento.split("-")[0];
        String hacia = movimiento.split("-")[1];
        colocar(tempPosicion, de, EncerradoPieza.V);
        colocar(tempPosicion, hacia, turno);
        posicion = tempPosicion;
        return new EncerradoTablero(tempPosicion, turno.opuesto());
    }

    private void colocar(EncerradoPieza[][] tablero, String donde, EncerradoPieza pieza) {
        int fila = donde.charAt(1) - '0' - 1;
        int columna = donde.charAt(0) - 'A';
        tablero[fila][columna] = pieza;
    }

    public boolean esVictoria() {
        // Si ya no tiene movimientos legales el oponente
        return obtenerMovimientosLegales().isEmpty();
    }

    // public boolean esEmpate() {
    //     // TODO Auto-generated method stub
    //     return false;
    // }

    public Lista<String> obtenerMovimientosLegales() {
        Lista<String> movimientosLegales = new Lista<>();

        for (int i = 0; i < posicion.length; i++) {
            for (int j = 0; j < posicion.length; j++) {
                if(posicion[i][j] == turno) {
                    String movimiento = movimiento(i, j);
                    if (movimiento != "") {
                        movimientosLegales.add(movimiento);
                    }
                }
            }
        }
        return movimientosLegales;
    }

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

    private boolean esPosicionValida(int i, int j) {
        return i >= 0 && i < posicion.length && j >= 0 && j < posicion.length && posicion[i][j] == EncerradoPieza.V ;
    }

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
