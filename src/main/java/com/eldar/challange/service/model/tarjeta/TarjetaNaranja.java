package com.eldar.challange.service.model.tarjeta;


import java.time.LocalDate;

public class TarjetaNaranja extends Tarjeta {

    public TarjetaNaranja(String pan, String nombreTitular, LocalDate fechaVencimiento, String cvv) {
        super(pan, nombreTitular, fechaVencimiento, cvv);
        this.setMarca(MarcaTarjeta.NARA);
    }

    public TarjetaNaranja() {
        super();
        this.setMarca(MarcaTarjeta.NARA);
    }

    @Override
    public double calcularTasaDeServicio(LocalDate fecha) {
        return fecha.getDayOfMonth() * 0.5;
    }
}
