package com.example.ejercicio1.tarjetas;

import java.time.LocalDate;

public class Nara implements MarcaTarjeta {
    @Override
    public String getNombre() {
        return "NARA";
    }

    @Override
    public double calcularTasa(double monto) {
        int diaMesActual = LocalDate.now().getDayOfMonth();

        double tasa = diaMesActual * 0.5;
        tasa = Math.max(0.3, Math.min(tasa, 5.0));

        return monto * (tasa / 100);
    }
}