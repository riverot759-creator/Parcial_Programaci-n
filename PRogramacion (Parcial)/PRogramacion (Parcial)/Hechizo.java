public class Hechizo {
    // Clase para los hechizos del mago.
    private String nombre;
    private int potencia;
    private int costoMana;

    public Hechizo(String nombre, int potencia, int costoMana) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.costoMana = costoMana;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getCostoMana() {
        return costoMana;
    }

    public void mostrarHabilidades() {
        System.out.println(nombre + " (Potencia " + potencia + ", Costo " + costoMana + ")");
    }
}
