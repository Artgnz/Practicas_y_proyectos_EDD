package edd.src.Logica;

public enum EncerradoPieza {
    // Rojo    
    R,
    // Azul
    A,
    // Vacío
    V;

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
    @Override
    public String toString() {
        switch(this) {
        case R:
            return "\033[0;31mO\033[0m";
        case A:
            return "\033[0;34mO\033[0m";
        default:
            return " ";
        }
    }
}
