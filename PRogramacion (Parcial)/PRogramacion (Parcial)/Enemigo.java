public class Enemigo extends Personaje {
    // Clase del enemigo que pelea contra el jugador.
    private int defensa;
    private int danoFijo;

    public Enemigo(String nombre, int salud, int defensa, int nivel, int danoFijo) {
        super(nombre, salud, nivel);
        this.defensa = defensa;
        this.danoFijo = danoFijo;
    }

    public int atacar() {
        // El daño fijo que hace el enemigo
        int daño = danoFijo + getNivel();
        System.out.println(getNombre() + " ataca y causa " + daño + " puntos de daño.");
        return daño;
    }

    @Override
    public void recibirDaño(int daño) {
        int dañoReal = daño - defensa;
        if (dañoReal < 0) {
            dañoReal = 0;
        }
        super.recibirDaño(dañoReal);
    }
}
