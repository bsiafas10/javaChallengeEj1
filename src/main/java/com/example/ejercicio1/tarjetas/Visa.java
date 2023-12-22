package com.example.ejercicio1.tarjetas;

import java.time.LocalDate;

public class Visa implements MarcaTarjeta {
    @Override
    public String getNombre() {
        return "VISA";
    }

    @Override
    public double calcularTasa(double monto) {
        LocalDate fechaActual = LocalDate.now();

        int anioActual = fechaActual.getYear() % 100;
        int mesActual = fechaActual.getMonthValue();

        double tasa = (double) anioActual / mesActual;
        tasa = Math.max(0.3, Math.min(tasa, 5.0));

        return monto * (tasa / 100);
    }
}