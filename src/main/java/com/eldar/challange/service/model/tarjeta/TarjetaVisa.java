package com.eldar.challange.service.model.tarjeta;

import java.time.LocalDate;

public class TarjetaVisa extends Tarjeta {

    public TarjetaVisa(String pan, String nombreTitular, LocalDate fechaVencimiento, String cvv) {
        super(pan, nombreTitular, fechaVencimiento, cvv);
        this.setMarca(MarcaTarjeta.VISA);
    }

    public TarjetaVisa() {
        super();
        this.setMarca(MarcaTarjeta.VISA);
    }

    @Override
    public double calcularTasaDeServicio(LocalDate fecha) {
        return (double) (fecha.getYear() % 100) / fecha.getMonthValue();
    }
}
