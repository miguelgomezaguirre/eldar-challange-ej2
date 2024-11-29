package com.eldar.challange.service.model.tarjeta;

import com.eldar.challange.service.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public abstract class Tarjeta {
    private String pan;
    private LocalDate fechaVencimiento;
    private String nombreTitular;
    private MarcaTarjeta marca;
    private String cvv;
    private Usuario titularTarjeta; //cardholder

    public abstract double calcularTasaDeServicio(LocalDate fecha);

    protected Tarjeta(String pan, String nombreTitular, LocalDate fechaVencimiento, String cvv) {
        this.pan = pan;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
    }
}
