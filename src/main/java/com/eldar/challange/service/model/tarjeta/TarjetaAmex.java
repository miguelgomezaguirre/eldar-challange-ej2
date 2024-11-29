package com.eldar.challange.service.model.tarjeta;

import java.time.LocalDate;

public class TarjetaAmex extends Tarjeta {

    public TarjetaAmex(String pan, String nombreTitular, LocalDate fechaVencimiento, String cvv) {
        super(pan, nombreTitular, fechaVencimiento, cvv);
        this.setMarca(MarcaTarjeta.AMEX);
    }

    public TarjetaAmex() {
        super();
        this.setMarca(MarcaTarjeta.AMEX);
    }

    @Override
    public double calcularTasaDeServicio(LocalDate fecha) {
        return fecha.getMonthValue() * 0.1;
    }
}
