public class Ataque {
    // Clase para los ataques del guerrero.
    private String nombre;
    private int poder;
    private int costoStamina;

    public Ataque(String nombre, int poder, int costoStamina) {
        this.nombre = nombre;
        this.poder = poder;
        this.costoStamina = costoStamina;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoder() {
        return poder;
    }

    public int getCostoStamina() {
        return costoStamina;
    }

    public void mostrarHabilidad() {
        System.out.println(nombre + " (Poder " + poder + ", Costo " + costoStamina + ")");
    }
}
