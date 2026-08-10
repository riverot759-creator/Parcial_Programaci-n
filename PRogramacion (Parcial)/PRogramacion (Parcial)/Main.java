import java.util.Scanner;

public class Main {
    // Esta clase controla el juego: el menú, crear personajes y el combate.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Guerrero guerrero = null;
        Mago mago = null;

        while (true) {
            System.out.println("Bienvenido al juego de rol");
            System.out.println("1. Crear guerrero");
            System.out.println("2. Crear mago");
            System.out.println("3. Entrar en combate");
            System.out.println("4. Equipar mascota");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); 

            if (opcion == 1) {
                guerrero = crearGuerrero(sc);
            } else if (opcion == 2) {
                mago = crearMago(sc);
            } else if (opcion == 3) {
                if (guerrero == null && mago == null) {
                    System.out.println("Debes crear un personaje primero.");
                } else {
                    iniciarCombate(sc, guerrero, mago);
                }
            } else if (opcion == 4) {
                if (guerrero == null) {
                    System.out.println("Necesitas crear un guerrero antes.");
                } else {
                    Mascota mascota = crearMascota(sc);
                    guerrero.setMascota(mascota);
                    System.out.println("Mascota equipada: " + mascota.getNombre());
                }
            } else if (opcion == 5) {
                System.out.println("Saliendo del juego...");
                break;
            } else {
                System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }

    // Crear un guerrero con vida fija y algunos datos básicos.
    private static Guerrero crearGuerrero(Scanner sc) {
        System.out.print("Nombre del guerrero: ");
        String nombre = sc.nextLine();
        System.out.print("Fuerza: ");
        int fuerza = sc.nextInt();
        sc.nextLine();
        System.out.print("Nivel: ");
        int nivel = sc.nextInt();
        sc.nextLine();
        System.out.print("Defensa: ");
        int defensa = sc.nextInt();
        sc.nextLine();
        System.out.print("Stamina: ");
        int stamina = sc.nextInt();
        sc.nextLine();

        Guerrero guerrero = new Guerrero(nombre, 100, fuerza, nivel, defensa, stamina);
        guerrero.getInventario().agregarItem(new ObjetosMagicos("Poción de salud", "Salud", 20));
        guerrero.getInventario().agregarItem(new ObjetosMagicos("Tónico de stamina", "Stamina", 6));
        System.out.println("Guerrero creado con 100 de salud.");
        return guerrero;
    }

    // Crear un mago con mana e inteligencia.
    private static Mago crearMago(Scanner sc) {
        System.out.print("Nombre del mago: ");
        String nombre = sc.nextLine();
        System.out.print("Mana: ");
        int mana = sc.nextInt();
        sc.nextLine();
        System.out.print("Inteligencia: ");
        int inteligencia = sc.nextInt();
        sc.nextLine();
        System.out.print("Nivel: ");
        int nivel = sc.nextInt();
        sc.nextLine();

        Mago mago = new Mago(nombre, 100, mana, inteligencia, nivel);
        mago.getInventario().agregarItem(new ObjetosMagicos("Poción de salud", "Salud", 18));
        mago.getInventario().agregarItem(new ObjetosMagicos("Poción de mana", "Mana", 10));
        System.out.println("Mago creado.");
        return mago;
    }

    private static Mascota crearMascota(Scanner sc) {
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = sc.nextLine();
        System.out.print("Lealtad: ");
        int lealtad = sc.nextInt();
        sc.nextLine();
        return new Mascota(nombreMascota, lealtad);
    }

    private static Personaje elegirPersonaje(Scanner sc, Guerrero guerrero, Mago mago) {
        System.out.println("Elige tu personaje:");
        if (guerrero != null) {
            System.out.println("1. Guerrero");
        }
        if (mago != null) {
            System.out.println("2. Mago");
        }
        System.out.print("Elección: ");
        String eleccion = sc.nextLine();

        if (eleccion.equals("1") && guerrero != null) {
            return guerrero;
        } else if (eleccion.equals("2") && mago != null) {
            return mago;
        } else {
            System.out.println("Opción inválida.");
            return null;
        }
    }

    private static void iniciarCombate(Scanner sc, Guerrero guerrero, Mago mago) {
        Personaje jugador = elegirPersonaje(sc, guerrero, mago);
        if (jugador == null) {
            return;
        } 

        Enemigo enemigo = new Enemigo("Orko", 120, 6, 2, 10);
        System.out.println("Comienza el combate contra " + enemigo.getNombre() + "!");

        while (jugador.estaVivo() && enemigo.estaVivo()) {
            System.out.println("Turno del jugador. Elige una acción:");
            System.out.println("1. Atacar / lanzar hechizo");
            System.out.println("2. Usar item");
            System.out.println("3. Recuperar energía");
            System.out.print("Opción: ");
            String accion = sc.nextLine();

            if (accion.equals("2")) {
                jugador.getInventario().mostrarItems();
                System.out.print("Escribe el nombre del item a usar: ");
                String item = sc.nextLine();
                jugador.getInventario().usarItem(item, jugador);
            } else if (accion.equals("3")) {
                if (jugador instanceof Guerrero) {
                    ((Guerrero) jugador).recargarStamina(5);
                } else if (jugador instanceof Mago) {
                    ((Mago) jugador).recargarMana(5);
                }
            } else {
                if (jugador instanceof Guerrero) {
                    Guerrero g = (Guerrero) jugador;
                    g.mostrarAtaques();
                    System.out.print("Escribe el ataque: ");
                    String ataque = sc.nextLine();
                    g.usarAtaque(ataque, enemigo);
                } else {
                    Mago m = (Mago) jugador;
                    m.mostrarHechizos();
                    System.out.print("Escribe el hechizo: ");
                    String hechizo = sc.nextLine();
                    m.lanzarHechizo(hechizo, enemigo);
                }
            }

            if (!enemigo.estaVivo()) {
                System.out.println("Has derrotado al orko.");
                break;
            }

            int dañoEnemigo = enemigo.atacar();
            jugador.recibirDaño(dañoEnemigo);

            if (jugador instanceof Guerrero) {
                ((Guerrero) jugador).recargarStamina(2);
            } else if (jugador instanceof Mago) {
                ((Mago) jugador).recargarMana(2);
            }

            System.out.println("Estado actual:");
            jugador.mostrarInfo();
            System.out.println(enemigo.getNombre() + " | Salud: " + enemigo.getSalud());
        }
    }
}
