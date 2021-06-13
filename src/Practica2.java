package src;

import java.sql.Struct;
import java.util.Scanner;

public class Practica2 {
    public static void main(String[] args) {
        Practica2 Memorabilia = new Practica2();
    }

    public Practica2() {
        menu();
    }

    Scanner entrada = new Scanner(System.in);

    // Cliente
    String[] nombre = new String[30];
    int[] id = new int[30];
    String[] telefono = new String[30];
    boolean[] peliPrestada = new boolean[30];

    // peliculas
    int[] idPeli = new int[30];
    String[] nombrePeli = new String[30];
    int[] año = new int[30];
    String[] categoria = new String[30];
    boolean[] disponible = new boolean[30];

    // prestamo peliculas
    int[] idPeliPrestada = new int[30];
    int[] idCliente = new int[30];
    int[] diasPrestado = new int[30];
    int[] contadorPrestado = new int[30];

    public void prestamoPelicula() {
        String desicion;
        System.out.println("====================== PRÉSTAMO DE PELÍCULAS ======================");
        System.out.println("Reglas para prestar películas:");
        System.out.println("1. La película debe de estar disponibles");
        System.out.println("2. El cliente solo puede prestar una película a la vez");
        System.out.print("Asepta las reglas s(si) o n(no): ");
        desicion = entrada.nextLine();

        // comprobamos que haya ingresado una opción valida
        while (!desicion.equalsIgnoreCase("S") && !desicion.equalsIgnoreCase("n")) {
            System.out.println("Ha ingresado una opción incorrecta vuela a ingrear una opción");
            desicion = entrada.nextLine();
        }

        if (desicion.equalsIgnoreCase("n")) {
            System.out.println("No ha aseptado las reglas.");
            return;
        }

        System.out.println("\nLas películas disponibles son las siguientes: ");
        System.out.println("# |  Nombre película   |   Categoria    |  año |  id  ");

        int contador = 0;
        while ((nombrePeli[contador] != null) && contador < 30) {
            if (disponible[contador]) {
                System.out.println((contador + 1) + " | " + nombrePeli[contador] + "  |  " + categoria[contador]
                        + "  |  " + año[contador] + "  |  " + idPeli[contador]);
            }
            contador++;
        }

        if (contador == 0) {
            System.out.println("no hay ningúna película disponible.");
            return;
        }

        System.out.print("Desea prestar alguna película s(si) o n(no): ");
        desicion = entrada.nextLine();

        // comprobamos que haya ingresado una opción valida
        while (!desicion.equalsIgnoreCase("S") && !desicion.equalsIgnoreCase("n")) {
            System.out.print("Ha ingresado una opción incorrecta vuela a ingrear una opción: ");
            desicion = entrada.nextLine();
        }

        if (desicion.equalsIgnoreCase("n")) {
            System.out.println("No presto ningúna película");
            return;
        }

        System.out.print("Ingrese el número de la película que desea: ");
        int num = entrada.nextInt();

        num--; // indice donde se encuentra la pelicula en sus arreglos.

        System.out.print("Cuantos dias va a prestar la película: ");
        int dias = entrada.nextInt();

        System.out.print("Ingrese el id del cliente: ");
        int idUsuario = entrada.nextInt();

        boolean usuarioEncontrado = false;
        int posUsuarioEncontrado = 0;
        // para encotrar a el usuario en el arreglo id
        while (!usuarioEncontrado && posUsuarioEncontrado < 30) {
            if (idUsuario == id[posUsuarioEncontrado]) {
                usuarioEncontrado = true;
            }
            posUsuarioEncontrado++;
        }
        // si se encontro o no el usuario
        if (usuarioEncontrado) {
            disponible[num] = false; // cambio de estado de la peli
            idPeliPrestada[num] = idPeli[num];
            idCliente[num] = idUsuario;
            diasPrestado[num] = dias;
            contadorPrestado[num] += 1;
            posUsuarioEncontrado--;// indice del usuario encontrado
            peliPrestada[posUsuarioEncontrado] = true;

        } else {
            System.out.println("El id del usuario no ha sido encontrado y no se pudo prestar la pelicula");
        }
    }

    public void devolucionPeli(){
        System.out.println("==================== DEVOLUCIÓN DE PELÍCULAS ====================\n");
        System.out.println("Las películas prestadas son:");
        System.out.println("# |  Nombre película   |    Nombre de cliente");

        for(int i=0; i<30; i++){
            if(!disponible[i])
            System.out.println((i+1)+" | "+ nombrePeli[i]+"  |  "+);
        }
    }

    public void menu() {

    }
}