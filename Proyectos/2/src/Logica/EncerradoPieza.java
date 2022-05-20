package edd.src.Logica;

/**
 * Enumeración que representa a una pieza de encerrado.
 * @author Arturo González Peñaloza
 * @author Arsenio Raudry Rico
 */

public enum EncerradoPieza {
    // Rojo
    R,
    // Azul
    A,
    // Vacío
    V;

    /**
     * Regresa la piesta opuesta a <code>this</code>.
     * @return EncerradoPieza la pieza opuesta.
     */
    public EncerradoPieza opuesto() {
        switch(this) {
            case R:
                return EncerradoPieza.A;
            case A:
                return EncerradoPieza.R;
            default:
                return EncerradoPieza.V;
        }
    }

    /**
     * Regresa una representación en cadena de la pieza.
     * La representación consiste en una "O" del color de la
     * pieza.
     * @return Representación en cadena de la pieza.
     */
    @Override
    public String toString() {
        switch(this) {
        case R:
            // "O" de color rojo.
            return "\033[0;31mO\033[0m";
        case A:
            // "O"de color azul.
            return "\033[0;34mO\033[0m";
        default:
            // Espacio vacío.
            return " ";
        }
    }
}
