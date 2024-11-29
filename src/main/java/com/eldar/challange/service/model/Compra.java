package com.eldar.challange.service.model;

import com.eldar.challange.service.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    private String detalle;
    private double importe;
    private String cvv;
    private Tarjeta tarjetaCompra;
    private Usuario titularTarjeta;
}
