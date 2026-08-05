

public class Arma {
    // Clase para un arma simple: nombre y daño base.
    private String nombre;
    private int danioBase;

    public Arma(String nombre, int danioBase) {
        this.nombre = nombre;
        this.danioBase = danioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDanioBase() {
        return danioBase;
    }
}