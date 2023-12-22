package com.example.ejercicio1.tarjetas;

import java.time.LocalDate;

public class Amex implements MarcaTarjeta {
    @Override
    public String getNombre() {
        return "AMEX";
    }

    @Override
    public double calcularTasa(double monto) {
        int mesActual = LocalDate.now().getMonthValue();

        double tasa = mesActual * 0.1;
        tasa = Math.max(0.3, Math.min(tasa, 5.0));

        return monto * (tasa / 100);
    }
}