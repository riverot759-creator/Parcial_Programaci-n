public class Elemental {
    // Clase simple para describir un elemento mágico.
    private String elemento;
    private String efecto;

    public Elemental(String elemento, String efecto) {
        this.elemento = elemento;
        this.efecto = efecto;
    }

    public String getElemento() {
        return elemento;
    }

    public String getEfecto() {
        return efecto;
    }
}
