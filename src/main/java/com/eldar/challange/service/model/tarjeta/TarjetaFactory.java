package com.eldar.challange.service.model.tarjeta;

import java.time.LocalDate;

public class TarjetaFactory {

    public static Tarjeta createTarjeta(MarcaTarjeta marca,
                                        String pan,
                                        String nombreTitular,
                                        LocalDate fechaVencimiento,
                                        String cvv) {
        return switch (marca) {
            case VISA -> new TarjetaVisa(pan, nombreTitular, fechaVencimiento, cvv);
            case NARA -> new TarjetaNaranja(pan, nombreTitular, fechaVencimiento, cvv);
            case AMEX -> new TarjetaAmex(pan, nombreTitular, fechaVencimiento, cvv);
        };
    }

    public static Tarjeta createTarjeta(MarcaTarjeta marca) {
        return switch (marca) {
            case VISA -> new TarjetaVisa();
            case NARA -> new TarjetaNaranja();
            case AMEX -> new TarjetaAmex();
        };
    }
}
