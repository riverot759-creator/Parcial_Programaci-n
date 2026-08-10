import java.util.ArrayList;

public class Guerrero extends Personaje {
    // Clase del guerrero: usa ataques con stamina y tiene defensa.
    private int fuerza;
    private int defensa;
    private int stamina;
    private int maxStamina;
    private ArrayList<Ataque> ataques;
    private Mascota mascota;

    public Guerrero(String nombre, int salud, int fuerza, int nivel, int defensa, int stamina) {
        super(nombre, salud, nivel);
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.stamina = stamina;
        this.maxStamina = 100;
        this.ataques = new ArrayList<>();
        this.mascota = null;

        ataques.add(new Ataque("Golpe", 8, 3));
        ataques.add(new Ataque("Embate", 10, 5));
    }

    public void mostrarAtaques() {
        System.out.println("Ataques del guerrero " + getNombre() + ":");
        for (Ataque ataque : ataques) {
            ataque.mostrarHabilidad();
        }
        System.out.println("Stamina: " + stamina + "/" + maxStamina);
    }

    public void usarAtaque(String nombreAtaque, Personaje objetivo) {
        for (Ataque ataque : ataques) {
            if (ataque.getNombre().equalsIgnoreCase(nombreAtaque)) {
                if (stamina < ataque.getCostoStamina()) {
                    System.out.println("No tienes suficiente stamina para usar " + ataque.getNombre());
                    return;
                }
                stamina -= ataque.getCostoStamina();
                int daño = ataque.getPoder() + fuerza + getNivel();
                System.out.println(getNombre() + " usa " + ataque.getNombre() + " y causa " + daño + " puntos de daño.");
                objetivo.recibirDaño(daño);
                return;
            }
        }
        System.out.println("Ataque no encontrado.");
    }

    @Override
    public void recibirDaño(int daño) {
        int dañoReal = daño - defensa;
        if (dañoReal < 0) {
            dañoReal = 0;
        }
        super.recibirDaño(dañoReal);
    }

    public void recargarStamina(int cantidad) {
        int anterior = stamina;
        stamina = Math.min(stamina + cantidad, maxStamina);
        System.out.println(getNombre() + " recupera " + (stamina - anterior) + " puntos de stamina.");
    }

    public void regenerarStamina() {
        if (stamina < maxStamina) {
            recargarStamina(2);
        }
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Fuerza: " + fuerza + " | Defensa: " + defensa + " | Stamina: " + stamina + "/" + maxStamina);
        if (mascota != null) {
            System.out.println("Mascota equipada: " + mascota.getNombre() + " (Lealtad " + mascota.getLealtad() + ")");
        }
    }
}
