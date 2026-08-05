import java.util.ArrayList;

public class Mago extends Personaje {
    // Clase del mago: usa mana y hechizos para atacar o curarse.
    private int mana;
    private int maxMana;
    private int inteligencia;
    private ArrayList<Hechizo> hechizos;

    public Mago(String nombre, int salud, int mana, int inteligencia, int nivel) {
        super(nombre, salud, nivel);
        this.mana = mana;
        this.maxMana = mana;
        this.inteligencia = inteligencia;
        this.hechizos = new ArrayList<>();

        hechizos.add(new Hechizo("Bola de fuego", 7, 10));
        hechizos.add(new Hechizo("Curacion", 5, 6));
        hechizos.add(new Hechizo("Escudo", 4, 5));
    }

    public void mostrarHechizos() {
        System.out.println("Hechizos del mago " + getNombre() + ":");
        for (Hechizo hechizo : hechizos) {
            hechizo.mostrarHabilidades();
        }
        System.out.println("Mana: " + mana + "/" + maxMana);
    }

    public void lanzarHechizo(String nombreHechizo, Personaje objetivo) {
        String buscado = nombreHechizo.toLowerCase();
        for (Hechizo hechizo : hechizos) {
            String nombre = hechizo.getNombre().toLowerCase();
            if (nombre.contains(buscado) || buscado.contains(nombre)) {
                if (mana < hechizo.getCostoMana()) {
                    System.out.println("No tienes suficiente mana para lanzar " + hechizo.getNombre());
                    return;
                }
                mana -= hechizo.getCostoMana();
                if (nombre.contains("curacion")) {
                    int curacion = hechizo.getPotencia() * 4 + inteligencia;
                    recuperarSalud(curacion);
                    System.out.println(getNombre() + " lanza " + hechizo.getNombre() + " y recupera " + curacion + " puntos de salud.");
                } else if (nombre.contains("escudo")) {
                    int buff = hechizo.getPotencia() + inteligencia / 2;
                    proteger(buff);
                    System.out.println(getNombre() + " lanza " + hechizo.getNombre() + " y gana " + buff + " de resistencia temporal.");
                } else {
                    int daño = hechizo.getPotencia() + inteligencia;
                    System.out.println(getNombre() + " lanza " + hechizo.getNombre() + " y causa " + daño + " puntos de daño.");
                    objetivo.recibirDaño(daño);
                }
                return;
            }
        }
        System.out.println("Hechizo no encontrado.");
    }

    public void recargarMana(int cantidad) {
        int anterior = mana;
        mana = Math.min(mana + cantidad, maxMana);
        System.out.println(getNombre() + " recupera " + (mana - anterior) + " puntos de mana.");
    }

    
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Inteligencia: " + inteligencia + " | Mana: " + mana + "/" + maxMana);
    }
}
