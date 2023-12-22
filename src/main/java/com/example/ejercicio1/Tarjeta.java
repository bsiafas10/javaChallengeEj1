package com.example.ejercicio1;

import java.time.LocalDate;
import java.util.Scanner;
import com.example.ejercicio1.tarjetas.MarcaTarjeta;

public class Tarjeta {
    private String marca;
    private String numero;
    private String cardholder;
    private String fechaVencimiento;

    public Tarjeta(String marca, String numero, String cardholder, String fechaVencimiento) {
        this.marca = marca;
        this.numero = numero;
        this.cardholder = cardholder;
        this.fechaVencimiento = fechaVencimiento;
    }

    public void mostrarInformacion() {
        System.out.println("Información de la tarjeta:\n" + obtenerInformacion());
    }

    public void mostrarOperacionValida(Scanner scanner) {
        System.out.print("Ingrese el monto de la operación: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Monto no válido. Intente nuevamente.");
            scanner.next();
            System.out.print("Ingrese el monto de la operación: ");
        }
        double monto = scanner.nextDouble();

        boolean operacionValida = esOperacionValida(monto);
        if (operacionValida) {
            System.out.println("¿La operación es válida? " + "Si, la operación es válida.");
        } else {
            System.out.println("¿La operación es válida? " + "No, la operación no es válida porque supera el consumo máximo permitido.");
        }
    }

    public void mostrarTarjetaValida() {
        boolean tarjetaValida = esTarjetaValida();
        if (tarjetaValida) {
            System.out.println("¿La tarjeta es válida para operar? " + "Si, la tarjeta es válida para operar");
        } else {
            System.out.println("¿La tarjeta es válida para operar? " + "No, la tarjeta está caducada. No se puede usar para operar.");
        }
    }

    public boolean esDistintaA(Tarjeta otraTarjeta) {
        return !numero.equals(otraTarjeta.numero);
    }

    public void mostrarTasaOperacion(Scanner scanner) {
        System.out.print("Ingrese el monto de la operación: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Monto no válido. Intente nuevamente.");
            scanner.next();
            System.out.print("Ingrese el monto de la operación: ");
        }
        double monto = scanner.nextDouble();

        double tasaOperacion = obtenerTasaOperacion(monto);
        System.out.printf("Tasa de la operación de la tarjeta " + getMarca() + " ingresada: $%.2f%n", tasaOperacion);
    }

    public String obtenerInformacion() {
        return "Marca: " + marca + ", Número: " + numero +
                ", Cardholder: " + cardholder + ", Fecha de Vencimiento: " + fechaVencimiento;
    }

    private boolean esOperacionValida(double monto) {
        double tasa = obtenerTasaOperacion(monto);
        double montoTotal = monto + tasa;
        return montoTotal < 1000;
    }

    private boolean esTarjetaValida() {
        LocalDate fechaActual = LocalDate.now();
        int anioActual = fechaActual.getYear();
        int mesActual = fechaActual.getMonthValue();

        String[] partesFechaVencimiento = fechaVencimiento.split("/");
        int mesVencimiento = Integer.parseInt(partesFechaVencimiento[0]);
        int anioVencimiento = Integer.parseInt(partesFechaVencimiento[1]) + 2000;

        return anioVencimiento > anioActual || (anioVencimiento == anioActual && mesVencimiento >= mesActual);
    }

    private MarcaTarjeta obtenerMarcaTarjeta() {
        switch (marca.toUpperCase()) {
            case "VISA":
                return new com.example.ejercicio1.tarjetas.Visa();
            case "NARA":
                return new com.example.ejercicio1.tarjetas.Nara();
            case "AMEX":
                return new com.example.ejercicio1.tarjetas.Amex();
            default:
                throw new IllegalArgumentException("Marca de tarjeta no válida: " + marca);
        }
    }

    public double obtenerTasaOperacion(double monto) {
        MarcaTarjeta marcaTarjeta = obtenerMarcaTarjeta();
        return marcaTarjeta.calcularTasa(monto);
    }

    public String getMarca() {
        return marca;
    }

    public String getNumero() {
        return numero;
    }

    public String getCardholder() {
        return cardholder;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
