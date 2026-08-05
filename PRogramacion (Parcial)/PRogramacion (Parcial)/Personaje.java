
public class Personaje {
    // Clase base que guarda datos comunes para todos los personajes.
    private String nombre;
    private int salud;
    private int maxSalud;
    private int nivel;
    private Inventario inventario;
    private int resistenciaTemporal;

    public Personaje(String nombre, int salud, int nivel) {
        this.nombre = nombre;
        this.salud = salud;
        this.maxSalud = salud;
        this.nivel = nivel;
        this.inventario = new Inventario(5);
        this.resistenciaTemporal = 0;
    }

    public void atacar(Personaje objetivo) {
        int daño = 4 + nivel;
        System.out.println(nombre + " ataca y causa " + daño + " puntos de daño.");
        objetivo.recibirDaño(daño);
    }

    public void recibirDaño(int daño) {
        int dañoReal = daño;
        if (resistenciaTemporal > 0) {
            dañoReal -= resistenciaTemporal;
            if (dañoReal < 0) {
                dañoReal = 0;
            }
            System.out.println(nombre + " bloquea " + resistenciaTemporal + " puntos de daño.");
            resistenciaTemporal = 0;
        }
        salud -= dañoReal;
        System.out.println(nombre + " recibe " + dañoReal + " puntos de daño.");
        if (salud <= 0) {
            salud = 0;
            System.out.println(nombre + " ha sido derrotado.");
        }
    }

    public void proteger(int cantidad) {
        resistenciaTemporal += cantidad;
        System.out.println(nombre + " obtiene " + cantidad + " puntos de resistencia temporal.");
    }

    public void recuperarSalud(int cantidad) {
        int anterior = salud;
        salud = Math.min(salud + cantidad, maxSalud);
        System.out.println(nombre + " recupera " + (salud - anterior) + " puntos de salud.");
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public void mostrarInfo() {
        System.out.println("Personaje: " + nombre + " | Salud: " + salud + "/" + maxSalud + " | Nivel: " + nivel);
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getNivel() {
        return nivel;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
