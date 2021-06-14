package src;

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
    // nota: peliculas y prestamo peliculas comparten indice de sus arreglos
    // peliculas
    int[] idPeli = new int[30];
    String[] nombrePeli = new String[30];
    int[] año = new int[30];
    String[] categoria = new String[30];
    boolean[] disponible = new boolean[30];
    int[] contadorPrestado = new int[30];

    // prestamo peliculas
    int[] idPeliPrestada = new int[30];
    int[] idCliente = new int[30];
    int[] diasPrestado = new int[30];

    // para reportes
    String[] nombreCategoria = new String[30];
    int[] cantidadPeliPorCategoria = new int[30];

    public void prestamoPelicula() {
        String desicion;
        System.out.println("====================== PRÉSTAMO DE PELÍCULAS ======================");
        System.out.println("Rexglas para prestar películas:");
        System.out.println("1. La película debe de estar disponibles");
        System.out.println("2. El cliente solo puede prestar una película a la vez");
        System.out.print("Acepta las reglas s(si) o n(no): ");
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
        System.out.println("# | Nombre película | Categoria | año | id ");

        int contador = 0;
        while ((nombrePeli[contador] != null) && contador < 30) {
            if (disponible[contador]) {
                System.out.println((contador + 1) + " | " + nombrePeli[contador] + "  |  " + categoria[contador]
                        + "  |  " + año[contador] + "  |  " + idPeli[contador]);
            }
            contador++;
        }

        if (contador == 0) {
            System.out.println("\nno hay ningúna película disponible.");
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
            System.out.println("Se ha prestado la película satisfactoriamente.");
            disponible[num] = false; // cambio de estado de la peli
            idPeliPrestada[num] = idPeli[num];
            idCliente[num] = idUsuario;
            diasPrestado[num] = dias;
            contadorPrestado[num] += 1;
            posUsuarioEncontrado--;// indice del usuario encontrado
            peliPrestada[posUsuarioEncontrado] = true;

        } else {
            System.out.println("El id del usuario no ha sido encontrado y no se pudo prestar la pelicula\n");
        }
    }

    public void devolucionPeli() {
        System.out.println("==================== DEVOLUCIÓN DE PELÍCULAS ====================\n");
        System.out.println("Las películas prestadas son:");
        System.out.println("# | Nombre película  | Nombre de cliente");

        boolean hayPeli = false;
        for (int i = 0; i < 30; i++) {
            // imprimir peliculas prestadas datos
            if (idPeliPrestada[i] != 0) {
                hayPeli = true;
                // para encontrar el nombre del cliente que presto la pelicula
                int indiceUsuario = 0;
                boolean usuarioNombre = false;
                do {
                    if (idCliente[i] == id[indiceUsuario]) {
                        usuarioNombre = true;
                    } else {
                        indiceUsuario++;
                    }
                } while (!usuarioNombre);

                System.out.println((i + 1) + " | " + nombrePeli[i] + "    |    " + nombre[indiceUsuario] + "");
            }
        }

        if (hayPeli == false) {
            System.out.println("No hay ninguna película a devolver.");
            return;
        }

        System.out.print("\nIngrese el número de la película que desea devolver: ");
        int num = entrada.nextInt();
        num--; // indice de la película a devolver
        // para encontra al cliente y cambiar su estado
        boolean cambioEstadoCliente = false;
        int usuarioIndice = 0;
        while (!cambioEstadoCliente) {
            if (idCliente[num] == id[usuarioIndice]) {
                cambioEstadoCliente = true;
            } else {
                usuarioIndice++;
            }
        }
        disponible[num] = true;// cambio de estado de la peli
        peliPrestada[usuarioIndice] = false;// cambio de estado del usuario
        idPeliPrestada[num] = 0;
        idCliente[num] = 0;
        diasPrestado[num] = 0;
        System.out.println("Ha devuelto la película satisfactoriamente.\n");
    }

    public void mostrarPeliculas() {
        System.out.println("\n==================== DATOS DE LAS PELÍCULAS ====================");
        System.out.println("# | Nombre | año |  Categoría  | id película | Disponible");
        int i = 0; // contador
        while (nombrePeli[i] != null) {
            System.out.println((i + 1) + " | " + nombrePeli[i] + " | " + año[i] + " | " + categoria[i] + " | "
                    + idPeli[i] + " | " + disponible[i]);
            i++;
        }
    }

    public void ingresoPeliculas() {
        String nombre;
        int idPelicula;
        int añoPelicula;
        String categoriaPelicula;

        System.out.println("\n==================== INGRESO DE PELÍCULAS ====================");
        // buscamos en que posición vamos a ubicar la película a ingresar
        int posicionUbicar = 0;
        while (posicionUbicar < 30 && nombrePeli[posicionUbicar] != null) {
            posicionUbicar++;
        }
        if (posicionUbicar == 30) {
            System.out.println("¡Ya no hay espacio para poder ingresar la películas!");
            return;
        }
        entrada.nextLine();
        System.out.print("Ingrese nombre de la películas: ");
        nombre = entrada.nextLine();
        System.out.print("Ingrese la categoria de la película: ");
        categoriaPelicula = entrada.nextLine();
        System.out.print("Ingrese el año de la película: ");
        añoPelicula = entrada.nextInt();
        System.out.print("Ingrese un id para la película: ");
        idPelicula = entrada.nextInt();

        // comprobamos que el id se diferente a las peliculas existentes
        boolean idRepetido = false;
        do {
            if (idRepetido) {
                System.out.print("¡ERROR! el id ya existe ingrese otro: ");
                idPelicula = entrada.nextInt();
            }
            idRepetido = comprobarIdPeliIngresada(idPelicula, posicionUbicar);
        } while (idRepetido);

        nombrePeli[posicionUbicar] = nombre;
        categoria[posicionUbicar] = categoriaPelicula;
        año[posicionUbicar] = añoPelicula;
        idPeli[posicionUbicar] = idPelicula;
        disponible[posicionUbicar] = true;
        // para reportes
        int i = 0;
        do {
            if (categoriaPelicula == nombreCategoria[i]) {
                cantidadPeliPorCategoria[i] += 1;
                break;
            } else if (nombreCategoria[i] == null) {
                nombreCategoria[i] = categoriaPelicula;
                cantidadPeliPorCategoria[i] += 1;
                break;
            }
            i++;
        } while (nombreCategoria[i] != categoriaPelicula && i < 30);
        System.out.println("Se ha gurado la película con exito.");
    }

    public boolean comprobarIdPeliIngresada(int id, int posicionPeliIngresada) {
        boolean idRepetido = false;
        for (int i = 0; i < posicionPeliIngresada; i++) {
            if (idPeli[i] == id) {
                idRepetido = true;
                break;
            }
        }
        return idRepetido;
    }

    public void ordenarPeliculas() {
        System.out.println("\n==================== ORDENAR PELÍCULAS ====================");
        String nomAux;
        int idAux;
        int añoAux;
        String categAux;
        boolean dispoAux;
        int contAux;
        int idPeliPrestadaAux;
        int idClienteAux;
        int diasAux;

        for (int i = 0; i < 29; i++) {
            if (nombrePeli[i] == null) {
                break;
            }
            for (int j = 0; j < 29; j++) {
                if (nombrePeli[j] == null || nombrePeli[j + 1] == null) {
                    break;
                } else {
                    if (nombrePeli[j].compareToIgnoreCase(nombrePeli[j + 1]) > 0) {
                        nomAux = nombrePeli[j];
                        idAux = idPeli[j];
                        añoAux = año[j];
                        categAux = categoria[j];
                        dispoAux = disponible[j];
                        contAux = contadorPrestado[j];
                        idPeliPrestadaAux = idPeliPrestada[j];
                        idClienteAux = idCliente[j];
                        diasAux = diasPrestado[j];

                        nombrePeli[j] = nombrePeli[j + 1];
                        idPeli[j] = idPeli[j + 1];
                        año[j] = año[j + 1];
                        categoria[j] = categoria[j + 1];
                        disponible[j] = disponible[j + 1];
                        contadorPrestado[j] = contadorPrestado[j + 1];
                        idPeliPrestada[j] = idPeliPrestada[j + 1];
                        idCliente[j] = idCliente[j + 1];
                        diasPrestado[j] = diasPrestado[j + 1];

                        nombrePeli[j + 1] = nomAux;
                        idPeli[j + 1] = idAux;
                        año[j + 1] = añoAux;
                        categoria[j + 1] = categAux;
                        disponible[j + 1] = dispoAux;
                        contadorPrestado[j + 1] = contAux;
                        idPeliPrestada[j + 1] = idPeliPrestadaAux;
                        idCliente[j + 1] = idClienteAux;
                        diasPrestado[j + 1] = diasAux;
                    }
                }
            }
        }
        System.out.println("Las películas se han ordenado en orden alfabético");
    }

    public void ingresarCliente() {
        String nombreUsuario;
        String numTelefono;
        int idUsuario;
        String desicion; // si se desea ingresar otro clente
        System.out.println("\n==================== INGRESAR CLIENTES NUEVOS ====================");
        do {
            entrada.nextLine();
            System.out.print("Ingrese nombre del cliente: ");
            nombreUsuario = entrada.nextLine();
            System.out.print("Ingrese número de telefono del cliente: ");
            numTelefono = entrada.nextLine();
            System.out.print("Ingrese un id para el cliente: ");
            idUsuario = entrada.nextInt();
            // comprobamos que el id no exista en los clientes
            boolean idRepetido = false;
            do {
                if (idRepetido) {
                    System.out.print("¡ERROR! el id ya existe ingrese otro: ");
                    idUsuario = entrada.nextInt();
                }
                idRepetido = comprobarIdUsuario(idUsuario);
            } while (idRepetido);

            int ubicarUsuario = 0;
            while (nombre[ubicarUsuario] != null && ubicarUsuario < 30) {
                ubicarUsuario++;
            }
            // guardamos los datos del usuario
            nombre[ubicarUsuario] = nombreUsuario;
            telefono[ubicarUsuario] = numTelefono;
            id[ubicarUsuario] = idUsuario;
            System.out.println("El cliente se ha ingresado satisfactoriamente.");
            System.out.print("Desea ingresar otro usuario s(si) o n(no): ");
            entrada.nextLine();
            desicion = entrada.nextLine();
        } while (desicion.equalsIgnoreCase("s"));
    }

    public boolean comprobarIdUsuario(int idUsuario) {
        boolean idRepetido = false;
        int contador = 0;
        while (id[contador] != 0 && contador < 30) {
            if (idUsuario == id[contador]) {
                idRepetido = true;
                break;
            }
            contador++;
        }
        return idRepetido;
    }

    public void mostrarClientes() {
        System.out.println("\n==================== DATOS DE LOS CLIENTES ====================");
        System.out.println("# |  Nombre  | Teléfono | id | Película prestada");

        for (int i = 0; i < 30; i++) {
            if (nombre[i] != null) {
                System.out.println(
                        (i + 1) + " |  " + nombre[i] + "  | " + telefono[i] + " | " + id[i] + " | " + peliPrestada[i]);
            }
        }
    }

    public void reportes() {
        int decision;
        System.out.println("\n==================== REPORTES ====================");
        System.out.println("1 Para cantidad de películas por categoria");
        System.out.println("2 Para peliculas de una categoría en específico");
        System.out.println("3 Para películas y la cantidad de veces que se han prestado");
        System.out.println("4 Para película más prestada");
        System.out.println("5 Para pe{ícula menos prestada");
        System.out.print("ingrese la opción que desea: ");
        decision = entrada.nextInt();
        switch (decision) {
            case 1:
                System.out.println("Categoría | Cantidad de Películas");
                for (int i = 0; i < 30; i++) {
                    if (nombreCategoria[i] != null) {
                        System.out.println(nombreCategoria[i] + " | " + cantidadPeliPorCategoria[i]);
                    } else {
                        break;
                    }
                }
                break;

            case 2:
                String categoriaDeseada;
                System.out.print("Ingrese la categoría que desea: ");
                categoriaDeseada = entrada.nextLine();
                System.out.println("Películas en la categoria " + categoriaDeseada + ":");
                for (int i = 0; i < 30; i++) {
                    if (categoria[i] == null) {
                        break;
                    }

                    if (categoria[i].equalsIgnoreCase(categoriaDeseada)) {
                        System.out.println(nombrePeli[i]);
                    }
                }
                break;

            case 3:
                System.out.println("Película | Cantidad de veces prestada");
                for (int i = 0; i < 30; i++) {
                    if (nombrePeli[i] == null) {
                        break;
                    } else {
                        System.out.println(nombrePeli[i] + " | " + contadorPrestado[i]);
                    }
                }
                break;

            case 4:
                int cantidadPrestada = 0;
                int indiceMasPrestada = 0;
                for (int i = 0; i < 30; i++) {
                    if (nombrePeli[0] == null) {
                        System.out.println("No hay ningúna película");
                        return;
                    } else if (nombrePeli[i] == null) {
                        break;
                    }
                    if (contadorPrestado[i] > cantidadPrestada) {
                        cantidadPrestada = contadorPrestado[i];
                        indiceMasPrestada = i;
                    }
                }
                System.out.println("Película más prestada de la tienda.");
                System.out.println("¡" + nombrePeli[indiceMasPrestada] + "! cantidad de veces prestada ¡"
                        + cantidadPrestada + "!");
                break;

            case 5:
                int cantPrestada = 0;
                int indiceMenosPrestada = 0;
                for (int i = 0; i < 30; i++) {
                    if (nombrePeli[0] == null) {
                        System.out.println("No hay ningúna película");
                        return;
                    } else if (nombrePeli[i] == null) {
                        break;
                    }
                    if (contadorPrestado[i] < cantPrestada) {
                        cantPrestada = contadorPrestado[i];
                        indiceMenosPrestada = i;
                    }
                }
                System.out.println("Película menos prestada de la tienda.");
                System.out.println(
                        "¡" + nombrePeli[indiceMenosPrestada] + "! cantidad de veces prestada ¡" + cantPrestada + "!");

                break;
            default:
                System.out.println("No ha ingresado algúna opción valida");
                break;
        }
    }

    public void menu() {
        int desicion;
        System.out.println("\n==========°°°°° ¡BIENVENIDO A ALQUILER DE PELICULAS ''MEMORABILIA''! °°°°°==========");
        do {
            System.out.println("\n<==================== Menú ====================>");
            System.out.println("1 Para préstamo de películas.");
            System.out.println("2 Para devolución de películas.");
            System.out.println("3 Para mostrar películas.");
            System.out.println("4 Para ingreso de películas.");
            System.out.println("5 Para ordenar películas.");
            System.out.println("6 Para ingresar clientes nuevos.");
            System.out.println("7 Para mostrar Clientes.");
            System.out.println("8 Para reportes");
            System.out.println("9 Para salir.");
            System.out.print("Ingrese el número de la opción que desea: ");
            desicion = entrada.nextInt();

            switch (desicion) {
                case 1:
                    prestamoPelicula();
                    break;
                case 2:
                    devolucionPeli();
                    break;
                case 3:
                    mostrarPeliculas();
                    break;
                case 4:
                    ingresoPeliculas();
                    break;
                case 5:
                    ordenarPeliculas();
                    break;
                case 6:
                    ingresarCliente();
                    break;
                case 7:
                    mostrarClientes();
                    break;
                case 8:
                    reportes();
                    break;
                case 9:
                    System.out.println("Ha decidido salir del programa ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("No ha ingresado una opción valida.");
                    break;
            }

        } while (desicion != 9);
    }
}