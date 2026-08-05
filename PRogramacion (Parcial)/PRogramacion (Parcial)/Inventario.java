import java.util.ArrayList;

public class Inventario {
    // Esta clase guarda los objetos mágicos que tiene el personaje.
    // Sirve como una mochila donde se pueden poner y usar items.
    private int capacidad;
    private ArrayList<ObjetosMagicos> items;

    public Inventario(int capacidad) {
        this.capacidad = capacidad;
        this.items = new ArrayList<>();
    }

    public void agregarItem(ObjetosMagicos item) {
        if (items.size() < capacidad) {
            items.add(item);
        } else {
            System.out.println("Inventario lleno.");
        }
    }

    public void mostrarItems() {
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("Objetos en el inventario:");
        for (ObjetosMagicos item : items) {
            System.out.println("- " + item.getNombre() + " (" + item.getTipo() + ", " + item.getPotencia() + ")");
        }
    }

    public boolean usarItem(String nombre, Personaje objetivo) {
    String buscado = normalizar(nombre);

    for (int i = 0; i < items.size(); i++) {
        ObjetosMagicos item = items.get(i);
        if (!normalizar(item.getNombre()).contains(buscado)) {
            continue;
        }

        if (!aplicarEfecto(item, objetivo)) {
            System.out.println("No puedes usar ese item.");
            return false;
        }

        System.out.println("Has usado: " + item.getNombre());
        items.remove(i);
        return true;
    }

    System.out.println("Item no encontrado.");
    return false;
}

private boolean aplicarEfecto(ObjetosMagicos item, Personaje objetivo) {
    String tipo = item.getTipo().toLowerCase();
    switch (tipo) {
        case "salud":
            objetivo.recuperarSalud(item.getPotencia());
            return true;
        case "mana":
            if (objetivo instanceof Mago) {
                ((Mago) objetivo).recargarMana(item.getPotencia());
                return true;
            }
            break;
        case "stamina":
            if (objetivo instanceof Guerrero) {
                ((Guerrero) objetivo).recargarStamina(item.getPotencia());
                return true;
            }
            break;
        case "resistencia":
            objetivo.proteger(item.getPotencia());
            return true;
    }
    return false;
}

    private String normalizar(String texto) {
        return texto.toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u")
                .trim();
    }
}
