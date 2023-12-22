package com.example.ejercicio1.ejercicio1;

import java.util.Scanner;

import com.example.ejercicio1.ejercicio1.Ejercicio1;
import com.example.ejercicio1.Tarjeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ejercicio1 {
    public static void main(String[] args) {
        SpringApplication.run(Ejercicio1.class, args);
        Scanner scanner = new Scanner(System.in);

        Tarjeta tarjeta1 = new Tarjeta("VISA", "4420808023851295", "Bruno Siafas", "04/23");
        Tarjeta tarjeta2 = new Tarjeta("NARA", "1234567890123456", "Pedro Sara", "12/24");
        Tarjeta tarjeta3 = new Tarjeta("AMEX", "9876543210987654", "Ruben Sosa", "06/25");

        Tarjeta[] tarjetasRegistradas = {tarjeta1, tarjeta2, tarjeta3};

        System.out.println("Seleccione una tarjeta para operar:");
        System.out.println("1. Tarjeta VISA");
        System.out.println("2. Tarjeta NARA");
        System.out.println("3. Tarjeta AMEX");

        int opcionTarjeta = obtenerOpcionTarjeta(scanner);

        Tarjeta tarjetaUsuario;
        switch (opcionTarjeta) {
            case 1:
                tarjetaUsuario = tarjeta1;
                break;
            case 2:
                tarjetaUsuario = tarjeta2;
                break;
            case 3:
                tarjetaUsuario = tarjeta3;
                break;
            default:
                System.err.println("Opción de tarjeta no válida. Saliendo del programa.");
                return;
        }

        try {
            int opcion;
            do {
                mostrarMenu(scanner);
                opcion = obtenerOpcion(scanner);

                switch (opcion) {
                    case 1:
                        tarjetaUsuario.mostrarInformacion();
                        break;
                    case 2:
                        tarjetaUsuario.mostrarOperacionValida(scanner);
                        break;
                    case 3:
                        tarjetaUsuario.mostrarTarjetaValida();
                        break;
                    case 4:
                        mostrarTarjetasDistintas(tarjetaUsuario, tarjetasRegistradas, scanner);
                        break;
                    case 5:
                        tarjetaUsuario.mostrarTasaOperacion(scanner);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa..");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }

                System.out.println("Presione la tecla Enter para continuar...");
                scanner.nextLine();
                scanner.nextLine();

            } while (opcion != 0);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static int obtenerOpcionTarjeta(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el número de la tarjeta deseada: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Opción no válida. Intente nuevamente.");
                scanner.next();
                System.out.print("Ingrese el número de la tarjeta deseada: ");
            }
            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 3) {
                return opcion;
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarTarjetasDistintas(Tarjeta tarjetaUsuario, Tarjeta[] tarjetasRegistradas, Scanner scanner) {
        int opcion;
        try {
            do {
                System.out.println("Seleccione una tarjeta para comparar:");
                for (int i = 0; i < tarjetasRegistradas.length; i++) {
                    System.out.println((i + 1) + ". " + tarjetasRegistradas[i].obtenerInformacion());
                }
    
                opcion = obtenerOpcionTarjeta(scanner);
            } while (opcion < 1 || opcion > tarjetasRegistradas.length);
    
            Tarjeta tarjetaAComparar = tarjetasRegistradas[opcion - 1];
    
            boolean tarjetasDistintas = tarjetaUsuario.esDistintaA(tarjetaAComparar);
            if (tarjetasDistintas) {
                System.out.println("¿La tarjeta ingresada es distinta a la tarjeta seleccionada? " + "Si, las tarjetas son distintas.");
            } else {
                System.out.println("¿La tarjeta ingresada es distinta a la tarjeta seleccionada? " + "No, las tarjetas son iguales.");
            }
        } finally {
        }
    }

    private static void mostrarMenu(Scanner scanner) {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Mostrar Información de la Tarjeta");
        System.out.println("2. Mostrar si la Operación es Válida");
        System.out.println("3. Mostrar si la Tarjeta es Válida para Operar");
        System.out.println("4. Mostrar si la Tarjeta es Distinta a Otra");
        System.out.println("5. Mostrar Tasa de Operación");
        System.out.println("0. Salir");
        System.out.println();
    
        System.out.print("Ingrese el número de la opción deseada: ");
    }

    private static int obtenerOpcion(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el número de la opción deseada: ");
            try {
                int opcion = Integer.parseInt(scanner.next());
                return opcion;
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
